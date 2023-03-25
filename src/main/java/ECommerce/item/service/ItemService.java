package ECommerce.item.service;

import ECommerce.brand.dao.BrandDaoDaoImpl;
import ECommerce.brand.model.Brand;
import ECommerce.item.dao.ItemDaoImpl;
import ECommerce.item.dto.ItemDTO;
import ECommerce.item.dto.ItemDTOPost;
import ECommerce.item.model.Item;
import ECommerce.item.model.ItemImage;
import ECommerce.role.doa.RoleDaoImpl;
import ECommerce.user.dao.UserDaoDaoImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {
    private final UserDaoDaoImpl userDaoDao;
    private final BrandDaoDaoImpl brandDaoImpl;
    private final ItemDaoImpl itemDaoImpl;
    private final RoleDaoImpl roleDaoImpl;


    public List<ItemDTO> getItemList() {
        List<Item> items = itemDaoImpl.getItemList();
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item : items) {
            List<ItemImage> itemImageList = itemDaoImpl.findItemImageListByItemId(item.getId());
            ItemDTO ItemDTO = new ItemDTO(
                    item.getId(),
                    brandDaoImpl.findBrandById(item.getBrandId()).getName(),
                    item.getModel(),
                    item.getYear(),
                    item.getType(),
                    itemImageList,
                    item.getDescription(),
                    item.getPrice(),
                    item.getQuantity());

            itemDTOList.add(ItemDTO);
        }
        return itemDTOList;
    }

    public void addItem(ItemDTOPost itemDTOPost) {
        try {
            String brandName = itemDTOPost.getBrandName();

            if (brandDaoImpl.checkBrandByName(brandName)) {
                brandDaoImpl.addBrand(brandName);
            }
            Brand brand = brandDaoImpl.findBrandByName(brandName);
            int itemId = itemDaoImpl.addItem(brand.getId(), itemDTOPost);

            if (!itemDTOPost.getImage().equals("")) {
                itemDaoImpl.addItemImage(itemId, itemDTOPost.getImage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    //    public String updateItem(ItemDTO itemDTO) {
//        try {
//            String brandName = itemDTO.getBrandName();
//            if (itemDaoImpl.checkItemId(itemDTO.getId())) {
//                throw new ItemException("Vehicle Id Is Not Valid");
//            }
//
//            if (brandDaoImpl.checkBrandByName(brandName)) {
//                brandDaoImpl.addBrand(brandName);
//            }
//            int brandId = brandDaoImpl.findBrandByName(brandName);
//            itemDaoImpl.updateItem(brandId, itemDTO);
//            return "Update Vehicle Successfully";
//        } catch (ItemException e) {
//            return e.getMessage();
//        }
//    }
//

    public boolean deleteItem(int itemId) {
        try {
            Item item = itemDaoImpl.findItemById(itemId);
            if (item == null) {
                throw new NullPointerException();
            }
            itemDaoImpl.deleteItem(itemId);
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }


}
