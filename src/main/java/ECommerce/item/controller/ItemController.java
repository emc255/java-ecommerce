package ECommerce.item.controller;

import ECommerce.item.dto.ItemDTO;
import ECommerce.item.dto.ItemDTOPost;
import ECommerce.item.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/item")
@CrossOrigin(origins = {"https://eeeshop.netlify.app/", "http://localhost:3000/"})
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping(path = "list")
    public List<ItemDTO> getItemList() {
        return itemService.getItemList();
    }

    @PostMapping(path = "add")
    public void addItem(@RequestBody ItemDTOPost itemDTOPost) {
        itemService.addItem(itemDTOPost);
    }

    //    @PutMapping(path = "{id}")
//    public String updateItem(@RequestBody ItemDTO itemDTO) {
//        return itemService.updateItem(itemDTO);
//    }
//
    @DeleteMapping(path = "delete")
    public boolean deleteItem(@RequestParam int itemId) {
        return itemService.deleteItem(itemId);
    }
}
