package com.wsbjavalab.todo_list.app.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @SequenceGenerator(name = "task_seq", initialValue = 1, allocationSize = 101)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @NotNull
    @Column(name = "user_id")
    private long userID;

    @NotBlank
    @Size(min = 3, message = "Title should have at least 3 characters")
    @Column(name = "title")
    private String title;

    @NotBlank
    @Size(min = 5, message = "Description should have at least 5 characters")
    @Column(name = "description")
    private String description;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy hh:mm:ss")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy hh:mm:ss")
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name = "status")
    private String status;

    public long getId() {
        return this.id;
    }

    public void setId(long todoID) {
        this.id = todoID;
    }

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

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String todoStatus) {
        this.status = todoStatus;
    }

    public Date getCreationDate() {
        return this.createdAt;
    }

    public void setCreationDate(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateDate() {
        return this.updatedAt;
    }

    public void setUpdateDate(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Task updateFields(UpdateTask updateBody) {
        //FIXME refactor this crap method
        if (updateBody.getUserID() != 0) {
            this.setUserID(updateBody.getUserID());
        }
        if (updateBody.getTitle() != null && !updateBody.getTitle().isEmpty()) {
            this.setTitle(updateBody.getTitle());
        }
        if (updateBody.getDescription() != null && !updateBody.getDescription().isEmpty()) {
            this.setDescription(updateBody.getDescription());
        }
        return this;
    }
}
