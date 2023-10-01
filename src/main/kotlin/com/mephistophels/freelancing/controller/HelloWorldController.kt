package com.mephistophels.freelancing.controller

import com.mephistophels.freelancing.util.API_PUBLIC
import com.mephistophels.freelancing.util.API_VERSION_1
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(API_PUBLIC)
class HelloWorldController {

    @GetMapping("/hello")
    fun getHelloWorld(): String {
        return "Hello, world!"
    }
}