package ECommerce.role.mapper;

import ECommerce.role.commons.RoleTable;
import ECommerce.role.model.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Role(
                rs.getInt(RoleTable.COLUMN_ROLE_ID),
                rs.getString(RoleTable.COLUMN_ROLE_NAME)
        );
    }
}
