package restaurantApi.service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import restaurantApi.dto.orderItemDTO.OrderItemRequest;
import restaurantApi.entity.Customer;
import restaurantApi.entity.OrderItem;
import restaurantApi.entity.Product;
import restaurantApi.entity.enums.*;
import restaurantApi.entity.Order;
import restaurantApi.dto.orderDTO.*;
import restaurantApi.repository.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import restaurantApi.repository.ProductRepository;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest ){
        Customer customer = customerRepository.findById(orderRequest.customerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found!"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());
        order.setTotalPrice(BigDecimal.ZERO);

        Order savedOrder = orderRepository.save(order);

        return new OrderResponse(
                savedOrder.getId(),
                savedOrder.getCustomer().getId(),
                savedOrder.getOrderStatus(),
                savedOrder.getTotalPrice(),
                savedOrder.getCreatedAt()
        );
    }

@Transactional
    public OrderResponse addItemToOrder(Long productId, Long id, OrderItemRequest requestItem){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Order Not found!"));

        if (order.getOrderStatus() != OrderStatus.CREATED) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Items can only be added when order status is CREATED"
            );
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found!"));

        if (requestItem.quantity() == null || requestItem.quantity() <= 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantity must be greater than zero");
        }

        OrderItem existingItem = null;

        for(OrderItem item : order.getOrderItems()){
            if (item.getProduct().getId().equals(product.getId())){
                existingItem = item;
                break;
            }
        }

        if (existingItem != null){
            existingItem.setQuantity(existingItem.getQuantity() + requestItem.quantity());
        }
        else {
            OrderItem newItem = new OrderItem();
            newItem.setOrder(order);
            newItem.setProduct(product);
            newItem.setProductName(product.getName());
            newItem.setQuantity(requestItem.quantity());
            newItem.setPrice(product.getPrice());

            order.getOrderItems().add(newItem);

        }

        recalculateTotal(order);
        Order savedOrder = orderRepository.save(order);

        return new OrderResponse(
                savedOrder.getId(),
                savedOrder.getCustomer().getId(),
                savedOrder.getOrderStatus(),
                savedOrder.getTotalPrice(),
                savedOrder.getCreatedAt()
        );

    }

    private void recalculateTotal(Order order){
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem item : order.getOrderItems()){
            BigDecimal itemTotal = item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            total = total.add(itemTotal);
        }
        order.setTotalPrice(total);
    }

    public OrderResponse updateOrderStatus(Long orderId, UpdateOrderStatusRequest request){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Order Not found!"));

        OrderStatus currentStatus = order.getOrderStatus();
        OrderStatus newStatus = request.orderStatus();

        System.out.println("Order ID: " + orderId);
        System.out.println("Current status: " + currentStatus);
        System.out.println("Requested new status: " + newStatus);

        boolean isValid =
                (currentStatus == OrderStatus.CREATED && newStatus == OrderStatus.PREPARING) ||
                (currentStatus == OrderStatus.CREATED && newStatus == OrderStatus.CANCELLED) ||
                (currentStatus == OrderStatus.PREPARING && newStatus == OrderStatus.READY) ||
                (currentStatus == OrderStatus.PREPARING && newStatus == OrderStatus.CANCELLED) ||
                (currentStatus == OrderStatus.READY && newStatus == OrderStatus.DELIVERED);

                if (!isValid){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid status transition");
                }

        order.setOrderStatus(newStatus);

        Order savedOrder = orderRepository.save(order);

        return new OrderResponse(
                savedOrder.getId(),
                savedOrder.getCustomer().getId(),
                savedOrder.getOrderStatus(),
                savedOrder.getTotalPrice(),
                savedOrder.getCreatedAt()
        );
    }

    public OrderResponse orderById(Long orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Order not found!"));

        return new OrderResponse(
                order.getId(),
                order.getCustomer().getId(),
                order.getOrderStatus(),
                order.getTotalPrice(),
                order.getCreatedAt()
        );
    }

    public List<OrderResponse> findAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(order -> new OrderResponse(
                        order.getId(),
                        order.getCustomer().getId(),
                        order.getOrderStatus(),
                        order.getTotalPrice(),
                        order.getCreatedAt()
                ))
                .toList();
    }

}
