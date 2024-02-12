package noel.heart.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class TestController {

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/detail")
    public String detail(Model model) {
        return "shop-detail";
    }

    @GetMapping("/shop")
    public String shop(Model model) {
        return "shop";
    }
}
