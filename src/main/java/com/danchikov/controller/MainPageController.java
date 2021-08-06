package com.danchikov.controller;

import com.danchikov.entity.Book;
import com.danchikov.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
public class MainPageController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping({"/", "/mainPage"})
    public String loadAllBooks(Model model){
        List<Book> books = bookRepository.findAll();
        books.sort(((o1, o2) -> (int) (o1.getId() - o2.getId())));
//        List<File> files = createPathList(books);

        model.addAttribute("books",books);
        return "/mainPage";
    }

    /*public List<File> createPathList(List<Book> books){
        List<String> paths = new ArrayList<>();
        for(Book element : books){
            paths.add(new File(element.getImagePath()));
        }
        return files;
    }*/
}
