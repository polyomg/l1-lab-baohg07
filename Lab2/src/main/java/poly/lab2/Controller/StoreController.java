package poly.lab2.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import poly.lab2.Product.StoreProduct;

@Controller
public class StoreController {
    @GetMapping("/store/form")
    public String form() {
        return "store";
    }

    @PostMapping("/store/save")
    public String save(@ModelAttribute("product") StoreProduct product , Model model) {
        model.addAttribute("product", product);
        return "store";
    }
}
