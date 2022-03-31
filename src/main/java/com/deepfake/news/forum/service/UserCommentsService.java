package com.deepfake.news.forum.service;

import com.deepfake.news.forum.components.UserComment;
import com.deepfake.news.forum.repositories.CommentsRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommentsService {

    private final CommentsRepo cr;

    public UserCommentsService(CommentsRepo cr) {
        this.cr = cr;
    }

    public int saveNewComment(UserComment userComment){
        int id = cr.findLastID()+1;
        userComment.setComment_id(id);
        cr.save(userComment);
        return id;
    }

    public void deleteUserComment(int comment_idToDelete){
        Optional<UserComment> coll = cr.getCommentsBodyByID(comment_idToDelete);
        coll.ifPresent(c-> {
            cr.delete(coll.get());
        });
    }
}
