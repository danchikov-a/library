package com.danchikov.controller;

import com.danchikov.entity.Book;
import com.danchikov.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainPageController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping({"/", "/mainPage"})
    public String loadAllBooks(Model model){
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books",books);
        return "/mainPage";
    }
}
