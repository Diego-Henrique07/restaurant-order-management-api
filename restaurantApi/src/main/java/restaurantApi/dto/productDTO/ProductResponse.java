package restaurantApi.dto.productDTO;
import java.math.BigDecimal;
public record ProductResponse(
        Long id,
        String name,
        BigDecimal price
) {



}
