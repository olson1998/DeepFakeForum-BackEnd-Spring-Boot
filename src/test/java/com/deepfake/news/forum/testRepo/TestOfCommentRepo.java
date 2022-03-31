package com.deepfake.news.forum.testRepo;

import com.deepfake.news.forum.components.UserComment;
import com.deepfake.news.forum.repositories.CommentsRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestOfCommentRepo {

    @Autowired
    private CommentsRepo cr;

    @Test
    public void testSaveNewComment(){
        UserComment comment = new UserComment();
        comment.setComment_id(0);
        comment.setCommenter_name("Tester");
        comment.setComment("Test comment");
        comment.setNews_id(2);
        cr.save(comment);
    }
}
