package ECommerce.role.doa;

import ECommerce.role.model.Role;

import java.util.List;

public interface RoleDao {
    List<Role> getRoleList();

    Role findRoleById(int roleId);

    Role findRoleByName(String name);
}
