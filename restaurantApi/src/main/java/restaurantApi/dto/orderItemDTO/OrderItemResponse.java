package restaurantApi.dto.orderItemDTO;
import java.math.BigDecimal;

public record OrderItemResponse(

        long id,
        String productName,
        Integer quantity,
        BigDecimal price,
        Long productId

) {
}
