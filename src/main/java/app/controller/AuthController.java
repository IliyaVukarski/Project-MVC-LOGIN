package app.controller;

import app.models.RegisterUserModel;
import app.models.RegisterUserServiceModel;
import app.services.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import app.services.AuthServiceImp;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class AuthController {
    private final AuthService authService;
    private final ModelMapper mapper;

    public AuthController(AuthService authService, ModelMapper mapper) {
        this.authService = authService;
        this.mapper = mapper;
    }

    @GetMapping("/login")
    public ModelAndView getLoginForm() {
        return new ModelAndView("login");
    }

    @GetMapping("/register")
    public ModelAndView getRegisterForm() {
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterUserModel model) {
        RegisterUserServiceModel serviceModel = mapper.map(model, RegisterUserServiceModel.class);
        authService.register(serviceModel);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute RegisterUserModel model, HttpSession session) throws  Exception {
        RegisterUserServiceModel serviceModel = mapper.map(model, RegisterUserServiceModel.class);
        try {
            authService.login(serviceModel);
            session.setAttribute("username", serviceModel.getUsername());
            return "redirect:/";
        }catch(Exception ex) {
            return "login";
        }
    }
}
