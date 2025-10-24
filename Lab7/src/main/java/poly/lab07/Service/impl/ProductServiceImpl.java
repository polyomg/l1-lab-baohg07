package poly.lab07.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import poly.lab07.Entity.Product;
import poly.lab07.Entity.Report;
import poly.lab07.Repository.ProductRepository;
import poly.lab07.Service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getPrice(double minPrice, double maxPrice) {
        return productRepository.findByPrice(minPrice,maxPrice);
    }

    @Override
    public Page<Product> searchProducts(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (keyword == null) keyword = "";
        return productRepository.searchByKeyword(keyword, pageable);
    }
}
