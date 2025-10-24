package poly.lab07.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import poly.lab07.Entity.Product;
import poly.lab07.Entity.Report;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    //  Cho bài 1
    @Query("FROM Product o WHERE o.price BETWEEN ?1 AND ?2")
    List<Product> findByPrice(double minPrice, double maxPrice);

    //  Cho bài 2
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword%")
    Page<Product> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    //  Cho bài 4
    List<Product> findByPriceBetween(double min, double max);

    // Cho bài 5
    Page<Product> findAllByNameLike(String keywords, Pageable pageable);

    @Query(value = "SELECT c.name AS group_name, SUM(p.price) AS total_sum, COUNT(p.id) AS total_count " +
            "FROM category c " +
            "JOIN category_product cp ON c.id = cp.ctid " +
            "JOIN product p ON cp.pdid = p.id " +
            "GROUP BY c.name " +
            "ORDER BY SUM(p.price) DESC",
            nativeQuery = true)
    List<Report> getInventoryByCategory();

}
