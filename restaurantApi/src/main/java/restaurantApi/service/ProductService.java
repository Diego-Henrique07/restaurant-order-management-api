package restaurantApi.service;
import restaurantApi.entity.Product;
import restaurantApi.repository.ProductRepository;
import restaurantApi.dto.productDTO.*;
import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;


    @Transactional
    public ProductResponse createProduct(ProductRequest productRequest){
        if (productRepository.findByName(productRequest.name()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Product already exists.");
        }

        Product product = new Product();
        product.setName(productRequest.name());
        product.setPrice(productRequest.price());


        Product savedProduct = productRepository.save(product);

        return new ProductResponse(
            savedProduct.getId(),
            savedProduct.getName(),
            savedProduct.getPrice()
        );
    }

    public List<ProductResponse> findAllProduct(){
         return productRepository.findAll().stream()
                 .map(product -> new ProductResponse(
                         product.getId(),
                         product.getName(),
                         product.getPrice()
                 ))
                 .toList();
    }

    public ProductResponse findProductById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!"));

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }

    @Transactional
    public ProductResponse UpdateProduct (Long id,ProductRequest productRequest){
        Product update = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Product already exists!"));

        update.setName(productRequest.name());
        update.setPrice(productRequest.price());

        Product savedProductUpdate = productRepository.save(update);

        return new ProductResponse(
                savedProductUpdate.getId(),
                savedProductUpdate.getName(),
                savedProductUpdate.getPrice()
        );
    }
    @Transactional
    public void deleteProduct(Long id ){
        Product productExcluded = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!"));

        productRepository.delete(productExcluded);
    }



}
