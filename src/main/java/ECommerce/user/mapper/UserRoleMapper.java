package ECommerce.user.mapper;

import ECommerce.user.commons.UserRoleTable;
import ECommerce.user.model.UserRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleMapper implements RowMapper<UserRole> {
    @Override
    public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserRole(
                rs.getInt(UserRoleTable.COLUMN_USER_ROLE_ID),
                rs.getInt(UserRoleTable.COLUMN_USER_ROLE_USER_ID),
                rs.getInt(UserRoleTable.COLUMN_USER_ROLE_ROLE_ID));
    }
}
