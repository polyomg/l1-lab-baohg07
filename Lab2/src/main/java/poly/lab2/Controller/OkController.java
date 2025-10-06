package poly.lab2.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OkController {
    @GetMapping("/ok")
    public String ok() {
        return "ok";
    }

    @PostMapping("/ctrl/ok")
    public String m1(Model model) {
        model.addAttribute("msg", "Nút 1");
        return "ok";
    }

    @GetMapping("/ctrl/ok")
    public String m2(Model model) {
        model.addAttribute("msg", "Nút 2");
        return "ok";
    }

    @PostMapping(value = "/ctrl/ok" , params = "x=36") // Nên để tham số x cs 1 giá trị cho dễ hình dung
    public String m3(Model model) {
        model.addAttribute("msg", "Nút 3");
        return "ok";
    }
}
