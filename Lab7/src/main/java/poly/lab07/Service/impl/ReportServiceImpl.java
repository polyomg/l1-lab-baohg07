package poly.lab07.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.lab07.Entity.Report;
import poly.lab07.Repository.ProductRepository;
import poly.lab07.Service.ReportService;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Report> getInventoryByCategory() {
        return productRepository.getInventoryByCategory();
    }
}
