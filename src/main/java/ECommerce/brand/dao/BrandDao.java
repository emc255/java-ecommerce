package ECommerce.brand.dao;

import ECommerce.brand.dto.BrandDTO;
import ECommerce.brand.model.Brand;

import java.util.List;

public interface BrandDao {
    List<Brand> getBrandList();

    Brand findBrandByName(String brandName);

    Brand findBrandById(int brandId);

    void addBrand(String brandName);

    void addBrand(BrandDTO brandDTO);

    void updateBrand(Brand brand);

    void deleteBrand(int brandId);

}
