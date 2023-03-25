package ECommerce.brand.mapper;

import ECommerce.brand.dto.BrandDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandMapperDTO implements RowMapper<BrandDTO> {

    private static final String COLUMN_BRAND_NAME = "name";
    private static final String COLUMN_BRAND_PHONE = "phone";

    @Override
    public BrandDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new BrandDTO(
                rs.getString(COLUMN_BRAND_NAME),
                rs.getString(COLUMN_BRAND_PHONE)
        );
    }
}
