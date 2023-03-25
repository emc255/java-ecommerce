package ECommerce.user.mapper;

import ECommerce.user.commons.UserTable;
import ECommerce.user.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getInt(UserTable.COLUMN_USER_ID),
                rs.getString(UserTable.COLUMN_USER_NAME),
                rs.getString(UserTable.COLUMN_USER_USERNAME),
                rs.getString(UserTable.COLUMN_USER_PASSWORD)
        );
    }
}
