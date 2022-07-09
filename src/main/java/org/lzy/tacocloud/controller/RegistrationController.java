package org.lzy.tacocloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.lzy.tacocloud.data.UserRepository;
import org.lzy.tacocloud.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Resource
    private PasswordEncoder encoder;

    @Resource
    private UserRepository userRepository;

    @GetMapping
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping
    public String processRegistration(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info("User registration: " + user);
        return "redirect:/login";
    }
}
