package com.deepfake.news.forum.components;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name ="posts")
public class News {

    private int news_id;
    private String name;
    private String sourceURL;
    private int relevance_vote;
    private int fake_vote;
    private Timestamp publishing_date;
    private List<UserComment> comments;

    @Id
    @Column(name = "news_id")
    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    @Column(name = "name",  nullable=false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "sourceURL",  nullable=false, length = 200)
    public String getSourceURL() {
        return sourceURL;
    }

    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }

    @Column(name = "relevance_vote", columnDefinition = "int default 0")
    public int getRelevance_vote() {
        return relevance_vote;
    }

    public void setRelevance_vote(int relevance_vote) {
        this.relevance_vote = relevance_vote;
    }

    @Column(name = "fake_vote", columnDefinition = "int default 0")
    public int getFake_vote() {
        return fake_vote;
    }

    public void setFake_vote(int fake_vote) {
        this.fake_vote = fake_vote;
    }

    @Column(name = "publishing_date", nullable = false)
    public Timestamp getPublishing_date() {
        return publishing_date;
    }

    public void setPublishing_date(Timestamp publishing_date) {
        this.publishing_date = publishing_date;
    }

    @OneToMany(mappedBy = "news_id", fetch = FetchType.EAGER)
    public List<UserComment> getComments() {
        return comments;
    }

    public void setComments(List<UserComment> comments) {
        this.comments = comments;
    }

}


















