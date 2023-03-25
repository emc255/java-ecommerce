package ECommerce.user.mapper;

import ECommerce.user.commons.UserItemTable;
import ECommerce.user.model.UserItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserItemMapper implements RowMapper<UserItem> {
    @Override
    public UserItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserItem(
                rs.getInt(UserItemTable.COLUMN_USER_ITEM_ID),
                rs.getInt(UserItemTable.COLUMN_USER_ITEM_USER_ID),
                rs.getInt(UserItemTable.COLUMN_USER_ITEM_ITEM_ID)
        );
    }
}
