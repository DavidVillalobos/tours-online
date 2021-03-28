package com.getyourtours

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class Controller {

    @RequestMapping("/")
    fun index(): String {
        return "Welcome to main page of GetYourTours :D"
    }

    @RequestMapping("/about")
    fun about(): String {
        return "This is a app for tour reservation management!"
    }
}