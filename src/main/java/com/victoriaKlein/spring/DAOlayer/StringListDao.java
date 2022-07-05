package com.victoriaKlein.spring.DAOlayer;

import com.victoriaKlein.spring.model.InputString;
import com.victoriaKlein.spring.model.InputSubString;
import com.victoriaKlein.spring.service.SubStringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.victoriaKlein.spring.model.TypeOfString.STRING;
import static com.victoriaKlein.spring.model.TypeOfString.SUBSTRING;

@Component
public class StringListDao {
    private final JdbcTemplate jdbcTemplate;
    List<String> wordList = new ArrayList<>();
    List<String> charactersList = new ArrayList<>();
    List<String> listOfResults;
    List <String> strings;
    List <String> substrings;
   Map<String, List> stringLists;
    @Autowired
    public StringListDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

public String createStr (InputString string){
    String listString;
    if(!string.getWord().isBlank()&&string.getType()== STRING){
wordList.add(string.getWord().toLowerCase().trim());
listString = String.join(", ", wordList);
    }
    else if (!string.getWord().isBlank()&&string.getType()== SUBSTRING){
        charactersList.add(string.getWord().toLowerCase().trim());
    listString = String.join(", ", charactersList);
    } else {
        listString = "Enter word";
    }
    return  listString;

}

public void cleanUp () {
wordList.clear();
charactersList.clear();
listOfResults.clear();
    System.out.println("cleaned " + wordList + charactersList);
    }
public void showResult(List<String> string, List<String> substring){
        strings= new ArrayList<>();
        substrings = new ArrayList<>();
        listOfResults = new ArrayList<>();
    stringLists = new HashMap<>();
    System.out.println("now is " + string +" " + substring);
    List <String> l = SubStringService.sortSubArray(string,substring);
    strings.add(String.join(", ", string));
    substrings.add(String.join(", ",substring));
    stringLists.put("string", string);
    stringLists.put("substring",substring);
        if (!l.isEmpty()){
            listOfResults.add(String.join(", ", l));
        } else {
            listOfResults.add("Any substring wasn't found in string");
        }
    stringLists.put("result", listOfResults);
}
public void show1 (){
    showResult(wordList,charactersList);
}
public void show2 (InputSubString inputSubString, InputString inputString) {
       showResult(Arrays.asList(inputString.getWordList().split(",")),Arrays.asList(inputSubString.getCharacterList().split(",")));
}
    //save into DB
    public void saveString (){
        if (!wordList.isEmpty()){
              jdbcTemplate.update("INSERT INTO input_string (strings) VALUES (?)", String.join(", ", wordList));
        }
        if (!charactersList.isEmpty()){
             jdbcTemplate.update("INSERT INTO input_substring (substrings) VALUES (?)", String.join(", ", charactersList));
        }
    }
    public List <InputString> showFromDB(){
        System.out.println(jdbcTemplate.query("SELECT * FROM input_string", new StringMapper()).get(0).getWordList());
        return jdbcTemplate.query("SELECT * FROM input_string", new StringMapper());

    }
    public List <InputSubString> showSubStringDB(){
        return jdbcTemplate.query("SELECT * FROM input_substring", new SubStringMapper());

    }
    public Map <String, List> showResultList () {
        return stringLists;
    }
}
