package com.deepfake.news.forum.controllers;

import com.deepfake.news.forum.components.News;
import com.deepfake.news.forum.service.NewsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin

@RestController
@RequestMapping("/news")
public class NewsController {

    private final NewsService ns;

    public NewsController(NewsService ns) {
        this.ns = ns;
    }

    @GetMapping("/")
    public List<News> loadNewses(){
        return ns.loadNewsesOnPage();
    }

    @PostMapping("/publish/")
    public void saveNewNews(@RequestBody News news){
        ns.saveNewNews(news);
    }

    @DeleteMapping("/delete/")
    public void deleteNews(@RequestBody News news){
        ns.deleteNews(news);
    }

}
