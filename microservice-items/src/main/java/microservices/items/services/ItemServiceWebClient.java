package microservices.items.services;

import lombok.RequiredArgsConstructor;
import microservices.items.models.Item;
import microservices.items.models.ProductDTO;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.*;


@RequiredArgsConstructor
@Service
public class ItemServiceWebClient implements ItemService {

    private final WebClient.Builder webClient;

    @Override
    public List<Item> list() {
        return this.webClient.build().get()
                .uri("http://microservice-products/products/all")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(ProductDTO.class)
                .map(product -> new Item(product, new Random().nextInt(10) + 1))
                .collectList()
                .block();
    }

    @Override
    public Optional<Item> getById(Long id) {
        Map<String, Long> params = new HashMap<>();
        params.put("id", id);
        try {
            return this.webClient.build().get()
                    .uri("http://microservice-products/products/{id}", params)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(ProductDTO.class)
                    .map(product -> new Item(product, new Random().nextInt(10) + 1))
                    .blockOptional();
        } catch (WebClientResponseException e) {
            return Optional.empty();
        }
    }
}
