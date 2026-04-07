package restaurantApi.dto.customerDTO;

public record CustomerResponse(
        Long id,
        String name,
        String phone
) {
}
