package ECommerce.item.mapper;

import ECommerce.item.commons.ItemImageTable;
import ECommerce.item.model.ItemImage;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemImageMapper implements RowMapper<ItemImage> {
    @Override
    public ItemImage mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ItemImage(
                rs.getInt(ItemImageTable.COLUMN_ITEM_IMAGE_ID),
                rs.getInt(ItemImageTable.COLUMN_ITEM_IMAGE_ITEM_ID),
                rs.getString(ItemImageTable.COLUMN_ITEM_IMAGE_IMAGE)
        );
    }
}
