package com.wsbjavalab.todo_list.app.task;

import org.springframework.lang.Nullable;

public class UpdateTask {
    @Nullable
    private long userID;

    @Nullable
    private String title;

    @Nullable
    private String description;

    public long getUserID() {
        return this.userID;
    }

    public void setUserID(long todoID) {
        this.userID = todoID;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String todoTitle) {
        this.title = todoTitle;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String todoDescription) {
        this.description = todoDescription;
    }

}
