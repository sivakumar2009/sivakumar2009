package com.demantiaapp.auth.web;

import com.demantiaapp.auth.model.Notifications;
import com.demantiaapp.auth.model.User;
import com.demantiaapp.auth.repository.NotificationsRepository;
import com.demantiaapp.auth.repository.UserRepository;
import com.demantiaapp.auth.service.NotificationsService;
import com.demantiaapp.auth.service.SecurityService;
import com.demantiaapp.auth.service.UserService;
import com.demantiaapp.auth.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationsRepository notificationsRepository;

    @Autowired
    private NotificationsService notificationsService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping({"/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }

    @GetMapping("/getNotifications")
    public ResponseEntity<List<Notifications>> getNotifications() {
        Timestamp original = new Timestamp(new Date().getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(original.getTime());
        cal.add(Calendar.SECOND, 5);
        Timestamp later = new Timestamp(cal.getTime().getTime());
        return ResponseEntity.ok((List<Notifications>) notificationsRepository.findByTimestampIsBetween(original, later));
    }

    @PostMapping("/putNotifications")
    public ResponseEntity<Notifications> putNotifications(@RequestBody Notifications notifications) {
        Notifications addedNotifications = notificationsService.addNotifications(notifications);
        return new ResponseEntity<>(addedNotifications, HttpStatus.CREATED);
    }
}
