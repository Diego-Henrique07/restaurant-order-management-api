package restaurantApi.controller;

import org.springframework.http.*;
import restaurantApi.service.CustomerService;
import restaurantApi.dto.customerDTO.*;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> create(
            @Valid @RequestBody CustomerRequest request
            ){
            CustomerResponse customerResponse = customerService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(customerResponse);
    }

    @GetMapping()
    public ResponseEntity<List<CustomerResponse>> findAll(){
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(customerService.findByIdCustomer(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequest request
    ){
        return ResponseEntity.ok(customerService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete( @PathVariable Long id){
        customerService.remove(id);
        return ResponseEntity.noContent().build();
    }
}