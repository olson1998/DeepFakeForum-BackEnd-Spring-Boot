package com.deepfake.news.forum.integrationTests;

import com.deepfake.news.forum.components.News;
import com.deepfake.news.forum.repositories.NewsRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestVoteController {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            APPLICATION_JSON.getType(),
            APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8
    );

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private NewsRepo nr;

    private MockMvc mockMvc;
    private ObjectMapper om;

    private int existingNewsID = 2;

    @Autowired
    private void setupOM() {
        om = new ObjectMapper();
    }

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testUpdateTruthVotes() throws Exception {
        int q = nr.getTruthVotesOfNewsWithID(existingNewsID);
        String body = om.writeValueAsString(existingNewsID);
        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/votes/increase/truth/")
                .contentType(APPLICATION_JSON_UTF8)
                .content(body)
        )
                .andDo(print())
                .andExpect(status().isOk());
        assertEquals(q+1, nr.getTruthVotesOfNewsWithID(existingNewsID));
    }
}
