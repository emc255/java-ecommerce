package ECommerce.item.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Item {
    private final int id;
    private final int brandId;
    private final String model;
    private final int year;
    private final String type;
    private final String description;
    private final double price;
    private final int quantity;
}
