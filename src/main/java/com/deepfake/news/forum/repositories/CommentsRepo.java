package com.deepfake.news.forum.repositories;

import com.deepfake.news.forum.components.News;
import org.springframework.data.jpa.repository.JpaRepository;
import com.deepfake.news.forum.components.UserComment;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.Optional;

@Repository
public interface CommentsRepo extends JpaRepository<UserComment, Long> {

    default Optional<UserComment> getCommentsBodyByID(int comment_id){
        return findAll().stream()
                .filter(c-> c.getComment_id() == comment_id)
                .findFirst();
    }

    default int findLastID(){
        return findAll().stream()
                .map(UserComment::getComment_id)
                .max(Comparator.comparing(Integer::intValue))
                .orElse(1);
    }

}
