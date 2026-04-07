package restaurantApi.dto.productDTO;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank
        String name,

        @NotNull
        BigDecimal price
) {
}
