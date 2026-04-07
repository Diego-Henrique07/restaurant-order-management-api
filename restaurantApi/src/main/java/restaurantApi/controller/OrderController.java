package restaurantApi.controller;
import jakarta.validation.Valid;
import restaurantApi.dto.orderDTO.*;
import restaurantApi.dto.orderItemDTO.OrderItemRequest;
import restaurantApi.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponse createOrder(@Valid @RequestBody OrderRequest request) {
        return orderService.createOrder(request);
    }

    @PostMapping("/{orderId}/items")
    public OrderResponse addItemToOrder(
            @PathVariable Long orderId,
            @Valid @RequestBody OrderItemRequest request) {
        return orderService.addItemToOrder(request.productId(), orderId, request);
    }

    @PatchMapping("/{orderId}/status")
    public OrderResponse updateOrderStatus(@PathVariable Long orderId,
                                           @Valid @RequestBody UpdateOrderStatusRequest request) {
        return orderService.updateOrderStatus(orderId, request);
    }

    @GetMapping("/{orderId}")
    public OrderResponse getOrderById(@PathVariable Long orderId) {
        return orderService.orderById(orderId);
    }

    @GetMapping
    public List<OrderResponse> getAllOrders() {
        return orderService.findAllOrders();
    }


}
