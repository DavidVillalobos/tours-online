package com.getyourtours

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tours")
class ControllerTours {
    @RequestMapping("")
    fun index(): String {
        return "Welcome to page Tours!"
    }

    @RequestMapping("/about")
    fun about(): String {
        return "This is a controller for tours"
    }
}