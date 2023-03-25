package ECommerce.item.dao;

import ECommerce.brand.commons.BrandTable;
import ECommerce.item.commons.ItemImageTable;
import ECommerce.item.commons.ItemTable;
import ECommerce.item.dto.ItemDTOPost;
import ECommerce.item.mapper.ItemImageMapper;
import ECommerce.item.mapper.ItemMapper;
import ECommerce.item.model.Item;
import ECommerce.item.model.ItemImage;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class ItemDaoImpl implements ItemDao {

    //query
    private static final String QUERY_ITEM_LIST = "SELECT * FROM " + ItemTable.TABLE_ITEM;

    private static final String QUERY_ITEMDTO = "SELECT " +
            ItemTable.TABLE_ITEM + "." + ItemTable.COLUMN_ITEM_ID + "," +
            BrandTable.TABLE_BRAND + "." + BrandTable.COLUMN_BRAND_NAME + "," +
            ItemTable.TABLE_ITEM + "." + ItemTable.COLUMN_ITEM_MODEL + "," +
            ItemTable.TABLE_ITEM + "." + ItemTable.COLUMN_ITEM_YEAR + "," +
            ItemTable.TABLE_ITEM + "." + ItemTable.COLUMN_ITEM_TYPE + "," +
            ItemTable.TABLE_ITEM + "." + ItemTable.COLUMN_ITEM_IMAGE + "," +
            ItemTable.TABLE_ITEM + "." + ItemTable.COLUMN_ITEM_DESCRIPTION + "," +
            ItemTable.TABLE_ITEM + "." + ItemTable.COLUMN_ITEM_PRICE + "," +
            ItemTable.TABLE_ITEM + "." + ItemTable.COLUMN_ITEM_QUANTITY +
            " FROM " + BrandTable.TABLE_BRAND +
            " INNER JOIN " + ItemTable.TABLE_ITEM +
            " ON " + ItemTable.TABLE_ITEM + "." + ItemTable.COLUMN_ITEM_BRAND_ID + "=" + BrandTable.TABLE_BRAND + "." + BrandTable.COLUMN_BRAND_ID;

    private static final String QUERY_ITEM_BY_ID = "SELECT * FROM " + ItemTable.TABLE_ITEM +
            " WHERE " + ItemTable.COLUMN_ITEM_ID + " = ?";

    private static final String QUERY_ITEM_BY_BRAND_ID = "SELECT * FROM " + ItemTable.TABLE_ITEM +
            " WHERE " + BrandTable.COLUMN_BRAND_ID + " = ?";

    private static final String QUERY_ITEM_IMAGE_BY_ITEM_ID = "SELECT * FROM " + ItemImageTable.TABLE_ITEM_IMAGE +
            " WHERE " + ItemImageTable.COLUMN_ITEM_IMAGE_ITEM_ID + " = ?";
    //insert
    private static final String INSERT_ITEM = "INSERT INTO " +
            "public." + ItemTable.TABLE_ITEM +
            "(" + ItemTable.COLUMN_ITEM_BRAND_ID + "," +
            ItemTable.COLUMN_ITEM_MODEL + "," +
            ItemTable.COLUMN_ITEM_YEAR + "," +
            ItemTable.COLUMN_ITEM_TYPE + "," +
            ItemTable.COLUMN_ITEM_DESCRIPTION + "," +
            ItemTable.COLUMN_ITEM_PRICE + "," +
            ItemTable.COLUMN_ITEM_QUANTITY +
            ") VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String INSERT_ITEM_IMAGE = "INSERT INTO " +
            "public." + ItemImageTable.TABLE_ITEM_IMAGE +
            "(" + ItemImageTable.COLUMN_ITEM_IMAGE_ITEM_ID + "," +
            ItemImageTable.COLUMN_ITEM_IMAGE_IMAGE +
            ") VALUES (?, ?)";

    //update
    private static final String UPDATE_ITEM = "UPDATE public." + ItemTable.TABLE_ITEM +
            " SET " + ItemTable.COLUMN_ITEM_BRAND_ID + " = ?, " +
            ItemTable.COLUMN_ITEM_MODEL + " = ?, " +
            ItemTable.COLUMN_ITEM_YEAR + " = ?, " +
            ItemTable.COLUMN_ITEM_TYPE + " = ?, " +
            ItemTable.COLUMN_ITEM_IMAGE + " = ?, " +
            ItemTable.COLUMN_ITEM_DESCRIPTION + " = ? " +
            "WHERE " + ItemTable.COLUMN_ITEM_ID + " = ?";

    //delete
    private static final String DELETE_ITEM_BY_ID = "DELETE FROM " + ItemTable.TABLE_ITEM +
            " WHERE " + ItemTable.COLUMN_ITEM_ID + " = ?";

    private final JdbcTemplate jdbcTemplate;

    //GET
    @Override
    public List<Item> getItemList() {
        return jdbcTemplate.query(QUERY_ITEM_LIST, new ItemMapper());
    }

    //FIND
    @Override
    public List<ItemImage> findItemImageListByItemId(int itemId) {
        List<ItemImage> imageList = jdbcTemplate.query(QUERY_ITEM_IMAGE_BY_ITEM_ID, new ItemImageMapper(), itemId);
        if (imageList.isEmpty()) {
            return null;
        }
        return imageList;
    }

    @Override
    public Item findItemById(int itemId) {
        return jdbcTemplate.query(QUERY_ITEM_BY_ID, new ItemMapper(), itemId).get(0);
    }

    //ADD
    @Override
    public int addItem(int brandId, ItemDTOPost itemDTOPost) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_ITEM,
                    new String[]{"id"});
            ps.setInt(1, brandId);
            ps.setString(2, itemDTOPost.getModel());
            ps.setInt(3, itemDTOPost.getYear());
            ps.setString(4, itemDTOPost.getType());
            ps.setString(5, itemDTOPost.getDescription());
            ps.setDouble(6, itemDTOPost.getPrice());
            ps.setInt(7, itemDTOPost.getQuantity());
            return ps;
        }, holder);

        return Objects.requireNonNull(holder.getKey(), "item must not be null").intValue();

    }

    @Override
    public void addItemImage(int itemId, String image) {
        jdbcTemplate.update(INSERT_ITEM_IMAGE, itemId, image);
    }

    //DELETE
    @Override
    public void deleteItem(int itemId) {
        jdbcTemplate.update(DELETE_ITEM_BY_ID, itemId);
    }


}
