package com.deepfake.news.forum.servicesTests;

import com.deepfake.news.forum.components.News;
import com.deepfake.news.forum.repositories.NewsRepo;
import com.deepfake.news.forum.service.VoteQuantityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VoteQuantityServiceTest {

    @Autowired
    private VoteQuantityService vqs;

    @Autowired
    private NewsRepo nr;

    /*@Test
    public void testUpdateTruthVotes(){
        int tested_news_id = 2;
        int q = nr.getTruthVotesOfNewsWithID(tested_news_id);
        News news = new News();
        news.setNews_id(tested_news_id);
        news.setRelevance_vote(q+1);
        vqs.updateTruthVotes(news);
        assertNotEquals(q, nr.getTruthVotesOfNewsWithID(tested_news_id));
    }*/
}
