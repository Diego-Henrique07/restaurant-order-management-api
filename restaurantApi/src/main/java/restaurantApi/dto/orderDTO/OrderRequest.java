package restaurantApi.dto.orderDTO;
import jakarta.validation.constraints.NotNull;

public record OrderRequest(
        @NotNull
        Long customerId
) {


}
