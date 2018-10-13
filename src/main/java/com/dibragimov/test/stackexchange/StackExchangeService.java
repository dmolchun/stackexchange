package com.dibragimov.test.stackexchange;

import com.dibragimov.test.stackexchange.dto.Query;
import com.dibragimov.test.stackexchange.dto.QuestionStatus;
import com.dibragimov.test.stackexchange.dto.ResultQuestion;
import com.dibragimov.test.stackexchange.dto.SearchResult;
import com.google.code.stackexchange.client.query.SearchApiQuery;
import com.google.code.stackexchange.client.query.StackExchangeApiQueryFactory;
import com.google.code.stackexchange.schema.Paging;
import com.google.code.stackexchange.schema.Question;
import com.google.code.stackexchange.schema.StackExchangeSite;
import com.google.code.stackexchange.schema.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Service to work with Stack Exchange API
 */
@Service
public class StackExchangeService {
    @Value("${stackexchange.app.key}")
    private String appKey;
    @Value("${stackexchange.site}")
    private String site;

    /**
     * Async search
     */
    @Async
    public CompletableFuture<SearchResult> searchAsync(Query query) {
        return CompletableFuture.completedFuture(search(query));
    }

    /**
     * Search for questions
     * @param query - query object
     * @return instance of SearchResult class
     */
    public SearchResult search(Query query) {
        List<ResultQuestion> resultQuestions =
                findQuestions(query).stream()
                        .map(this::from)
                        .collect(Collectors.toList());
        int totalCount = (query.getPageNum() - 1) * query.getPageSize() + resultQuestions.size();
        return new SearchResult(resultQuestions, totalCount);
    }

    /**
     * Find questions using Stack Exchange API according to param
     * @param query - query object
     * @return list of Questions, found by query. Maximum size of list is pageSize + 1
     */
    private List<Question> findQuestions(Query query) {
        StackExchangeApiQueryFactory factory =
                StackExchangeApiQueryFactory.newInstance(appKey, StackExchangeSite.fromValue(site));
        final SearchApiQuery searchApiQuery = factory.newSearchApiQuery();

        return searchApiQuery
                .withInTitle(query.getTitle())
                .withPaging(new Paging(query.getPageNum(), query.getPageSize() + 1))
                .list();
    }

    /**
     * Transform com.google.code.stackexchange.schema.Question to com.dibragimov.test.stackexchange.dto.ResultQuestion
     */
    private ResultQuestion from(Question question) {
        return new ResultQuestion(
                question.getTitle(),
                question.getOwner().getDisplayName(),
                question.getLink(),
                question.getCreationDate(),
                question.getIsAnswered() ? QuestionStatus.ANSWERED : QuestionStatus.OPENED
        );
    }
}
