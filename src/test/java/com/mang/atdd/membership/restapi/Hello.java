package com.mang.atdd.membership.restapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Controller
public class Hello {

    @GetMapping(value = "/hello", consumes = APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String test() {
        return "hello";
    }
}
