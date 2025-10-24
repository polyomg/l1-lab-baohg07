package poly.lab4.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import poly.lab4.model.Staff;

@Controller
@RequestMapping("/staff/create")
public class StaffController {
    @GetMapping("/form")
    public String createForm(Model model , @ModelAttribute("staff") Staff staff) {
        model.addAttribute("message", "Vui lòng nhập thông tin nhân viên");
        return "staff-create";
    }

    @PostMapping("/save")
    public String createSave(
            Model model,
            @Valid @ModelAttribute("staff") Staff staff, // <-- @Valid ở đây
            Errors errors,                                // <-- Errors/BidingResult phải ngay sau
            MultipartFile photoFile // file sau Errors
    ) {
        if (!errors.hasErrors()) {
            if (photoFile != null && !photoFile.isEmpty()) {
                staff.setPhoto(photoFile.getOriginalFilename());
            }
            model.addAttribute("message", "Dữ liệu đã nhập đúng. Xin chào " + staff.getFullName());
        } else {
            model.addAttribute("message", "Vui lòng sửa các lỗi sau!");
        }
        return "staff-create";
    }
}
