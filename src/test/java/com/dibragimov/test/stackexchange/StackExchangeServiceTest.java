package com.dibragimov.test.stackexchange;

import com.dibragimov.test.stackexchange.dto.Query;
import com.dibragimov.test.stackexchange.dto.SearchResult;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StackExchangeServiceTest {
    @Autowired
    private StackExchangeService service;

    @Ignore
    @Test
    public void testSearch() {
        SearchResult result = service.search(new Query("Spring Boot", 1, 10));
        Assert.assertEquals(11, result.getTotalCount());
        result = service.search(new Query("Spring Boot", 2, 10));
        Assert.assertEquals(21, result.getTotalCount());
    }
}
