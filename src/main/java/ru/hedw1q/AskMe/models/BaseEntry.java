package ru.hedw1q.AskMe.models;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author hedw1q
 */
@MappedSuperclass
public class BaseEntry extends BaseEntity {

    private String author;
    @NotEmpty(message = "Введите тело")
    private String body;
    private LocalDateTime date;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return date.format(formatter);
    }


    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
