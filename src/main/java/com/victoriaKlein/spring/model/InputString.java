package com.victoriaKlein.spring.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputString {
    private int stringId;

    public int getStringId() {
        return stringId;
    }

    public void setStringId(int stringId) {
        this.stringId = stringId;
    }

    private String word;
    private TypeOfString type;
    private String wordList;

    public String getWordList() {
        return wordList;
    }

    public void setWordList(String wordList) {
        this.wordList = wordList;
    }

    public TypeOfString getType() {
        return type;
    }

    public void setType(TypeOfString type) {
        this.type = type;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

}
