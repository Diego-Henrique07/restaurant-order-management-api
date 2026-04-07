package restaurantApi.controller;
//import org.hibernate.mapping.List;
import restaurantApi.service.ProductService;
import restaurantApi.dto.productDTO.*;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import jakarta.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> create(
            @Valid @RequestBody ProductRequest request
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(request));

    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> findAll(){
        return ResponseEntity.ok(productService.findAllProduct());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(productService.findProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request
    ){
        return ResponseEntity.ok(productService.UpdateProduct(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
