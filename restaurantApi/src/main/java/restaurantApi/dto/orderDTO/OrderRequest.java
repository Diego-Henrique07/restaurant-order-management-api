package restaurantApi.dto.orderDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import restaurantApi.entity.Customer;
import restaurantApi.entity.enums.OrderStatus;

public record OrderRequest(

        @NotNull
        Long customerId
) {


}
