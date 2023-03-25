package ECommerce.brand.dao;

import ECommerce.brand.commons.BrandTable;
import ECommerce.brand.dto.BrandDTO;
import ECommerce.brand.mapper.BrandMapper;
import ECommerce.brand.model.Brand;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class BrandDaoDaoImpl implements BrandDao {

    //query
    private static final String QUERY_BRANDS = "SELECT  * FROM " + BrandTable.TABLE_BRAND;
    private static final String QUERY_BRAND_BY_ID = "SELECT * FROM " + BrandTable.TABLE_BRAND +
            " WHERE " + BrandTable.COLUMN_BRAND_ID + " = ?";
    private static final String QUERY_BRAND_BY_NAME = "SELECT * FROM " + BrandTable.TABLE_BRAND +
            " WHERE " + BrandTable.COLUMN_BRAND_NAME + " = ?";


    //insert
    private static final String INSERT_BRAND_NAME = "INSERT INTO " +
            "public." + BrandTable.TABLE_BRAND +
            "(" + BrandTable.COLUMN_BRAND_NAME + ")" +
            "VALUES (?)";
    private static final String INSERT_BRAND = "INSERT INTO " +
            "public." + BrandTable.TABLE_BRAND +
            "(" + BrandTable.COLUMN_BRAND_NAME + ", " +
            BrandTable.COLUMN_BRAND_PHONE + ")" +
            "VALUES (?, ?)";
    //update
    private static final String UPDATE_BRAND = "UPDATE public." + BrandTable.TABLE_BRAND +
            " SET " + BrandTable.COLUMN_BRAND_NAME + " = ?, " +
            BrandTable.COLUMN_BRAND_PHONE + " = ? " +
            "WHERE " + BrandTable.COLUMN_BRAND_ID + " = ?";
    //delete
    private static final String DELETE_BRAND_BY_ID = "DELETE FROM " + BrandTable.TABLE_BRAND +
            " WHERE " + BrandTable.COLUMN_BRAND_ID + " = ?";
    private final JdbcTemplate jdbcTemplate;


    @Override
    public List<Brand> getBrandList() {
        return jdbcTemplate.query(QUERY_BRANDS, new BrandMapper());
    }

    @Override
    public Brand findBrandByName(String brandName) {
        return jdbcTemplate.query(QUERY_BRAND_BY_NAME, new BrandMapper(), brandName.toUpperCase()).get(0);
    }

    @Override
    public Brand findBrandById(int brandId) {
        return jdbcTemplate.query(QUERY_BRAND_BY_ID, new BrandMapper(), brandId).get(0);
    }

    @Override
    public void addBrand(String brandName) {
        jdbcTemplate.update(INSERT_BRAND_NAME, brandName.toUpperCase());
    }

    @Override
    public void addBrand(BrandDTO brandDTO) {
        jdbcTemplate.update(INSERT_BRAND, brandDTO.getName().toUpperCase(), brandDTO.getPhone());
    }

    @Override
    public void updateBrand(Brand brand) {
        jdbcTemplate.update(UPDATE_BRAND,
                brand.getName(),
                brand.getPhone(),
                brand.getId()
        );
    }

    @Override
    public void deleteBrand(int brandId) {
        jdbcTemplate.update(DELETE_BRAND_BY_ID, brandId);
    }

    public boolean checkBrandById(int brandId) {
        List<Brand> brands = jdbcTemplate.query(QUERY_BRAND_BY_ID, new BrandMapper(), brandId);
        return brands.isEmpty();
    }

    public boolean checkBrandByName(String brandName) {
        List<Brand> brands = jdbcTemplate.query(QUERY_BRAND_BY_NAME, new BrandMapper(), brandName.toUpperCase());
        return brands.isEmpty();
    }


}
