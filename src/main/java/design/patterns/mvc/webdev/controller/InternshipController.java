package design.patterns.mvc.webdev.controller;

import design.patterns.mvc.webdev.model.Application;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InternshipController {

    @GetMapping("/apply")
    public String showForm() {
        return "apply"; // renders apply.html
    }

    @PostMapping("/apply")
    public String submitForm(@RequestParam String name,
                             @RequestParam String email,
                             @RequestParam String role,
                             Model model) {
        Application application = new Application(name, email, role);
        model.addAttribute("myapp", application);

        return "thankyou";
    }
}
