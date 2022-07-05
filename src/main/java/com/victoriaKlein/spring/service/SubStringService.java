package com.victoriaKlein.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubStringService {
    public static List <String> sortSubArray (List <String> inputString, List <String> inputSubstring){
        ArrayList<String> resList = new ArrayList<>();
        String resultString;
        for (int i = 0; i< inputString.size(); i++) {
            for (int j=0; j< inputSubstring.size(); j++) {
                if(inputString.get(i).contains(inputSubstring.get(j))) {
                    resList.add(inputSubstring.get(j));
                }
            }
        }
        return resList.stream().distinct().collect(Collectors.toList());
    }
}
