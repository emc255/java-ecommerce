package ECommerce.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemDTOPost {
    private final int id;
    private final String brandName;
    private final String model;
    private final int year;
    private final String type;
    private final String image;
    private final String description;
    private final double price;
    private final int quantity;
}
