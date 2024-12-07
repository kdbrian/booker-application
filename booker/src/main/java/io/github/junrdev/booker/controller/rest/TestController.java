package io.github.junrdev.booker.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/test")
    public String amATeaPot() {
        return "Hi, I am a tea pot.";
    }
}
