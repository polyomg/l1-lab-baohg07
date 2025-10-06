package poly.lab2.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import poly.lab2.Product.StoreProduct;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    public final List<StoreProduct> iteam = new ArrayList<>();

    public ProductController() {
        iteam.add(new StoreProduct("iPhone 100 Mắt", 9999));
        iteam.add(new StoreProduct("iPhone -10 Mắt", 9));
    }

    @GetMapping("/product/form")
    public String form(Model model){
        // Sản phẩm mẫu 1 có dữ liệu sẵn
        StoreProduct p1 = new StoreProduct("iPhone 17", 2000);
        model.addAttribute("storeProduct", p1);

        // Sản phẩm mẫu 2 khởi tạo rỗng để không hiển thị gì
        model.addAttribute("storeProduct2", new StoreProduct());

        return "form-bai4";
    }

    @PostMapping("/product/save")
    public String save(@ModelAttribute("storeProduct2") StoreProduct p , Model model){
        // Thêm sản phẩm mới vào danh sách
        iteam.add(p);

        // Giữ sản phẩm mẫu 1 để hiển thị
        StoreProduct p1 = new StoreProduct("iPhone 17", 2000);
        model.addAttribute("storeProduct", p1);

        // Hiển thị sản phẩm vừa nhập
        model.addAttribute("storeProduct2", p);

        return "form-bai4";
    }

    @ModelAttribute("items")
    public List<StoreProduct> items(){
        return iteam;
    }
}
