package com.esct.im_platform.controller.hello;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {
    public static void main(String[] args) {
        System.out.println("hello");
    }

    @GetMapping("/say")
    @ResponseBody
    public User sayHello(@RequestParam(name = "name") String name) {
        User user = new User();
        user.setName(name);
        return user;
    }
}
