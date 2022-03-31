package com.deepfake.news.forum.repositories;

import com.deepfake.news.forum.components.News;
import com.deepfake.news.forum.components.UserComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


@Repository
public interface NewsRepo extends JpaRepository<News, Long>{

    default Optional<News> getNewsWithGivenId(int id){
        return findAll().stream()
                .filter(c -> c.getNews_id()==id)
                .findFirst();
    }

    default List<UserComment> getAllCommentsByNewsId(int id){
        return findAll().stream()
                .filter(c -> c.getNews_id() == id)
                .map(News::getComments)
                .findAny()
                .orElse(new ArrayList<UserComment>());
    }

    default int findLastID(){
        return findAll().stream()
                .map(News::getNews_id)
                .max(Comparator.comparing(Integer::intValue))
                .orElse(1);
    }

    default boolean isThisNewsInDB(News news) {
        AtomicBoolean thereIs = new AtomicBoolean(false);
        findAll().stream()
                .filter(n-> n.getNews_id()==news.getNews_id() &&
                        n.getSourceURL().equals(news.getSourceURL())
                        )
                .findFirst()
                .ifPresent(n->
                        thereIs.set(true)
                );
        return thereIs.get();
    }

    default int countNumberOfNewses(){
        return findAll().size();
    }

    default Integer getTruthVotesOfNewsWithID(int id){
        AtomicInteger q = new AtomicInteger(-1);
        findAll().stream()
                .filter(news -> news.getNews_id()==id)
                .findFirst()
                .ifPresentOrElse((news) -> {
                    q.set(news.getRelevance_vote());
                }
                , ()->
                {
                    throw new RuntimeException();
                });
        return q.get();
    }

    default Integer getFakeVotesOfNewsWithID(int id){
        AtomicInteger q = new AtomicInteger(-1);
        findAll().stream()
                .filter(news -> news.getNews_id()==id)
                .findFirst()
                .ifPresentOrElse((news) -> {
                            q.set(news.getFake_vote());
                        }
                        , ()->
                        {
                            throw new RuntimeException();
                        });
        return q.get();
    }

    @Transactional
    @Modifying
    @Query("update News news set news.relevance_vote= :new_quantity where news.news_id = :news_id")
    void updateTruthVotes( @Param("news_id") int id, @Param("new_quantity") int nq);

    @Transactional
    @Modifying
    @Query("update News news set news.fake_vote= :new_quantity where news.news_id = :news_id")
    void updateFakeVotes( @Param("news_id") int id, @Param("new_quantity") int nq);

}
