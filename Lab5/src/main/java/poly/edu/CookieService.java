package poly.edu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CookieService {
    @Autowired
    HttpServletRequest request;
    
    @Autowired
    HttpServletResponse response;

    /**
     * Đọc cookie từ request
     * @param name tên cookie cần đọc
     * @return đối tượng cookie đọc được hoặc null nếu không tìm thấy
     */
    public Cookie get(String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * Đọc giá trị cookie từ request
     * @param name tên cookie cần đọc
     * @return giá trị chuỗi đọc được hoặc chuỗi rỗng nếu không tìm thấy
     */
    public String getValue(String name) {
        Cookie cookie = get(name);
        return cookie != null ? cookie.getValue() : "";
    }

    /**
     * Tạo và gửi cookie đến client
     * @param name tên cookie
     * @param value giá trị cookie
     * @param hours thời gian hết hạn (tính bằng giờ)
     * @return đối tượng cookie đã tạo
     */
    public Cookie add(String name, String value, int hours) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(hours * 60 * 60); // Chuyển đổi giờ thành giây
        cookie.setPath("/");
        response.addCookie(cookie);
        return cookie;
    }

    /**
     * Xóa cookie khỏi client
     * @param name tên cookie cần xóa
     */
    public void remove(String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
