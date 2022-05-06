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
import org.springframework.web.bind.annotation.RequestParam;
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

    // Bug: "In Spring Data JPA 2.6.3, an error occurs when the field Contains is queried for the second time"
    // https://github.com/spring-projects/spring-data-jpa/issues/2472
    // WORKAROUND: Using spring boot 2.6.2 version instead of the newest version to fix the problem.
    @GetMapping("/books")
    public List<Book> index(@RequestParam(name="search",required=false) String search) {
        if (search != null) {
            return BookRespository.findByTitleContainingOrDescriptionContaining(search, search);
        }
        return BookRespository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book show(@PathVariable int id){
        return BookRespository.findById(id).get();
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
