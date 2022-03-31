package com.deepfake.news.forum.components;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class UserComment {

    private int comment_id;
    private int news_id;
    private String commenter_name;
    private String comment;

    @Id
    @Column(name ="comment_id")
    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    @Column(name ="news_id", nullable = false, columnDefinition = "UNSIGNED INT(11)")
    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    @Column(name ="commenter_name", length = 30, nullable = false)
    public String getCommenter_name() {
        return commenter_name;
    }

    public void setCommenter_name(String commenter_name) {
        this.commenter_name = commenter_name;
    }

    @Column(name ="comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
