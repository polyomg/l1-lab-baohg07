package poly.lab07.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.lab07.Service.ReportService;

@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/inventory-by-category")
    public String inventory(Model model) {
        var items = reportService.getInventoryByCategory();
        model.addAttribute("items", items);
        return "inventory-by-category";
    }

}