package ECommerce;

import ECommerce.security.commons.Security;
import com.stripe.Stripe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ECommerceApplication {


    public static void main(String[] args) {
        SpringApplication.run(ECommerceApplication.class, args);

    }

    @PostConstruct
    public void setup() {
        Stripe.apiKey = Security.STRIPE_API_KEY;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
