package ECommerce.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private final int id;
    private final String name;
    private final String username;
    private final String password;

}
