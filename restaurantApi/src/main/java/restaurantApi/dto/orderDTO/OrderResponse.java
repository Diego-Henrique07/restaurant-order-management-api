package restaurantApi.dto.orderDTO;
import restaurantApi.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderResponse(
        Long id,
        Long customerId,
        OrderStatus orderStatus,
        BigDecimal totalPrice,
        LocalDateTime createdAt

) {





}
