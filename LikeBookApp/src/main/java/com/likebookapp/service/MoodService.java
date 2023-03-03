package com.likebookapp.service;

import com.likebookapp.model.entity.Mood;
import com.likebookapp.model.entity.MoodType;
import com.likebookapp.repository.MoodRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MoodService {

    private final MoodRepository moodRepository;

    public MoodService(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    @PostConstruct
    public void moodInit() {
        if (!isMoodInit()) {
            List<Mood> moods = new ArrayList<>();

            moods.add(new Mood().setMoodName(MoodType.HAPPY).setDescription("Happy"));
            moods.add(new Mood().setMoodName(MoodType.INSPIRED).setDescription("Inspired"));
            moods.add(new Mood().setMoodName(MoodType.SAD).setDescription("Sad"));

            this.moodRepository.saveAllAndFlush(moods);
        }

    }

    public boolean isMoodInit() {
        return this.moodRepository.count() > 0;
    }
}
