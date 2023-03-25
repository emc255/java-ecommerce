package ECommerce.user.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreatePayment {
    @SerializedName("items")
    Object[] items;
}
