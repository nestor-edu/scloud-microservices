package microservices.product.services;

import microservices.product.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> list();
    Page<Product> listPage(PageRequest pageRequest);
    Optional<Product> getById(Long id);
}
