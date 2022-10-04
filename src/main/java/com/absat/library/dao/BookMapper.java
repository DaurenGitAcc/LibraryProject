package com.absat.library.dao;

import com.absat.library.models.Book;
import com.absat.library.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        book.setYear(rs.getInt("year"));
        book.setCurrentReader(rs.getString("current_reader")==null?-1:rs.getInt("current_reader"));
        return book;
    }
}
