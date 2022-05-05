package com.wildcodeschool.springQuestREST.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.wildcodeschool.springQuestREST.entity.Book;

@Repository
public interface BookRespository extends JpaRepository<Book, Integer> {

    // TODO
    List<Book> findByTitleContainingOrAuthorContainingOrDescriptionContaining(String text, String textAgain, String textAgainAgain);
    List<Book> findByTitleOrAuthorOrDescription(String text, String textAgain, String textAgainAgain);
}
