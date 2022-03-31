package com.deepfake.news.forum.controllers;

import com.deepfake.news.forum.service.NewsService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin

@RestController
@RequestMapping("/settings")
public class PageSettingsController {

    private final NewsService ns;

    public PageSettingsController(NewsService ns) {
        this.ns = ns;
    }

    @PutMapping("/page/")
    public void changeCurrentPage(@RequestBody int page){
        ns.setPage(page);
    }

    @PutMapping("/quantity/")
    public void changeQuantityOfNewsesOnPage(@RequestBody int quantity){
        ns.setQuantity_newses_on_page(quantity);
    }

    @GetMapping("/numberof/pages")
    public int getNumberOfPages(){
        return ns.countPagesQuantity();
    }

    @GetMapping("/current/page")
    public int getCurrentPage(){
        return ns.getPage();
    }
}
