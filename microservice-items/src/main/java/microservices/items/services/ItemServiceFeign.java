package microservices.items.services;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import microservices.items.http.OpenFeignClient;
import microservices.items.models.Item;
import microservices.items.models.ProductDTO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Primary
@Service
@RequiredArgsConstructor
public class ItemServiceFeign implements ItemService {
    private final OpenFeignClient feignClient;

    @Override
    public List<Item> list() {
        try {
            return feignClient.listProducts()
                    .stream()
                    .map(product -> new Item(product, new Random().nextInt(10) + 1))
                    .toList();
        } catch (FeignException e) {
            System.err.println("FeignClient Error: " + e.getMessage());
            throw new IllegalStateException("Products API is not available: " + e.getMessage(), e);
        }

    }


    @Override
    public Optional<Item> getById(Long id) {
        try {
            ProductDTO product = feignClient.productDetails(id);
            return Optional.of(new Item(product, new Random().nextInt(10) + 1));
        } catch (FeignException e) {
            return Optional.empty();
        }
    }
}
