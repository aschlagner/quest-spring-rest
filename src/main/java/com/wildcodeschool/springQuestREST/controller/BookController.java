package com.wildcodeschool.springQuestREST.controller;

import java.util.List;
import java.util.Map;

import com.wildcodeschool.springQuestREST.entity.Book;
import com.wildcodeschool.springQuestREST.repository.BookRespository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class BookController {

    @Autowired
    BookRespository BookRespository;

    @PostMapping("/books")
    public Book create(@RequestBody Book Book){
        if (Book.getTitle() == null || Book.getAuthor() == null || Book.getDescription() == null)
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        return BookRespository.save(Book);
    }

    @GetMapping("/books")
    public List<Book> index(){
        return BookRespository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book show(@PathVariable int id){
        return BookRespository.findById(id).get();
    }

    //TODO: Only first time running
    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        //return BookRespository.findByTitleContainingOrAuthorContainingOrDescriptionContaining(searchTerm, searchTerm, searchTerm);  // NotOK
        return BookRespository.findByTitleOrAuthorOrDescription(searchTerm, searchTerm, searchTerm);  // OK
    }

    @PutMapping("/books/{id}")
    public Book update(@PathVariable int id, @RequestBody Book Book){
        Book BookToUpdate = BookRespository.findById(id).get();
        if (Book.getTitle() != null) BookToUpdate.setTitle(Book.getTitle());
        if (Book.getAuthor() != null) BookToUpdate.setAuthor(Book.getAuthor());
        if (Book.getDescription() != null) BookToUpdate.setDescription(Book.getDescription());
        return BookRespository.save(BookToUpdate);
    }

    @DeleteMapping("/books/{id}")
    public boolean delete(@PathVariable int id){
        BookRespository.deleteById(id);
        return true;
    }

}
