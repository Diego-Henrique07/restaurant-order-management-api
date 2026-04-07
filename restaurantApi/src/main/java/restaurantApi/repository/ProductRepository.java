package restaurantApi.repository;
import restaurantApi.entity.Product;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    Optional<Product> findByName(String name);


}
