package ECommerce.brand.controller;

import ECommerce.brand.dto.BrandDTO;
import ECommerce.brand.model.Brand;
import ECommerce.brand.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/brand")
@CrossOrigin(origins = {"https://eeeshop.netlify.app/", "http://localhost:3000/"})
@AllArgsConstructor
public class BrandController {

    private final BrandService brandService;


    @GetMapping(path = "list")
    public List<Brand> getBrandList() {
        return brandService.getBrandList();
    }

    @PostMapping()
    public String addBrand(@RequestBody BrandDTO brandDTO) {
        return brandService.addBrand(brandDTO);
    }

//    @PutMapping(path = "{id}")
//    public String updateBrand(@RequestBody Brand brand) {
//        return brandService.updateBrand(brand);
//    }
//
//    @DeleteMapping(path = "{id}")
//    public String deleteBrand(@RequestParam int brandId) {
//        return brandService.deleteBrand(brandId);
//    }
}
