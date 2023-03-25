package ECommerce.brand.mapper;

import ECommerce.brand.model.Brand;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandMapper implements RowMapper<Brand> {

    private static final String COLUMN_BRAND_ID = "id";
    private static final String COLUMN_BRAND_NAME = "name";
    private static final String COLUMN_BRAND_PHONE = "phone";

    @Override
    public Brand mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Brand(
                rs.getInt(COLUMN_BRAND_ID),
                rs.getString(COLUMN_BRAND_NAME),
                rs.getString(COLUMN_BRAND_PHONE)
        );
    }
}
