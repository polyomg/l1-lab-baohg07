package poly.edu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.domain.Category;

public interface CategoryDAO extends JpaRepository<Category, String> {
}


