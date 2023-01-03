package com.wsbjavalab.todo_list.app.user;

import org.springframework.lang.Nullable;

public class UpdateUser {

    @Nullable
    private String name;

    @Nullable
    private String email;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
