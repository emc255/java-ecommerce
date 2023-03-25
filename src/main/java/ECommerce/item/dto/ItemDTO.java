package ECommerce.item.dto;

import ECommerce.item.model.ItemImage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ItemDTO {
    private final int id;
    private final String brandName;
    private final String model;
    private final int year;
    private final String type;
    private final List<ItemImage> imageList;
    private final String description;
    private final double price;
    private final int quantity;
}
