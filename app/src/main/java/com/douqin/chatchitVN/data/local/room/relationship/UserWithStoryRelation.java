package com.douqin.chatchitVN.data.local.room.relationship;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.douqin.chatchitVN.data.local.room.entity.StoryEntity;
import com.douqin.chatchitVN.data.local.room.entity.UserEntity;
import com.douqin.chatchitVN.domain.entities.UserWithListStory;

import java.util.List;
import java.util.stream.Collectors;

public class UserWithStoryRelation {
    @Embedded
    public UserEntity user;

    @Relation(
            parentColumn = "iduser",
            entityColumn = "iduserowner"
    )
    public List<StoryEntity> storyList;

    public UserWithListStory toModel() {
        return new UserWithListStory(
                this.user.toModel(),
                this.storyList.stream().map(StoryEntity::toModel).collect(Collectors.toList())
        );
    }
}
