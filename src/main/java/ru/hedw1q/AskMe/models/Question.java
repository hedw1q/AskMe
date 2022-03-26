package ru.hedw1q.AskMe.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author hedw1q
 * Simple entity object representing an question.
 */
@Entity
@Data
@Table(name = "AM_question")
public class Question extends BaseEntry {

    @NotEmpty(message = "Заголовок вопроса не должен быть пустым")
    private String title;
    private String tag;
    private int rating;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question", fetch = FetchType.EAGER)
    private List<Answer> answers;

    public Question() {
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void incrRating() {
        this.rating++;
    }

    public void decrRating() {
        this.rating--;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
        answer.setQuestion(this);
    }


}
