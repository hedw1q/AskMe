package ru.hedw1q.AskMe.models;

import lombok.Data;

import javax.persistence.*;

/**
 * @author hedw1q
 */
@Entity
@Data
@Table(name = "AM_answer")
public class Answer extends BaseEntry {

    private int rating;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer() {
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
}
