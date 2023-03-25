package ECommerce.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRole {
    private final int id;
    private final int userId;
    private final int roleId;
}
