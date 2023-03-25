package ECommerce.item.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemImage {
    private final int id;
    private final int itemId;
    private final String image;
}
