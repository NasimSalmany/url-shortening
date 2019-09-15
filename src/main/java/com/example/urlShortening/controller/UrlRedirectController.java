package com.example.urlShortening.controller;

import com.example.urlShortening.services.ShortenUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Nasim Salmany
 */

@Controller
@RequestMapping("/tiny")
public class UrlRedirectController {

    @Autowired
    private ShortenUrlService urlService;

    @GetMapping("/{shortenStr}")
    public ModelAndView redirectToOriginalUrl(@PathVariable("shortenStr") String shortenStr) {
        return urlService.getShortenUrl(shortenStr)
                .map(shortenUrl -> new ModelAndView("redirect:" + shortenUrl.getOriginalUrl()))
                .orElseGet(() -> new ModelAndView("tiny_not_found"));
    }

}
