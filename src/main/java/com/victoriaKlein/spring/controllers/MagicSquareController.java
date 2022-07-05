package com.victoriaKlein.spring.controllers;

import com.google.common.collect.Multimap;
import com.victoriaKlein.spring.model.InputSquare;
import com.victoriaKlein.spring.DAOlayer.SquareListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/square")
public class MagicSquareController {
    SquareListDao squareListDao;

    @Autowired
    public MagicSquareController(SquareListDao squareListDao) {
        this.squareListDao = squareListDao;
    }

    @GetMapping()
    public String getSquaresFromDB(InputSquare inputSquare, Model model) {
        model.addAttribute("square", squareListDao.uploadFromDB());
        return "square/new";
    }

    @PostMapping()
    public String createInputSquare(Model model, InputSquare inputSquare) {
        squareListDao.getMagicSquareList(inputSquare);
        return "redirect:/square/result";
    }

    @PostMapping(params = "export")
    public String exportFromDB(InputSquare inputSquare) {
        squareListDao.useSquareFromDB(inputSquare);
        return "redirect:/square/result";
    }

    @PostMapping(params = "delete")
    public String delete(InputSquare inputSquare) {
        squareListDao.delete(inputSquare.getId());
        return "redirect:/square";
    }

    @PostMapping(params = "edit")
    public String chooseSquareToChange(Model model,InputSquare inputSquare) {
        model.addAttribute("squareToChange",squareListDao.showSquare(inputSquare));
        System.out.println(squareListDao.showSquare(inputSquare).getStringRepresantation());
        return "redirect:/square";
    }
    @PostMapping("edit")
    public String edit(InputSquare inputSquare) {
        squareListDao.edit(inputSquare);
        return "redirect:/square";
    }

    @GetMapping("/result")
    public String showResultes(Model model) {
        Multimap<String, int[][]> resultMap = squareListDao.showResult();
        model.addAttribute("square", squareListDao.getinputArray());
        model.addAttribute("number", resultMap.keys().stream().findFirst().get());
        model.addAttribute("resultMagicSquare", resultMap.values());
        return "square/showSquare";
    }

    @PostMapping(params = "save")
    public String saveInputToDB(InputSquare inputSquare) {
        squareListDao.saveInputSquare(inputSquare);
        return "square/new";
    }

}
