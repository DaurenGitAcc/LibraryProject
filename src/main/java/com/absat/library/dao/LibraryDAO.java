package com.absat.library.dao;

import com.absat.library.models.Book;
import com.absat.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LibraryDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LibraryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void savePerson(Person person){
        jdbcTemplate.update("INSERT INTO Person(name,year_of_birth) VALUES(?,?)",person.getName(),person.getYearOfBirth());
    }
    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM Person",new PersonMapper());
    }
    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",new Object[]{id},new PersonMapper()).stream().findAny().orElse(null);
    }
    public List<Book> personBooks(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE current_reader=?",new Object[]{id},new BookMapper());
    }
    public void deletePerson(int id){
        jdbcTemplate.update("DELETE FROM Person WHERE id=?",id);
    }
    public void updatePerson(Person person){
        jdbcTemplate.update("UPDATE Person SET name=?, year_of_birth=? WHERE id=?",person.getName(),person.getYearOfBirth(),person.getId());
    }

    public List<Book> indexBooks(){
        return jdbcTemplate.query("SELECT * FROM Book",new BookMapper());
    }
    public Book showBook(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?",new Object[]{id},new BookMapper()).stream().findAny().orElse(null);
    }
    public String isCurrentReader(int id){
        return jdbcTemplate.query("SELECT current_reader FROM Book WHERE id=?",new Object[]{id},new BeanPropertyRowMapper<>(String.class)).stream().findAny().orElse(null);
    }
    public void saveBook(Book book){
        jdbcTemplate.update("INSERT INTO Book(name,author,year) values(?,?,?)",book.getName(),book.getAuthor(),book.getYear());
    }
    public void updateBook(Book book){
        jdbcTemplate.update("UPDATE Book SET name=?,author=?,year=? WHERE id=?",book.getName(),book.getAuthor(),book.getYear(),book.getId());
    }
    public void setReader(int id, Person person){
        jdbcTemplate.update("UPDATE Book SET current_reader=? WHERE id=?",person.getId(),id);
    }
    public void releaseReader(int id){
        jdbcTemplate.update("UPDATE Book SET current_reader=? WHERE id=?",null,id);
    }
    public void deleteBook(int id){
        jdbcTemplate.update("DELETE FROM Book WHERE id=?",id);
    }

}
