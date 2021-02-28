package ru.hedw1q.AskMe.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author hedw1q
 * Simple entity object representing an question.
 */
@Entity
@Table(name = "AM_question")
public class Question extends BaseEntity{

    @Size(min=2, message = "Не меньше 2 знаков")
    private String title;
    @NotEmpty(message = "Введите тело вопроса")
    private String body;
    private String tag;
    private String author;
    private int rating;

    public Question() { }

    public int getRating() { return rating; }

    public void setRating(int rating) { this.rating = rating; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
