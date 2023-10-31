package com.douqin.chatchitVN.domain;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.douqin.chatchitVN.data.local.room.relationship.UserWithStoryRelation;
import com.douqin.chatchitVN.data.repositories.story.StoryRepository;
import com.douqin.chatchitVN.domain.entities.UserWithListStory;

import java.util.ArrayList;
import java.util.List;

public class StoryUserCase {
    StoryRepository storyRepository;

    public StoryUserCase(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    public LiveData<List<UserWithListStory>> getStory() {
        return Transformations.map(storyRepository.getStory(), list -> {
            List<UserWithListStory> userWithListStories = new ArrayList<>();
            for (UserWithStoryRelation userWithStoryRelation : list) {
                if (!userWithStoryRelation.storyList.isEmpty()) {
                    userWithListStories.add(userWithStoryRelation.toModel());
                }
            }
            return userWithListStories;
        });
    }

    public void initFromServer() {
        this.storyRepository.getStoryFromServer();
    }
}
