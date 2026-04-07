package restaurantApi.entity;
import restaurantApi.entity.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@RequiredArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Getter
    @Setter
    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Getter
    @Setter
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Getter
    @Setter
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();
}
