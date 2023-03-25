package ECommerce.role.doa;


import ECommerce.role.commons.RoleTable;
import ECommerce.role.mapper.RoleMapper;
import ECommerce.role.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@AllArgsConstructor
public class RoleDaoImpl implements RoleDao {

    //query
    private static final String QUERY_ROLES = "SELECT  * FROM " + RoleTable.TABLE_ROLE;
    private static final String QUERY_ROLE_BY_ID = "SELECT * FROM " + RoleTable.TABLE_ROLE +
            " WHERE " + RoleTable.COLUMN_ROLE_ID + " = ?";
    private static final String QUERY_ROLE_BY_NAME = "SELECT * FROM " + RoleTable.TABLE_ROLE +
            " WHERE " + RoleTable.COLUMN_ROLE_NAME + " = ?";
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Role> getRoleList() {
        return jdbcTemplate.query(QUERY_ROLES, new RoleMapper());
    }

    @Override
    public Role findRoleById(int roleId) {
        return jdbcTemplate.query(QUERY_ROLE_BY_ID, new RoleMapper(), roleId).get(0);
    }

    @Override
    public Role findRoleByName(String name) {
        return jdbcTemplate.query(QUERY_ROLE_BY_NAME, new RoleMapper(), name).get(0);
    }
}
