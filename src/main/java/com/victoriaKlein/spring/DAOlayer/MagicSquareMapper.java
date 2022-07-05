package com.victoriaKlein.spring.DAOlayer;

import com.victoriaKlein.spring.model.InputSquare;
import com.victoriaKlein.spring.model.MagicSquare;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MagicSquareMapper implements RowMapper<MagicSquare> {
    //custom Mapper
    @Override
    public MagicSquare mapRow(ResultSet rs, int rowNum) throws SQLException {
        MagicSquare square= new MagicSquare();
        square.setId(rs.getInt("magsq_id"));
         square.setMagicSquareVariant(rs.getString("magsq_variant"));
        return square;
    }
}
