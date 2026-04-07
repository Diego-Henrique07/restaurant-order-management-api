package restaurantApi.repository;
import  restaurantApi.entity.Order;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import restaurantApi.entity.OrderItem;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
