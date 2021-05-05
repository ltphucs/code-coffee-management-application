package com.nobita.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/reports")
public class ReportController {
    @GetMapping
    public String showReportPage() {
        return "/report";
    }

    @GetMapping(value = "/bills")
    public String showReportProduct() {
        return "report-bill";
    }

    @GetMapping(value = "/stores")
    public String showReportStore() {
        return "/report-store";
    }

    @GetMapping(value = "/ingredients")
    public String showReportOrder() {
        return "report-ingredient";
    }

    @GetMapping(value = "/products")
    public String showReportIngredient() {
        return "report-product";
    }

}
