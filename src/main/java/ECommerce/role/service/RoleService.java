package ECommerce.role.service;

import ECommerce.role.doa.RoleDaoImpl;
import ECommerce.role.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleDaoImpl roleDaoImpl;

    public List<Role> getRoleList() {
        return roleDaoImpl.getRoleList();
    }
}
