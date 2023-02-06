package bg.softuni.mobilele.web;

import bg.softuni.mobilele.domain.dto.binding.UserRegisterFormDto;
import bg.softuni.mobilele.domain.dto.view.UserRoleViewDto;
import bg.softuni.mobilele.services.userRole.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController{
    private final UserRoleService userRoleService;

    @Autowired
    public UserController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping("/register")
    public ModelAndView getRegister(ModelAndView modelAndView) {
        List<UserRoleViewDto> roles = this.userRoleService.getAll();
        modelAndView.addObject("roles", roles);
        return super.view("auth-register", modelAndView);
    }

    @PostMapping("/register")
    public ModelAndView postRegister(UserRegisterFormDto userRegister) {
        return super.redirect("auth-login");
    }
}
