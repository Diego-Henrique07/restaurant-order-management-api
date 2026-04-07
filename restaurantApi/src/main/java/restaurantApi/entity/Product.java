package restaurantApi.entity;

import jakarta.persistence.*;
import jakarta.annotation.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(nullable = false)
    private BigDecimal price;

}
