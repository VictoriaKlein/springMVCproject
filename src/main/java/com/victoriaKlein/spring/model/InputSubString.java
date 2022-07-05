package com.victoriaKlein.spring.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputSubString {
    private String characters;
    private int substringId;
    private String characterList;

    public String getCharacterList() {
        return characterList;
    }

    public void setCharacterList(String characterList) {
        this.characterList = characterList;
    }

    public int getSubstringId() {
        return substringId;
    }

    public void setSubstringId(int substringId) {
        this.substringId = substringId;
    }


    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }
}
