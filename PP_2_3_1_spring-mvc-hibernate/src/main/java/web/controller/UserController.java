package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.models.Users;
import web.service.UserServiceImpl;

import java.util.Optional;

@Controller
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String printUsers(ModelMap model) {
        model.addAttribute("users", userService.findAll());
        return "user-list";
    }

    @PostMapping("users/delete")
    public String deleteUser(@RequestParam(value = "id") Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping(value = "users/new")
    public String createUserForm(@ModelAttribute("user") Users user) {
        return "user-create";
    }

    @PostMapping("users/new")
    public String createUser(@ModelAttribute("user") Users user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String editUserForm(@RequestParam("id") Long id, Model model) {
        Optional<Users> userById = userService.findById(id);
        model.addAttribute("user", userById.get());
        return "edit-user";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") Users user) {
        userService.updateUser(user);
        return "redirect:/users";
    }
}
