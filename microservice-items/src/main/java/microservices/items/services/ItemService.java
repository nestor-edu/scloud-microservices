package microservices.items.services;

import microservices.items.models.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    List<Item> list();
    Optional<Item> getById(Long id);
}
