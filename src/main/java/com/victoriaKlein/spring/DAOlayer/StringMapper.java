package com.victoriaKlein.spring.DAOlayer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.victoriaKlein.spring.model.InputString;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class StringMapper implements RowMapper<InputString> {

    @Override
    public InputString mapRow(ResultSet rs, int rowNum) throws SQLException {
        InputString inputString= new InputString();
        Gson gson = new GsonBuilder().create();
        inputString.setStringId(rs.getInt("string_id"));
       inputString.setWordList(rs.getString("strings"));
        return inputString;
    }
}
