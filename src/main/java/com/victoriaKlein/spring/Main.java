package com.victoriaKlein.spring;

import com.victoriaKlein.spring.model.InputSquare;
import com.victoriaKlein.spring.service.MagicSquareService;
import com.victoriaKlein.spring.service.SubStringService;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
       // System.out.println(new TaskListDao().showTask(1).getDesc());
//        System.out.println(new MagicSquareService());
//        new MagicSquareService().convertToSring();
      //  new StringListDao();
new InputSquare().addRow();
    }
    public static String getActualPublishDate (Integer timestamp){
        Date d = new Date((long)timestamp*1000);
        System.out.println("date " + d.toString());
        return d.toString();
    }

}
