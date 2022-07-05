package com.victoriaKlein.spring.DAOlayer;

import com.google.common.collect.Multimap;
import com.victoriaKlein.spring.model.InputSquare;
import com.victoriaKlein.spring.model.MagicSquare;
import com.victoriaKlein.spring.service.MagicSquareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SquareListDao {
    //   private static int TASK_COUNT;
    private final JdbcTemplate jdbcTemplate;
    private List<MagicSquare> magicSquareList;
    int [][] inputArr;
    private Multimap <String, int[][]> results;

    @Autowired
    public SquareListDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    //getting the list of all 3x3 magic square variants saved in db
    //and making comparison by calling methods from MagicSquareService
    public void getMagicSquareList(InputSquare inputSquare) {
        inputSquare.addRow();
        inputArr=inputSquare.getArray();
        magicSquareList = jdbcTemplate.query("SELECT * FROM magic_square", new MagicSquareMapper());
        results = MagicSquareService.convertFromStringToArray(magicSquareList, inputSquare.getArray());
    }

        public MagicSquare showSquare (int id){
        return jdbcTemplate.query("SELECT * FROM magic_square WHERE magsq_id=?", new Object[]{id}, new MagicSquareMapper())
                .stream().findAny().orElse(null);

    }

//Save into DB
    public void saveInputSquare (InputSquare square) {
        square.addRow();
        jdbcTemplate.update("INSERT INTO input_square (input_array) VALUES (?)", Arrays.deepToString(square.getArray()));
    }
    //show List from DB
    public List <InputSquare> uploadFromDB (){
            return jdbcTemplate.query("SELECT * FROM input_square", new SquareMapper());

    }
    public InputSquare  showSquare (InputSquare inputSquare){
        return jdbcTemplate.query("SELECT * FROM input_square WHERE id=?", new Object[]{inputSquare.getId()}, new SquareMapper())
                .stream().findAny().orElse(null);
    }
    public void useSquareFromDB(InputSquare inputSquare) {
        inputArr = showSquare(inputSquare).getArray();
        magicSquareList = jdbcTemplate.query("SELECT * FROM magic_square", new MagicSquareMapper());
        results = MagicSquareService.convertFromStringToArray(magicSquareList, inputArr);
    }
    //return inputArray and magicSquare for thymeleaf
    public Multimap <String, int[][]> showResult (){
        return results;
    }
    public int[][] getinputArray() {
        return inputArr;
    }
//further CRUD operations
public void edit (InputSquare square) {
   jdbcTemplate.update("UPDATE input_square SET input_array=? WHERE id=?",square.getStringRepresantation(), square.getId());
}

public void delete(int i) {
jdbcTemplate.update("DELETE FROM input_square WHERE id=?", i);
}
}
