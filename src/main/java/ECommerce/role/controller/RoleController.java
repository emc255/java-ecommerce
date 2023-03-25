package ECommerce.role.controller;


import ECommerce.role.model.Role;
import ECommerce.role.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/role")
@CrossOrigin(origins = {"https://eeeshop.netlify.app/", "http://localhost:3000/"})
@AllArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping(path = "list")
    public List<Role> getRoleList() {
        return roleService.getRoleList();
    }
}
