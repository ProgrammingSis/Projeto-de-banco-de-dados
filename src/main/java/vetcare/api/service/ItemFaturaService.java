package vetcare.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vetcare.api.model.entities.ItemFatura;
import vetcare.api.repository.entities.ItemFaturaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemFaturaService {

    private final ItemFaturaRepository itemFaturaRepository;

    public ItemFatura getItemFaturaById(Long id) {
        return itemFaturaRepository.findById(id);
    }

    public List<ItemFatura> getAllItemFaturas() {
        return itemFaturaRepository.findAll();
    }

    public int addItemFatura(ItemFatura itemFatura) {
        return itemFaturaRepository.save(itemFatura);
    }

    public int updateItemFatura(ItemFatura itemFatura) {
        return itemFaturaRepository.update(itemFatura);
    }

    public int deleteItemFatura(Long id) {
        return itemFaturaRepository.deleteById(id);
    }
}
