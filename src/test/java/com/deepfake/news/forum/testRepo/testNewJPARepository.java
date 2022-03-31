package com.deepfake.news.forum.testRepo;

import com.deepfake.news.forum.components.News;
import com.deepfake.news.forum.components.UserComment;
import com.deepfake.news.forum.repositories.NewsRepo;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class testNewJPARepository {

    @Autowired
    NewsRepo nr;

    private final int id = 1; //there is one 'Test' News with id = 1
    private Consumer<List<UserComment>> get;
    private final int notexistingID = -1;

    @Test
    public void testGetNewsById(){
        Optional<News> news = nr.getNewsWithGivenId(id);
        assertNotNull(news);
    }

    @Test
    public void testGetCommentsById(){
        List<UserComment> comments = nr.getAllCommentsByNewsId(id);
        if(!comments.isEmpty()){
            comments.forEach(c -> System.out.println(c.getComment()));
        }
        assertNotNull(comments);
    }

    @Test
    public void testGetCommentsOfNonExistingId(){
        List<UserComment> comments = nr.getAllCommentsByNewsId(notexistingID);
        if(!comments.isEmpty()){
            comments.forEach(c -> System.out.println(c.getComment()));
        }
        assertEquals(0, comments.size());
    }

    @Test
    public void testFindLastAddedNewsID(){
        int lastID = nr.findAll().stream()
                .map(News::getNews_id)
                .max(Comparator.comparingInt(Integer::intValue))
                .orElse(-1);
        assertEquals(lastID, nr.findLastID());
        assertNotEquals(-1, nr.findLastID());
    }

    /*@Test
    @Tag("test Get truth votes")
    public void testGetTruthVotes(){
        assertEquals(3, nr.getTruthVotesOfNewsWithID(1));
    }*/

    @Test
    public void testGetTruthVotesThrowingRuntimeException(){
        assertThrows(RuntimeException.class,()-> nr.getTruthVotesOfNewsWithID(-1));
    }

}
