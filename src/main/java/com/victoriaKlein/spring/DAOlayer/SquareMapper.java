package com.victoriaKlein.spring.DAOlayer;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.victoriaKlein.spring.model.InputSquare;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SquareMapper implements RowMapper <InputSquare> {
//custom Mapper
    @Override
    public InputSquare mapRow(ResultSet rs, int rowNum) throws SQLException {
        InputSquare square= new InputSquare();
        Gson gson = new GsonBuilder().create();
        square.setId(rs.getInt("id"));
        square.setStringRepresantation(rs.getString("input_array"));
        square.setArray(gson.fromJson(rs.getString("input_array"), int [][].class));
        return square;
    }
}

