package com.wsbjavalab.todo_list.app.task;

import java.util.Arrays;
import java.util.List;

public class Status {

    private final List<String> allowedStatuses = Arrays.asList("TO_DO", "IN_PROGRESS", "DONE");

    private String status;

    public List<String> getAllowedStatuses() {
        return this.allowedStatuses;
    }
    public boolean isStatusValid() {
        return this.allowedStatuses.contains(this.status);
    }

    public String getStatus() {
        return this.status;
    }
}