package com.dibragimov.test.stackexchange.dto;

import java.util.List;

/**
 * Search result dto
 */
public class SearchResult {
    private List<ResultQuestion> questions;
    private int totalCount;

    public SearchResult(List<ResultQuestion> questions, int totalCount) {
        this.questions = questions;
        this.totalCount = totalCount;
    }

    public List<ResultQuestion> getQuestions() {
        return questions;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
