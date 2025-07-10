package microservices.items.http;

import microservices.items.models.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microservice-products", path = "/products")
public interface OpenFeignClient {

    @GetMapping("/all")
    List<ProductDTO> listProducts();

    @GetMapping("/{id}")
    ProductDTO productDetails(@PathVariable Long id);
}
