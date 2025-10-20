package poly.lab07.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import poly.lab07.Entity.Product;
import poly.lab07.Entity.Report;

import java.util.List;

public interface ProductService  {
    public List<Product> getAllProducts();

    public List<Product> getPrice(double minPrice, double maxPrice);

    Page<Product> searchProducts(String keyword, int page, int size);
}
