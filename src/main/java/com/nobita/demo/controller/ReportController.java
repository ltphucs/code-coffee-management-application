package com.nobita.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/reports")
public class ReportController {
    @GetMapping
    public String showReportPage(){
        return "/report";
    }
}
