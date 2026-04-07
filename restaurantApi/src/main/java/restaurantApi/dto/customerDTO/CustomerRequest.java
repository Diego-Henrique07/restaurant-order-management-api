package restaurantApi.dto.customerDTO;

import jakarta.validation.constraints.*;

public record CustomerRequest(

        @NotBlank(message = "Name must not be empty")
        String name,

        @NotBlank(message = "phone must not be empty")
        String number

) {
}
