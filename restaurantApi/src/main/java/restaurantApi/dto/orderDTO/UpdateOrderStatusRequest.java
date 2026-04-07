package restaurantApi.dto.orderDTO;
import restaurantApi.entity.enums.OrderStatus;

public record UpdateOrderStatusRequest(
        OrderStatus orderStatus
) {}
