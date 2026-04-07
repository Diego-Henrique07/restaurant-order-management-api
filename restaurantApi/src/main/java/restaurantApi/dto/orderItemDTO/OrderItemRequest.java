package restaurantApi.dto.orderItemDTO;
import restaurantApi.entity.Order;

import jakarta.validation.constraints.*;

public record OrderItemRequest(

        @NotNull
        Long productId,

        @NotNull
        @Min(1)
        Integer quantity
) {
}
