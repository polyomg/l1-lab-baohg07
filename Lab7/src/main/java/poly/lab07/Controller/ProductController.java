package poly.lab07.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import poly.lab07.Entity.Product;
import poly.lab07.Service.ProductService;
import poly.lab07.Service.SessionService;

import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SessionService session;

    // --- Trang hiển thị danh sách sản phẩm cơ bản ---
    @GetMapping("/products")
    public String Product(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products-b1";
    }

    // --- Trang hiển thị tìm kiếm + phân trang ---
    @GetMapping("/products-b2")
    public String Productb2(Model model) {
        // khi mới vào trang lần đầu, hiển thị tất cả
        Page<Product> page = productService.searchProducts("", 0, 5);
        model.addAttribute("page", page);
        model.addAttribute("keywords", "");
        return "search-and-page";
    }

    // --- Lọc theo khoảng giá (chức năng bài 1) ---
    @PostMapping("/products/search")
    public String searchProduct(Model model,
                                @RequestParam double min,
                                @RequestParam double max) {
        model.addAttribute("products", productService.getPrice(min, max));
        model.addAttribute("min", min);
        model.addAttribute("max", max);
        return "products-b1";
    }

    // --- Tìm kiếm + phân trang (POST từ form) ---
    @PostMapping("/search-and-page")
    public String searchAndPage(Model model,
                                @RequestParam("keywords") Optional<String> kw,
                                @RequestParam("p") Optional<Integer> p) {

        String keyword = kw.orElse(session.get("keywords", ""));
        session.set("keywords", keyword);

        int currentPage = p.orElse(0);
        int pageSize = 5;

        Page<Product> page = productService.searchProducts(keyword, currentPage, pageSize);
        model.addAttribute("page", page);
        model.addAttribute("keywords", keyword);

        return "search-and-page";
    }

    // --- Hỗ trợ GET để bấm link phân trang ---
    @GetMapping("/search-and-page")
    public String searchAndPageGet(Model model,
                                   @RequestParam("keywords") Optional<String> kw,
                                   @RequestParam("p") Optional<Integer> p) {
        return searchAndPage(model, kw, p);
    }
}
