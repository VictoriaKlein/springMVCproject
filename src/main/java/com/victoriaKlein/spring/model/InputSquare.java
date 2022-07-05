package com.victoriaKlein.spring.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputSquare {
    private int id;
    private int[] row0;
    private int[] row1;
    private int[] row2;
    private int[][] array;
    private String stringRepresantation;

    public String getStringRepresantation() {
        return stringRepresantation;
    }

    public void setStringRepresantation(String stringRepresantation) {
        this.stringRepresantation = stringRepresantation;
    }

    public int[][] getArray() {
        return array;
    }

    public void setArray(int[][] array) {
        this.array = array;
    }

    public int[] getRow0() {
        return row0;
    }

    public void setRow0(int[] row0) {
        this.row0 = row0;
    }

    public int[] getRow1() {
        return row1;
    }

    public void setRow1(int[] row1) {
        this.row1 = row1;
    }

    public int[] getRow2() {
        return row2;
    }

    public void setRow2(int[] row2) {
        this.row2 = row2;
    }

    public void addRow() {
        array=new int[3][3];
        array[0]=row0;
        array[1]=row1;
        array[2]=row2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
