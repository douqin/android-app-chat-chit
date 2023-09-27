package com.douqin.chatchitVN.data.database.room.relationship;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.douqin.chatchitVN.data.database.room.entity.StoryEntity;
import com.douqin.chatchitVN.data.models.UI.Story;
import com.douqin.chatchitVN.data.models.UI.User;

import java.util.List;

public class UserWithStoryRelation {
    @Embedded
    User user;

    @Relation(
            entity = User.class,
            parentColumn = "iduser",
            entityColumn = "iduserowner"
    )
    List<StoryEntity> storyList;
}
