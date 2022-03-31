package com.deepfake.news.forum.controllers;

import com.deepfake.news.forum.service.VoteQuantityService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin

@RestController
@RequestMapping("/votes")
public class VotesController {

    private final VoteQuantityService vqs;

    public VotesController(VoteQuantityService vqs) {
        this.vqs = vqs;
    }

    @PutMapping("/increase/truth/")
    public void increaseTruthVoteForNews(@RequestBody int id){
        vqs.increaseVotesQuantity(id, 't');
    }

    @PutMapping("/increase/fake/")
    public void increaseFakeVoteForNews(@RequestBody int id){
        vqs.increaseVotesQuantity(id, 'f');
    }
}
