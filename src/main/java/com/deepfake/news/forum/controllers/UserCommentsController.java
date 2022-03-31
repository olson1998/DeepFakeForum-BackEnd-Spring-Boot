package com.deepfake.news.forum.controllers;

import com.deepfake.news.forum.components.UserComment;
import com.deepfake.news.forum.service.UserCommentsService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin

@RestController
@RequestMapping("/comments")
public class UserCommentsController {

    private final UserCommentsService ucs;

    public UserCommentsController(UserCommentsService ucs) {
        this.ucs = ucs;
    }

    @PostMapping(value = "/savenew/")
    public int saveNewComment(@RequestBody UserComment newUserComment){
        return ucs.saveNewComment(newUserComment);
    }

    @DeleteMapping("/delete/")
    public void deleteUserComment(@RequestBody UserComment comment){
        ucs.deleteUserComment(comment.getComment_id());
    }
}
