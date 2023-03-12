package com.web.ManageModule.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/elasticsearch")
public class ElasticsearchController {

    @GetMapping("/getwithoutparams")
    public void getwithoutparams(String city) throws IOException {

    }

}
