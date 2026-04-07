package restaurantApi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    private String productName;

    @Getter
    @Setter
    @Column(nullable = false)
    private Integer quantity;

    @Getter
    @Setter
    @Column(nullable = false)
    private BigDecimal price;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}
