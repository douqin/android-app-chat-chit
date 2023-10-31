package com.douqin.chatchitVN.ui.story;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.douqin.chatchitVN.data.local.room.database.AppDatabase;
import com.douqin.chatchitVN.data.repositories.story.StoryRepository;
import com.douqin.chatchitVN.domain.StoryUserCase;
import com.douqin.chatchitVN.domain.entities.UserWithListStory;

import java.util.List;
import java.util.Objects;

public class StoryViewModel extends AndroidViewModel {

    private UserWithListStory story;

    LiveData<List<UserWithListStory>> listLiveData;

    public void selectStory(UserWithListStory story) {
        this.story = story;
    }

    public int getIndexCurrPick() {
        return Objects.requireNonNull(this.listLiveData.getValue()).indexOf(this.story);
    }

    StoryUserCase storyUserCase;

    public StoryViewModel(@NonNull Application application) {
        super(application);
        storyUserCase = new StoryUserCase(new StoryRepository(AppDatabase.gI(application).storyDao()));
        listLiveData = this.storyUserCase.getStory();
    }

    public LiveData<List<UserWithListStory>> getStory() {
        return listLiveData;
    }

    public void initFromServer() {
        this.storyUserCase.initFromServer();
    }

}
