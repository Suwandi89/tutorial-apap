package apap.tutorial.emsidi.controller;

import apap.tutorial.emsidi.model.RoleModel;
import apap.tutorial.emsidi.model.UserModel;
import apap.tutorial.emsidi.service.RoleService;
import apap.tutorial.emsidi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/add")
    private String addUserFormPage(Model model){
        UserModel user = new UserModel();
        List<RoleModel> listRole = roleService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("listRole",listRole);
        return "form-add-user";
    }

    @PostMapping(value = "/add")
    private String addUserSubmit(@ModelAttribute UserModel user, Model model){
        userService.addUser(user);
        model.addAttribute("user", user);
        return "redirect:/";
    }

    @GetMapping("/viewall")
    public String listUser(Model model){
        List<UserModel> listUser = userService.getUserList();
        boolean adaUser = false;
        if (listUser.size()>0){
            adaUser = true;
        }
        model.addAttribute("adaUser", adaUser);
        model.addAttribute("listUser", listUser);
        return "viewall-user";
    }

    @GetMapping("/delete/{username}")
    public String deleteUserSubmit(
            @PathVariable(value = "username", required = true)  String username
    ) {
        userService.deleteUserByUsername(username);
        return "viewall-user";
    }

    @GetMapping("/updatePassword")
    public String formUpdatePassword(Model model){
        return "form-update-password";
    }

    @PostMapping("/updatePassword")
    public String updatePasswordSubmit(@ModelAttribute UserModel userAwal, String username, String password, String password1, String password2, Model model){
        UserModel user = userService.getUserByUsername(username);
        if (userService.matchPassword(user.getPassword(), password)){
            if(password1.equals(password2)){
                userService.updatePassword(user, password1);
            } else {
                model.addAttribute("fail", true);
            }
        } else {
            model.addAttribute("fail", true);
        }
        return "home";
    }
}
