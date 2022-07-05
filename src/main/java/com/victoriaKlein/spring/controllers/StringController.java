package com.victoriaKlein.spring.controllers;

import com.victoriaKlein.spring.DAOlayer.StringListDao;
import com.victoriaKlein.spring.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/string")
public class StringController {
    StringListDao stringListDao;
    @Autowired
    public StringController(StringListDao stringListDao) {
        this.stringListDao = stringListDao;
    }
@GetMapping()
public String newString (@ModelAttribute("inputString") InputString inputString) {
    return "string/newString";
}
@PostMapping()
    public String createStr (Model model, InputString inputString) {
        if(!inputString.getWord().isBlank())
model.addAttribute("words" , stringListDao.createStr(inputString));
        else
            model.addAttribute("words", "EMPTY STRING");
        return "string/newString";
    }

@GetMapping("/result")
    public String showResult (){
stringListDao.show1();
    return "redirect:/string/showResult";
}
    @PostMapping("/export")
    public String addFromDBandShowResult(Model model,InputSubString inputSubString, InputString inputString){
        stringListDao.show2(inputSubString,inputString);
        return "redirect:/string/showResult";
    }
@GetMapping("/showResult")
    public String showResult (Model model){
    Map<String, List> m = stringListDao.showResultList();
    model.addAttribute("strings", m.get("string"));
    model.addAttribute("substrings", m.get("substring"));
    model.addAttribute("results", m.get("result"));
        return "string/showString";
    }
    @GetMapping("/saveString")
    public String save () {
        stringListDao.saveString();
        return "redirect:/string";
    }
    @GetMapping("/export")
    public String exportDB (Model model, InputString inputString, InputSubString inputSubString){
model.addAttribute("exportString",stringListDao.showFromDB());
        model.addAttribute("exportSubString",stringListDao.showSubStringDB());
        return "string/newString";
    }

@GetMapping("/clean")
public String cleanStrings () {
    stringListDao.cleanUp();
    return "redirect:/string";
}
}
