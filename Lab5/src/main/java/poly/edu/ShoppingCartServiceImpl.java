package poly.edu;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    Map<Integer, Item> map = new HashMap<>();

    @Override
    public Item add(Integer id) {
        Item existing = map.get(id);
        if (existing != null) {
            existing.setQty(existing.getQty() + 1);
            return existing;
        }
        // Lấy thông tin mặt hàng thật từ "DB" và thêm vào giỏ
        Item source = DB.items.get(id);
        if (source == null) {
            return null;
        }
        Item item = new Item(source.getId(), source.getName(), source.getPrice(), 1);
        map.put(id, item);
        return item;
    }

    @Override
    public void remove(Integer id) {
        map.remove(id);
    }

    @Override
    public Item update(Integer id, int qty) {
        Item item = map.get(id);
        if (item == null) {
            return null;
        }
        if (qty <= 0) {
            map.remove(id);
            return null;
        }
        item.setQty(qty);
        return item;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Collection<Item> getItems() {
        return map.values();
    }

    @Override
    public int getCount() {
        return map.values().stream().mapToInt(Item::getQty).sum();
    }

    @Override
    public double getAmount() {
        return map.values().stream().mapToDouble(i -> i.getPrice() * i.getQty()).sum();
    }
}


