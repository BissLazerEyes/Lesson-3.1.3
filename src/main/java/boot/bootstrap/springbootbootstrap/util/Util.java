package boot.bootstrap.springbootbootstrap.util;

import boot.bootstrap.springbootbootstrap.model.Role;
import boot.bootstrap.springbootbootstrap.model.User;
import boot.bootstrap.springbootbootstrap.service.RoleService;
import boot.bootstrap.springbootbootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Util {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public Util(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void dataBaseInit() {
        Role roleAdmin = new Role("ADMIN");
        Role roleUser = new Role("USER");
        Set<Role> adminSet = new HashSet<>();
        Set<Role> userSet = new HashSet<>();

        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);

        adminSet.add(roleAdmin);
        adminSet.add(roleUser);
        userSet.add(roleUser);

        User user = new User("Marie", "Smith", 23, "ivan@mail.com", "User", "user", userSet);
        User admin = new User("Edvard", "Blair", 30, "garry@gmail.com", "admin", "admin", adminSet);

        userService.saveUser(user);
        userService.saveUser(admin);
    }
}
