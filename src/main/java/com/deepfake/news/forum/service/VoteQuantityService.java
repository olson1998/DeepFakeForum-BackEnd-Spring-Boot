package com.deepfake.news.forum.service;

import com.deepfake.news.forum.repositories.NewsRepo;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class VoteQuantityService {

    private final NewsRepo nr;

    public VoteQuantityService(NewsRepo nr) {
        this.nr = nr;
    }

    public void increaseVotesQuantity(int id, char type){
        if(givenIDExist(id)){
            int q = 0;
            if(type =='t'){
                q = nr.getTruthVotesOfNewsWithID(id);
                nr.updateTruthVotes(id, q+1);
            }
            if(type =='f'){
                q = nr.getFakeVotesOfNewsWithID(id);
                nr.updateFakeVotes(id, q+1);
            }
        }
        else{
            throw new RuntimeException();
        }
    }

    private boolean givenIDExist(int id){
        AtomicBoolean exist = new AtomicBoolean(false);
        nr.findAll().stream()
                .filter(news-> news.getNews_id() == id)
                .findFirst()
                .ifPresent(n-> {
                    exist.set(true);
                });
        return exist.get();
    }
}
