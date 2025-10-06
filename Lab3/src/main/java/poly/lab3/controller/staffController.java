package poly.lab3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.lab3.model.staff;

import java.util.List;

@Controller
public class staffController {
    @RequestMapping("/staff/detail")
    public String detail(Model model) {
        staff Staff = staff.builder()

                .id("user@gmail.com")
                .fullname("nguyễn văn user")
                .level(2)
                .build();

        model.addAttribute("staff", Staff);
        return "/staff-detail";
    }

    @RequestMapping("/staff/list")
    public String list(Model model) {
        List<staff> list = List.of(
                staff.builder().id("user1@gmail.com").fullname("nguyễn văn user1").level(0).build(),
                staff.builder().id("user2@gmail.com").fullname("nguyễn văn user2").level(1).build(),
                staff.builder().id("user3@gmail.com").fullname("nguyễn văn user3").level(2).build(),
                staff.builder().id("user4@gmail.com").fullname("nguyễn văn user4").level(2).build(),
                staff.builder().id("user5@gmail.com").fullname("nguyễn văn user5").level(1).build(),
                staff.builder().id("user6@gmail.com").fullname("nguyễn văn user6").level(0).build());
        model.addAttribute("list", list);
        return "/staff-list";
    }

    @RequestMapping("/staff/list2")
    public String list2(Model model) {
        List<staff> list = List.of(
                staff.builder().id("user1@gmail.com").fullname("nguyễn văn user1").level(0).build(),
                staff.builder().id("user2@gmail.com").fullname("nguyễn văn user2").level(1).build(),
                staff.builder().id("user3@gmail.com").fullname("nguyễn văn user3").level(2).build(),
                staff.builder().id("user4@gmail.com").fullname("nguyễn văn user4").level(2).build(),
                staff.builder().id("user5@gmail.com").fullname("nguyễn văn user5").level(1).build(),
                staff.builder().id("user6@gmail.com").fullname("nguyễn văn user6").level(0).build());
        model.addAttribute("list", list);
        return "/table";
    }

    @RequestMapping("/staff/list5")
    public String list5(Model model) {
        List<staff> list = List.of(
                staff.builder().id("user1@gmail.com").fullname("nguyễn văn user1").level(0).build(),
                staff.builder().id("user2@gmail.com").fullname("nguyễn văn user2").level(1).build(),
                staff.builder().id("user3@gmail.com").fullname("nguyễn văn user3").level(2).build(),
                staff.builder().id("user4@gmail.com").fullname("nguyễn văn user4").level(2).build(),
                staff.builder().id("user5@gmail.com").fullname("nguyễn văn user5").level(1).build(),
                staff.builder().id("user6@gmail.com").fullname("nguyễn văn user6").level(0).build()
        );
        model.addAttribute("list", list);
        return "/list-controls";
    }
}
