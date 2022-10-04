package com.absat.library.controllers;

import com.absat.library.dao.LibraryDAO;
import com.absat.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final LibraryDAO libraryDAO;
    @Autowired
    public PeopleController(LibraryDAO libraryDAO) {
        this.libraryDAO = libraryDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people",libraryDAO.index());
        return "people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person",libraryDAO.show(id));
        model.addAttribute("books",libraryDAO.personBooks(id));
        return "people/show";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "people/new";
    }

    @PostMapping()
    public String save(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "people/new";
        }
        libraryDAO.savePerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id,Model model){
        model.addAttribute("person",libraryDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "people/edit";
        }
        libraryDAO.updatePerson(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        libraryDAO.deletePerson(id);
        return "redirect:/people";
    }



}
