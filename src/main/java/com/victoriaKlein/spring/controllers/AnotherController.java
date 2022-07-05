package com.victoriaKlein.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnotherController {
    @GetMapping("/calculator")
    public String bu (@RequestParam(value = "a", required = false) String c,
                      @RequestParam (value = "b", required = false) String d,
                      @RequestParam(value = "action", required = false) String action,
                      Model model){
        double res=0;
        int a = Integer.parseInt(c);
        int b = Integer.parseInt(d);
        switch (action) {
            case "addition":
                res = a + b;
                break;
            case "multiplication":
                res = a * b;
                break;
            case  "subtraction":
                res = a - b;
                break;
            case "division":
                res = a/(double)b;
                break;
            default:
                res = 0;
                break;
        }
        System.out.println(res);
        model.addAttribute("result", "Result: " + res);
        return "/calc/calculator";
    }
}
