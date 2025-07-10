package microservices.product.services;

import microservices.product.models.Product;
import microservices.product.repositories.ProductRepository;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final Environment environment;

    public ProductServiceImpl(ProductRepository productRepository, Environment environment) {
        this.environment = environment;
        this.repository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> list() {
        return ((List<Product>) repository.findAll()).stream()
                .map(product -> {
                    product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
                    return product;
                }).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> listPage(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> getById(Long id) {
        return repository.findById(id).map(product -> {
            String portValue = environment.getProperty("local.server.port", "0"); // Default to 0
            product.setPort(Integer.parseInt(portValue));
            return product;
        });
    }
}
