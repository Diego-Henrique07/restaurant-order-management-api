package restaurantApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestaurantApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApiApplication.class, args);
	}

}
//PATCH http://localhost:8080/orders/1/status

//http://localhost:8080/customers {
//  "id": 1,
//  "name": "Diego",
//  "phone": "123456"
//}

//http://localhost:8080/products
//{
//  "id": 1,
//  "name": "Pizza",
//  "price": 50.00
//}

//http://localhost:8080/orders
//{
//  "id": 1,
//  "customerId": 1,
//  "orderStatus": "CREATED",
//  "totalPrice": 0,
//  "createdAt": "2026-04-04T14:26:31.2916379"
//}


//git status
//git add .
