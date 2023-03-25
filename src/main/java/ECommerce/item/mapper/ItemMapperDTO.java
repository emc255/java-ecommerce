//package ECommerce.item.mapper;
//
//import ECommerce.brand.commons.BrandTable;
//import ECommerce.item.commons.ItemTable;
//import ECommerce.item.dto.ItemDTO;
//import lombok.AllArgsConstructor;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//@AllArgsConstructor
//public class ItemMapperDTO implements RowMapper<ItemDTO> {
//
//    @Override
//    public ItemDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//        return new ItemDTO(
//                rs.getInt(ItemTable.COLUMN_ITEM_ID),
//                rs.getString(BrandTable.COLUMN_BRAND_NAME),
//                rs.getString(ItemTable.COLUMN_ITEM_MODEL),
//                Integer.parseInt(rs.getString(ItemTable.COLUMN_ITEM_YEAR)),
//                rs.getString(ItemTable.COLUMN_ITEM_TYPE),
//                rs.getString(ItemTable.COLUMN_ITEM_IMAGE),
//                rs.getString(ItemTable.COLUMN_ITEM_DESCRIPTION),
//                rs.getDouble(ItemTable.COLUMN_ITEM_PRICE),
//                rs.getInt(ItemTable.COLUMN_ITEM_QUANTITY)
//        );
//    }
//}
