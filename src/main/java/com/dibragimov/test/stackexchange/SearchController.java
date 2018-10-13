package com.dibragimov.test.stackexchange;

import com.dibragimov.test.stackexchange.dto.Query;
import com.dibragimov.test.stackexchange.dto.SearchResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * Rest controller for Stack Exchange search
 */
@RestController
public class SearchController {

    private StackExchangeService stackExchangeService;

    public SearchController(StackExchangeService stackExchangeService) {
        this.stackExchangeService = stackExchangeService;
    }

    /**
     * Search method
     * @param query - object containing information about request
     */
    @RequestMapping(
            value = "/search",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json")
    public CompletableFuture<SearchResult> search(@RequestBody Query query) {
        return stackExchangeService.searchAsync(query);
    }
}
