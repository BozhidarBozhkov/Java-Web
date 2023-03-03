package com.likebookapp.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "moods")
public class Mood {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private MoodType moodName;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Mood() {}

    public Long getId() {
        return id;
    }

    public Mood setId(Long id) {
        this.id = id;
        return this;
    }

    public MoodType getMoodName() {
        return moodName;
    }

    public Mood setMoodName(MoodType moodName) {
        this.moodName = moodName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Mood setDescription(String description) {
        this.description = description;
        return this;
    }
}
