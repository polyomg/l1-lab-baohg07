package poly.edu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {
    @Autowired
    CookieService cookieService;
    
    @Autowired
    ParamService paramService;
    
    @Autowired
    SessionService sessionService;

    @GetMapping("/account/login")
    public String login1() {
        return "/account/login";
    }

    @PostMapping("/account/login")
    public String login2() {
        // Đọc các tham số từ form
        String username = paramService.getString("username", "");
        String password = paramService.getString("password", "");
        boolean remember = paramService.getBoolean("remember", false);
        
        // Kiểm tra đăng nhập (un="poly", pw="123")
        if ("poly".equals(username) && "123".equals(password)) {
            // Lưu username vào session
            sessionService.set("username", username);
            
            // Xử lý ghi nhớ tài khoản
            if (remember) {
                // Ghi nhớ tài khoản 10 ngày
                cookieService.add("user", username, 10 * 24); // 10 ngày = 10 * 24 giờ
            } else {
                // Xóa cookie tài khoản đã ghi nhớ trước đó
                cookieService.remove("user");
            }
            
            // Chuyển hướng đến trang chủ hoặc dashboard
            return "redirect:/account/success";
        }
        
        // Đăng nhập thất bại, quay lại trang login
        return "/account/login";
    }

    @GetMapping("/account/success")
    public String success() {
        return "/account/success";
    }
}
