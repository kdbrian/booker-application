package io.github.junrdev.booker.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tests")
public class TestController {


    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/no-auth")
    public String amATeaPot() {
        log.info("called");
        return "Hi, I am a tea pot.";
    }
}
