package ECommerce.brand.service;

import ECommerce.brand.commons.BrandException;
import ECommerce.brand.dao.BrandDaoDaoImpl;
import ECommerce.brand.dto.BrandDTO;
import ECommerce.brand.model.Brand;
import ECommerce.item.dao.ItemDaoImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandService {

    private final BrandDaoDaoImpl brandDaoImpl;
    private final ItemDaoImpl itemDaoImpl;

    public List<Brand> getBrandList() {
        return brandDaoImpl.getBrandList();
    }

    public String addBrand(BrandDTO brandDTO) {
        try {
            if (brandDaoImpl.checkBrandByName(brandDTO.getName())) {
                brandDaoImpl.addBrand(brandDTO);
                return "Add Brand Successfully";
            }
            throw new BrandException(brandDTO.getName() + " is Already Exist");
        } catch (BrandException e) {
            return e.getMessage();
        }
    }

    public String updateBrand(Brand brand) {
        try {
            if (brandDaoImpl.checkBrandById(brand.getId())) {
                throw new BrandException("Invalid Brand Id");
            }
            brandDaoImpl.updateBrand(brand);
            return "Update Brand Successfully";
        } catch (BrandException e) {
            return e.getMessage();
        }
    }


}
