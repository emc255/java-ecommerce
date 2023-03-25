package ECommerce.user.dao;

import ECommerce.user.commons.UserItemTable;
import ECommerce.user.commons.UserRoleTable;
import ECommerce.user.commons.UserTable;
import ECommerce.user.mapper.UserItemMapper;
import ECommerce.user.mapper.UserMapper;
import ECommerce.user.mapper.UserRoleMapper;
import ECommerce.user.model.User;
import ECommerce.user.model.UserItem;
import ECommerce.user.model.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserDaoDaoImpl implements UserDao {
    //query
    private static final String QUERY_USERS = "SELECT  * FROM " + UserTable.TABLE_USERS;
    private static final String QUERY_USER_BY_ID = "SELECT * FROM " + UserTable.TABLE_USERS +
            " WHERE " + UserTable.COLUMN_USER_ID + " = ?";
    private static final String QUERY_USER_BY_USERNAME_AND_PASSWORD = "SELECT * FROM " + UserTable.TABLE_USERS +
            " WHERE " + UserTable.COLUMN_USER_USERNAME + " = ?" +
            " AND " + UserTable.COLUMN_USER_PASSWORD + " = ?";
    private static final String QUERY_USER_BY_USERNAME = "SELECT * FROM " + UserTable.TABLE_USERS +
            " WHERE " + UserTable.COLUMN_USER_USERNAME + " = ?";
    private static final String QUERY_USER_ITEM_BY_USER_ID = "SELECT * FROM " + UserItemTable.TABLE_USER_ITEM +
            " WHERE " + UserItemTable.COLUMN_USER_ITEM_USER_ID + " = ?";
    private static final String QUERY_COUNT_SHOPPING_CART = "SELECT COUNT(" +
            UserItemTable.COLUMN_USER_ITEM_ITEM_ID + ")" +
            " AS item" +
            " FROM " + UserItemTable.TABLE_USER_ITEM +
            " WHERE " + UserItemTable.COLUMN_USER_ITEM_USER_ID + " = ?";
    private static final String QUERY_USER_ROLE_BY_USER_ID = "SELECT * FROM " + UserRoleTable.TABLE_USER_ROLE +
            " WHERE " + UserRoleTable.COLUMN_USER_ROLE_USER_ID + " = ?";
    //insert
    private static final String INSERT_USER_ITEM = "INSERT INTO " +
            "public." + UserItemTable.TABLE_USER_ITEM +
            "(" + UserItemTable.COLUMN_USER_ITEM_USER_ID + ", " +
            UserItemTable.COLUMN_USER_ITEM_ITEM_ID + ")" +
            "VALUES (?, ?)";
    private static final String INSERT_USER = "INSERT INTO " +
            "public." + UserTable.TABLE_USERS +
            "(" + UserTable.COLUMN_USER_NAME + ", " +
            UserTable.COLUMN_USER_USERNAME + ", " +
            UserTable.COLUMN_USER_PASSWORD + ")" +
            "VALUES (?, ?, ?)";
    private static final String INSERT_USER_ROLE = "INSERT INTO " +
            "public." + UserRoleTable.TABLE_USER_ROLE +
            "(" + UserRoleTable.COLUMN_USER_ROLE_USER_ID + ", " +
            UserRoleTable.COLUMN_USER_ROLE_ROLE_ID + ")" +
            "VALUES (?, ?)";

    //delete
    private static final String DELETE_USER_ITEM_BY_USER_ID_AND_ITEM_ID = "DELETE FROM " + UserItemTable.TABLE_USER_ITEM +
            " WHERE " + UserItemTable.COLUMN_USER_ITEM_USER_ID + " = ?" +
            " AND " + UserItemTable.COLUMN_USER_ITEM_ITEM_ID + " = ?";
    private static final String DELETE_USER_ITEM_BY_ITEM_ID = "DELETE FROM " + UserItemTable.TABLE_USER_ITEM +
            " WHERE " + UserItemTable.COLUMN_USER_ITEM_ID + " = ?";
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getUserList() {
        return jdbcTemplate.query(QUERY_USERS, new UserMapper());
    }

    @Override
    public User findUserByUsername(String username) {
        List<User> users = jdbcTemplate.query(QUERY_USER_BY_USERNAME, new UserMapper(), username);
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public List<UserRole> findUserRoleListByUserId(int userId) {
        return jdbcTemplate.query(QUERY_USER_ROLE_BY_USER_ID, new UserRoleMapper(), userId);
    }

    @Override
    public void addItemInUserItem(int userId, int itemId) {
        jdbcTemplate.update(INSERT_USER_ITEM, userId, itemId);
    }

    @Override
    public void deleteItemInUserItem(int userId, int itemId) {
        jdbcTemplate.update(DELETE_USER_ITEM_BY_USER_ID_AND_ITEM_ID, userId, itemId);
    }

    @Override
    public void deleteItemInUserItem(int id) {
        jdbcTemplate.update(DELETE_USER_ITEM_BY_ITEM_ID, id);
    }

    @Override
    public List<UserItem> getUserItemList(int userId) {
        return jdbcTemplate.query(QUERY_USER_ITEM_BY_USER_ID, new UserItemMapper(), userId);
    }

    @Override
    public void createUser(String firstname, String username, String password) {
        jdbcTemplate.update(INSERT_USER, firstname, username, password);
    }

    @Override
    public void addRoleToUser(int userId, int roleId) {
        jdbcTemplate.update(INSERT_USER_ROLE, userId, roleId);
    }
}
