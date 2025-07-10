package microservices.items.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private LocalDateTime createdAt;
    private int port;
}
