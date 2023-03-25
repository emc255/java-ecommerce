package ECommerce.user.dao;

import ECommerce.user.model.User;
import ECommerce.user.model.UserItem;
import ECommerce.user.model.UserRole;

import java.util.List;

public interface UserDao {


    List<User> getUserList();

    List<UserRole> findUserRoleListByUserId(int userId);

    List<UserItem> getUserItemList(int userId);

    User findUserByUsername(String username);

    void addItemInUserItem(int userId, int itemId);

    void deleteItemInUserItem(int userId, int itemId);

    void deleteItemInUserItem(int id);

    void createUser(String firstname, String username, String password);

    void addRoleToUser(int userId, int roleId);

}
