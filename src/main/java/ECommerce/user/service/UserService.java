package ECommerce.user.service;

import ECommerce.role.doa.RoleDaoImpl;
import ECommerce.role.model.Role;
import ECommerce.security.commons.Security;
import ECommerce.user.commons.UserException;
import ECommerce.user.dao.UserDaoDaoImpl;
import ECommerce.user.dto.UserDTO;
import ECommerce.user.model.User;
import ECommerce.user.model.UserItem;
import ECommerce.user.model.UserRole;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserDaoDaoImpl userDaoImpl;
    private final RoleDaoImpl roleDaoImpl;

    public List<User> getUserList() {
        return userDaoImpl.getUserList();
    }

    public List<UserItem> getUserItemList(HttpServletRequest request) {
        String username = decodeUsername(request);
        User user = userDaoImpl.findUserByUsername(username);
        return userDaoImpl.getUserItemList(user.getId());
    }

    public void addItemInUserItem(HttpServletRequest request, int itemId) {
        String username = decodeUsername(request);
        User user = userDaoImpl.findUserByUsername(username);
        userDaoImpl.addItemInUserItem(user.getId(), itemId);
    }

    public void deleteItemInUserItem(HttpServletRequest request, int itemId) {
        String username = decodeUsername(request);
        User user = userDaoImpl.findUserByUsername(username);
        userDaoImpl.deleteItemInUserItem(user.getId(), itemId);
    }

    public void deleteItemInUserItem(int id) {
        userDaoImpl.deleteItemInUserItem(id);
    }

    public boolean isUsernameAvailable(String username) {
        if (username.isEmpty()) {
            return true;
        }
        User user = userDaoImpl.findUserByUsername(username);
        return user != null;
    }

    public void createUser(String firstname, String username, String password) {
        //CREATING BYCRYPT PASSWORD
//        String pw_hash = BCrypt.hashpw(password, BCrypt.gensalt());
        Role role = roleDaoImpl.findRoleByName("User");
        userDaoImpl.createUser(firstname, username, password);
        User user = userDaoImpl.findUserByUsername(username);
        userDaoImpl.addRoleToUser(user.getId(), role.getId());
    }

    public UserDTO refreshUserAccessToken(HttpServletRequest request, String refresh_token) {
        Algorithm algorithm = Algorithm.HMAC256(Security.SECRET.getBytes());
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(refresh_token);
        String username = decodedJWT.getSubject();

        User user = userDaoImpl.findUserByUsername(username);
        List<UserRole> userRoleList = userDaoImpl.findUserRoleListByUserId(user.getId());
        List<String> roles = new ArrayList<>();
        for (UserRole ur : userRoleList) {
            String roleName = roleDaoImpl.findRoleById(ur.getRoleId()).getName();
            roles.add(roleName);
        }

        String access_token = JWT.create().withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", roles)
                .sign(algorithm);

        String roleName = userRole(roles);
        return new UserDTO(access_token, refresh_token, "", roleName, false);
    }


    //UserDetails
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {

            User user = userDaoImpl.findUserByUsername(username);
            if (user == null) {
                throw new UserException("no user match");
            }
            List<UserRole> userRoleList = userDaoImpl.findUserRoleListByUserId(user.getId());

            List<String> roles = new ArrayList<>();
            for (UserRole ur : userRoleList) {
                String roleName = roleDaoImpl.findRoleById(ur.getRoleId()).getName();
                roles.add(roleName);
            }

            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            roles.forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role));
            });

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

        } catch (UserException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //helper method
    private String decodeUsername(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String access_token = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256(Security.SECRET.getBytes());
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(access_token);
        return decodedJWT.getSubject();
    }

    private String userRole(List<String> roles) {
        if (roles.contains("Admin")) {
            return "Admin";
        } else if (roles.contains("Employee")) {
            return "Employee";
        } else {
            return "User";
        }
    }
}
