package apap.tutorial.emsidi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalTime;

@Controller
public class BaseController {

    @GetMapping("/")
    private String home(Model model) {
        LocalTime waktuSekarang = LocalTime.now();
        model.addAttribute("time",waktuSekarang);
        return "home";
    }

}
