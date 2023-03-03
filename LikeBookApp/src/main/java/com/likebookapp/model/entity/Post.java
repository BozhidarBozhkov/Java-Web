package com.likebookapp.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    private UserEntity creator;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserEntity> userLikes;

    @ManyToOne
    private Mood mood;

    public Post() {}

    public Long getId() {
        return id;
    }

    public Post setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Post setContent(String content) {
        this.content = content;
        return this;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public Post setCreator(UserEntity creator) {
        this.creator = creator;
        return this;
    }

    public List<UserEntity> getUserLikes() {
        return userLikes;
    }

    public Post setUserLikes(List<UserEntity> userLikes) {
        this.userLikes = userLikes;
        return this;
    }

    public Mood getMood() {
        return mood;
    }

    public Post setMood(Mood mood) {
        this.mood = mood;
        return this;
    }
}
