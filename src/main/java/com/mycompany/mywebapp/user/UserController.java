package com.mycompany.mywebapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> users = service.listAll();
        System.out.println(users);
        model.addAttribute("listUsers", users);
        return "users";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user){
        service.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model){
        try {
            User user = service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User with ID: " + id);
            return "user_form";
        } catch (Exception ex) {
            return "redirect:/users";
        }
    }
}
