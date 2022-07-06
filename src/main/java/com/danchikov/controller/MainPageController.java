package com.danchikov.controller;

import com.danchikov.entity.Book;
import com.danchikov.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainPageController {
    private static final String MAIN_PAGE_VIEW = "/mainPage";
    private final BookRepository bookRepository;

    public MainPageController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping({"/", MAIN_PAGE_VIEW})
    public String loadAllBooks(Model model){
        List<Book> books = bookRepository.findAll();
        books.sort(((o1, o2) -> (int) (o1.getId() - o2.getId())));
        model.addAttribute("books",books);
        return MAIN_PAGE_VIEW;
    }
}
