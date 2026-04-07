package restaurantApi.service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import restaurantApi.entity.Customer;
import restaurantApi.repository.CustomerRepository;
import restaurantApi.dto.customerDTO.*;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public CustomerResponse create( CustomerRequest dto){
        Customer customer = new Customer();

        customer.setName(dto.name());
        customer.setNumber(dto.number());

        Customer savedCustomer = customerRepository.save(customer);

        return new CustomerResponse(
                savedCustomer.getId(),
                savedCustomer.getName(),
                savedCustomer.getNumber()
        );
    }

    public List<CustomerResponse> findAllCustomers(){
        return customerRepository.findAll()
                .stream()
                .map(customer -> new CustomerResponse(
                        customer.getId(),
                        customer.getName(),
                        customer.getNumber()
                )).toList();
    }

    public CustomerResponse findByIdCustomer(Long id){
        Customer customerById = customerRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer not found!"));

        return new CustomerResponse(
                customerById.getId(),
                customerById.getName(),
                customerById.getNumber()
        );
    }

    @Transactional
    public CustomerResponse update(Long id, CustomerRequest dto){
        Customer customerUpdate = customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer not found!"));

                customerUpdate.setName(dto.name());
                customerUpdate.setNumber(dto.number());

        Customer savedCustomer = customerRepository.save(customerUpdate);

        return new CustomerResponse(
                savedCustomer.getId(),
                savedCustomer.getName(),
                savedCustomer.getNumber()
        );
    }

    @Transactional
    public void remove(Long id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer not found!"));

        customerRepository.delete(customer);
    }
}
