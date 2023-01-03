package com.wsbjavalab.todo_list.app.user;

import com.wsbjavalab.todo_list.app.task.Task;
import com.wsbjavalab.todo_list.app.task.UpdateTask;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @SequenceGenerator(name = "user_seq", initialValue = 1, allocationSize = 101)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @NotBlank
    @Size(min = 3, message = "Name should have at least 3 characters")
    @Column(name = "name")
    private String name;

    @NotBlank
    @Email
    @Column(name = "email")
    private String email;

    public long getId() {
        return this.id;
    }

    public void setId(long todoId) {
        this.id = todoId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String todoName) {
        this.name = todoName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String todoEmail) {
        this.email = todoEmail;
    }

    public User updateFields(UpdateUser updateBody) {
        //FIXME refactor this crap method
        if (updateBody.getName() != null && !updateBody.getName().isEmpty()) {
            this.setName(updateBody.getName());
        }
        if (updateBody.getEmail() != null && !updateBody.getEmail().isEmpty()) {
            this.setEmail(updateBody.getEmail());
        }
        return this;
    }
}
