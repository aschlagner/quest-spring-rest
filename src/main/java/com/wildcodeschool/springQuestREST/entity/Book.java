package com.wildcodeschool.springQuestREST.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="title")
    private String title;
    private String author;
    private String description;

    public Book() {
    }

    public Book(String title, String author, String description) {
        this.setTitle(title);
        this.setAuthor(author);
        this.setDescription(description);
    }

    public Book(int id, String title, String author, String description) {
        this.setId(id);
        this.setTitle(title);
        this.setAuthor(author);
        this.setDescription(description);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book [author=" + author + ", description=" + description + ", id=" + id + ", title=" + title + "]";
    }

}
