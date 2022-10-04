package com.absat.library.controllers;

import com.absat.library.dao.LibraryDAO;
import com.absat.library.models.Book;
import com.absat.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    private final LibraryDAO libraryDAO;

    @Autowired
    public BookController(LibraryDAO libraryDAO) {
        this.libraryDAO = libraryDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", libraryDAO.indexBooks());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id, @ModelAttribute("person") Person person) {
        Book book = libraryDAO.showBook(id);
        model.addAttribute("book",book);
        model.addAttribute("currentReader", null);
        if (book.getCurrentReader()!=-1) {
            model.addAttribute("currentReader", libraryDAO.show(book.getCurrentReader()));
        }
        model.addAttribute("people", libraryDAO.index());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String save(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "books/new";
        }
        libraryDAO.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book",libraryDAO.showBook(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "books/edit";
        }
        libraryDAO.updateBook(book);
        return "redirect:/books";
    }

    @PatchMapping("/release")
    public String release(@ModelAttribute("book") Book book) {
        libraryDAO.releaseReader(book.getId());
        return "redirect:/books";
    }

    @PatchMapping("/{id}/set")
    public String setReader(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        libraryDAO.setReader(id, person);
        return "redirect:/books";
    }

    @DeleteMapping()
    public String delete(@ModelAttribute("book") Book book) {
        libraryDAO.deleteBook(book.getId());
        return "redirect:/books";
    }


}
