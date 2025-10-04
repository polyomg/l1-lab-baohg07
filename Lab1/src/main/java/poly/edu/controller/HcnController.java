package poly.edu.controller;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HcnController {

    @GetMapping("/hcn/form")
    public String hcnForm(){
        return "hcn";
    }

    @PostMapping("/hcn/result")
    public String hcnResult(Model model, HttpServletRequest request){

        String dai = request.getParameter("chieu_dai");
        String rong = request.getParameter("chieu_rong");

        if (dai == null || dai.isEmpty() || rong == null || rong.isEmpty()) {
            model.addAttribute("message", "Vui lòng nhập đầy đủ!");
            return "hcn";
        }

        double chieu_dai = Double.parseDouble(dai);
        double chieu_rong = Double.parseDouble(rong);

        if (chieu_dai < chieu_rong) {
            model.addAttribute("message", "Dữ liệu không hợp lệ, Chiều rộng không được lớn hơn chiều dài!");
            return "hcn";
        }

        double chuvi = (chieu_dai + chieu_rong) * 2;
        double dientich = chieu_dai * chieu_rong;

        model.addAttribute("chuvi", "Chu Vi Hình Chữ Nhật Là: " + chuvi);
        model.addAttribute("dientich", "Diện Tích Hình Chữ Nhật Là: " + dientich);
        return "hcn";
    }
}
