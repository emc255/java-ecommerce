package ECommerce.item.mapper;

import ECommerce.item.commons.ItemTable;
import ECommerce.item.model.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMapper implements RowMapper<Item> {
    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Item(
                rs.getInt(ItemTable.COLUMN_ITEM_ID),
                rs.getInt(ItemTable.COLUMN_ITEM_BRAND_ID),
                rs.getString(ItemTable.COLUMN_ITEM_MODEL),
                rs.getInt(ItemTable.COLUMN_ITEM_YEAR),
                rs.getString(ItemTable.COLUMN_ITEM_TYPE),
                rs.getString(ItemTable.COLUMN_ITEM_DESCRIPTION),
                rs.getDouble(ItemTable.COLUMN_ITEM_PRICE),
                rs.getInt(ItemTable.COLUMN_ITEM_QUANTITY)

        );
    }
}
