package com.dibragimov.test.stackexchange.dto;

import java.util.Date;

/**
 * Contains information about question from Stack Exchange
 */
public class ResultQuestion {
    private String title;
    private String author;
    private String url;
    private Date date;
    private QuestionStatus status;

    public ResultQuestion(String title, String author, String url, Date date, QuestionStatus status) {
        this.title = title;
        this.author = author;
        this.url = url;
        this.date = date;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public Date getDate() {
        return date;
    }

    public QuestionStatus getStatus() {
        return status;
    }
}
