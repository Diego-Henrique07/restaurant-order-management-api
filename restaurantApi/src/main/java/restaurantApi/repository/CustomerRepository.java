package restaurantApi.repository;
import restaurantApi.entity.Customer;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
}