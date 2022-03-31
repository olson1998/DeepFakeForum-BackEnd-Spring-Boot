package com.deepfake.news.forum.service;
import com.deepfake.news.forum.components.News;
import com.deepfake.news.forum.repositories.CommentsRepo;
import com.deepfake.news.forum.repositories.NewsRepo;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class NewsService {

    private final NewsRepo nr;
    private final CommentsRepo cr;
    private int page;
    private int quantity_newses_on_page;

    public NewsService(NewsRepo nr, CommentsRepo cr) {
        this.cr=cr;
        this.nr = nr;
        this.page = 1;
        this.quantity_newses_on_page = 10;
    }

    public List<News> loadNewsesOnPage(){
        int first = (page-1)*quantity_newses_on_page;
        int last = first + quantity_newses_on_page;
        if(last>nr.countNumberOfNewses()){last=nr.countNumberOfNewses()-1;}
        return nr.findAll().stream()
                .sorted(Comparator.comparingLong(news->
                        -1*(news.getRelevance_vote()+news.getFake_vote()+60*3*news.getComments().size())+(
                                16*60*60- ChronoUnit.SECONDS.between(
                                LocalDateTime.now(),
                                news.getPublishing_date().toLocalDateTime()
                                )/10
                        )
                        )
                )
                //sorted(Comparator.comparing(News::getPublishing_date).reversed())
                .collect(Collectors.toList())
                .subList(first, last);
    }

    public void saveNewNews(News news){
        int lastID = nr.findLastID();
        if(uniquePostedNewsURL(news)){
            news.setNews_id(lastID+1);
            news.setPublishing_date(Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault()))
            );
            nr.save(news);
        }
    }

    public void deleteNews(News news){
        if(nr.isThisNewsInDB(news)){
            nr.delete(news);
        }
        news.getComments().forEach(cr::delete);
    }

    private boolean uniquePostedNewsURL(News news) {
        AtomicBoolean found = new AtomicBoolean(true);
        nr.findAll().stream()
                .map(News::getSourceURL)
                .filter(news.getSourceURL()::equals)
                .findFirst()
                .ifPresent(url -> found.set(false));
        return found.get();
    }

   public int countPagesQuantity(){
        int number_of_newses = nr.countNumberOfNewses();
        int newses_on_last_page = number_of_newses % (quantity_newses_on_page);
        int additional_pages = 1;
       System.out.println("newes on last page: "+ newses_on_last_page);
        if(newses_on_last_page ==0){additional_pages=0;}
       return additional_pages+ ( (number_of_newses - newses_on_last_page )  / (quantity_newses_on_page));
   }

    public void setPage(int page) {
        this.page = page;
    }

    public void setQuantity_newses_on_page(int quantity_newses_on_page) {
        this.quantity_newses_on_page = quantity_newses_on_page;
    }

    public int getPage() {
        return page;
    }
}
