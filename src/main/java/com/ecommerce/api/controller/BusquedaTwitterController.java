package com.ecommerce.api.controller;

import com.ecommerce.api.service.BusquedaTwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BusquedaTwitterController {
    @Autowired
    BusquedaTwitterService service;

    @GetMapping("/search")
    public String searchTweets(@RequestParam String recent) {
        return service.getTwitter(recent);
    }
}
