package ECommerce.item.dao;

import ECommerce.item.dto.ItemDTOPost;
import ECommerce.item.model.Item;
import ECommerce.item.model.ItemImage;

import java.util.List;

public interface ItemDao {

    //all user


    List<Item> getItemList();


    //auth user
    int addItem(int brandId, ItemDTOPost itemDTOPost);

    void addItemImage(int itemId, String image);

    void deleteItem(int itemId);

    Item findItemById(int itemId);


    List<ItemImage> findItemImageListByItemId(int itemId);
}
