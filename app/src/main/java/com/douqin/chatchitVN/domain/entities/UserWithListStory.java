package com.douqin.chatchitVN.domain.entities;

import com.douqin.chatchitVN.common.Identifiable;
import com.douqin.chatchitVN.data.models.UI.Story;
import com.douqin.chatchitVN.data.models.UI.User;

import java.util.List;
import java.util.Objects;

public class UserWithListStory implements Identifiable {
    public User user;
    public List<Story> storyList;

    public UserWithListStory(User user,
                             List<Story> storyList) {
        this.user = user;
        this.storyList = storyList;
    }

    @Override
    public long getId() {
        return user.iduser;
    }

    public boolean equals(UserWithListStory other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        if (!Objects.equals(storyList, other.storyList)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (storyList != null ? storyList.hashCode() : 0);
        return result;
    }
}
