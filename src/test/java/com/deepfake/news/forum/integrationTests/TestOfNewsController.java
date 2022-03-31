package com.deepfake.news.forum.integrationTests;

import com.deepfake.news.forum.components.News;
import com.deepfake.news.forum.repositories.NewsRepo;
import com.deepfake.news.forum.service.NewsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class TestOfNewsController {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            APPLICATION_JSON.getType(),
            APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8
    );

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private NewsService ns;

    private MockMvc mockMvc;
    private ObjectMapper om;

    @Autowired
    private void setupOM() {
        om = new ObjectMapper();
    }

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    @Tag("Test load comments from DB")
    public void testLoadNewses() throws Exception {
        ns.setPage(1);
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/news/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /*@Test
    public void testSaveNewPost() throws Exception {

        Random generator = new Random();

        for(int i =1; i < 666; i++){
            int rr = generator.nextInt(1222);
            int rf = generator.nextInt(2222);
            News news = new News();
            news.setName("test" + i);
            news.setSourceURL("https://testnews.pl/"+i);
            news.setRelevance_vote(rr);
            news.setFake_vote(rf);
            news.setPublishing_date(Timestamp.valueOf(LocalDateTime.now()));
            news.setComments(new ArrayList<>());
            String body = om.writeValueAsString(news);
            mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/news/publish/")
                    .contentType(APPLICATION_JSON_UTF8)
                    .content(body)
            ).andExpect(status().isOk());
        }
    }*/

}
