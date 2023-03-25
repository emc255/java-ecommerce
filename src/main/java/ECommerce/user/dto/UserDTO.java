package ECommerce.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDTO {
    private final String accessToken;
    private final String refreshToken;
    private final String errorMessage;
    private final String role;
    private final boolean booleanRefreshTokenExpired;


}
