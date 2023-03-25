package ECommerce.security.config;


import ECommerce.security.filter.CustomAuthenticationFilter;
import ECommerce.security.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    //authorequest order matters if u want to pass certain api do top first
    protected void configure(HttpSecurity http) throws Exception {
        //changing the url of default login page
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/user/sign-in");

        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/item/list",
                        "/api/brand/list",
                        "/api/user/sign-in",
                        "/api/user/refresh/token/**",
                        "/api/user/isUsernameAvailable",
                        "/api/user/createUser").permitAll()

                .antMatchers(HttpMethod.GET,
                        "/api/user/inventory").hasAnyAuthority("Admin", "Employee", "User")

                .antMatchers(HttpMethod.POST, "/api/user/item/add",
                        "/api/user/create-payment-intent").hasAnyAuthority("Admin", "Employee", "User")

                .antMatchers(HttpMethod.DELETE,
                        "/api/user/item/delete",
                        "/api/user/item/delete/{id}"
                ).hasAnyAuthority("Admin", "Employee", "User")

                .antMatchers(HttpMethod.POST,
                        "/api/item/add").hasAnyAuthority("Admin", "Employee")

                .antMatchers(HttpMethod.DELETE,
                        "/api/item/delete").hasAnyAuthority("Admin")

                .anyRequest()
                .authenticated()
                .and()
                .addFilter(customAuthenticationFilter)
                .addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

//                .addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
//        http.csrf().disable();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.authorizeRequests().antMatchers("/api/login/**").permitAll();
//        http.authorizeRequests().antMatchers("/api/items/**").permitAll();
//
//        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/user/**").hasAnyAuthority("ROLE_USER");
//        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/users/save/**").hasAnyAuthority("ROLE_ADMIN");
//        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/users/**").hasAnyAuthority("ROLE_ADMIN");
//        http.authorizeRequests().anyRequest().authenticated();
//        //http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
//        http.addFilter(customAuthenticationFilter);


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth jdbc for my flyway
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }


}



