package com.victoriaKlein.spring.DAOlayer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.victoriaKlein.spring.model.InputString;
import com.victoriaKlein.spring.model.InputSubString;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class SubStringMapper implements RowMapper<InputSubString> {

    @Override
    public InputSubString mapRow(ResultSet rs, int rowNum) throws SQLException {
        InputSubString inputSubString = new InputSubString();
        Gson gson = new GsonBuilder().create();
        inputSubString.setSubstringId(rs.getInt("substring_id"));
        inputSubString.setCharacterList(rs.getString("substrings"));
        return inputSubString;
    }
}
