package com.deepfake.news.forum.servicesTests;

import com.deepfake.news.forum.service.NewsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NewsServiceTests {

    @Autowired
    private NewsService ns;

    @Test
    public void testCheckNewNewsUrl(){

    }
}
