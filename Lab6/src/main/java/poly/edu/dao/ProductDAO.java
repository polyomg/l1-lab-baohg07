package poly.edu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.domain.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {
}


