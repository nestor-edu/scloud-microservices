package microservices.items.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {
    private ProductDTO product;
    private int quantity;

    public Double getTotal() {
        return product.getPrice() * quantity;
    }
}
