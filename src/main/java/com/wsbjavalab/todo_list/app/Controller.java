package com.wsbjavalab.todo_list.app;

import com.wsbjavalab.todo_list.app.task.Status;
import com.wsbjavalab.todo_list.app.task.Task;
import com.wsbjavalab.todo_list.app.task.TaskListService;
import com.wsbjavalab.todo_list.app.task.UpdateTask;
import com.wsbjavalab.todo_list.app.user.UpdateUser;
import com.wsbjavalab.todo_list.app.user.User;
import com.wsbjavalab.todo_list.app.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping(Controller.BASE_URL)
public class Controller {
    public static final String BASE_URL = "api/todolist";

    @Autowired
    private TaskListService taskListService;
    @Autowired
    private UserService userService;

    /*
    Task context
     */
    @GetMapping(value = "/task/{taskID}")
    public ResponseEntity getTaskById(@Valid @PathVariable long taskID) {
        Task task = taskListService.getTask(taskID);
        return isNull(task) ?
                ResponseEntity.
                        status(HttpStatus.NOT_FOUND).
                        body(taskNotFoundString(taskID)) :
                ResponseEntity.
                        status(HttpStatus.OK).
                        body(task);
    }

    @GetMapping(value = "/task")
    public ResponseEntity getTaskList() {
        List<Task> taskList = taskListService.getTaskList();
        return taskList.isEmpty() ?
                ResponseEntity.
                        status(HttpStatus.NOT_FOUND).
                        body(null) :
                ResponseEntity.
                        status(HttpStatus.OK).
                        body(taskList);
    }

    @GetMapping(value = "/task/user/{userID}")
    public ResponseEntity getTaskList(@Valid @PathVariable long userID) {
        if (!userExists(userID)) {
            return ResponseEntity.
                    status(HttpStatus.NOT_FOUND).
                    body("User with ID " + userID + " doesn't exist");
        }
        List<Task> userTaskList = taskListService.getUserTaskList(userID);
        return userTaskList.isEmpty() ?
                ResponseEntity.
                        status(HttpStatus.NOT_FOUND).
                        body("No tasks found") :
                ResponseEntity.
                        status(HttpStatus.OK).
                        body(userTaskList);
    }

    @PostMapping(value = "/task")
    public ResponseEntity addTaskToList(@Valid @RequestBody Task task) {
        if (!userExists(task.getUserID())) {
            return ResponseEntity.
                    status(HttpStatus.NOT_FOUND).
                    body("Cannot add task for non-existent user");
        }
        Task addedTask = taskListService.addTask(task);
        return isNull(addedTask) ?
                ResponseEntity.
                        status(HttpStatus.INTERNAL_SERVER_ERROR).
                        body("Adding task failed") :
                ResponseEntity.
                        status(HttpStatus.CREATED).
                        body(addedTask);
    }

    @DeleteMapping(value = "/task/{taskID}")
    public ResponseEntity deleteTaskFromList(@Valid @PathVariable long taskID) {
        if (!taskExists(taskID)) {
            return ResponseEntity.
                    status(HttpStatus.NOT_FOUND).
                    body(taskNotFoundString(taskID));
        }
        taskListService.deleteTask(taskID);
        return ResponseEntity.
                status(HttpStatus.NO_CONTENT).
                body(null);
    }

    @PutMapping(value = "/task/{taskID}")
    public ResponseEntity updateTaskFromList(@Valid @PathVariable long taskID, @Valid @RequestBody UpdateTask updateBody) {
        if (!taskExists(taskID)) {
            return ResponseEntity.
                    status(HttpStatus.NOT_FOUND).
                    body(taskNotFoundString(taskID));
        }
        if (!userExists(updateBody.getUserID())) {
            return ResponseEntity.
                    status(HttpStatus.NOT_FOUND).
                    body("Cannot append non-existent user to the task");
        }
        Task updatedTask = taskListService.updateTask(taskID, updateBody);
        return isNull(updatedTask) ?
                ResponseEntity.
                        status(HttpStatus.INTERNAL_SERVER_ERROR).
                        body("Could not update task with ID " + taskID) :
                ResponseEntity.
                        status(HttpStatus.OK).
                        body(updatedTask);
    }

    @PutMapping(value = "/task/{taskID}/status")
    public ResponseEntity updateTaskStatusFromList(@Valid @PathVariable long taskID, @Valid @RequestBody Status status) {
        if (!taskExists(taskID)) {
            return ResponseEntity.
                    status(HttpStatus.BAD_REQUEST).
                    body(taskNotFoundString(taskID));
        }
        if (!status.isStatusValid()) {
            return ResponseEntity.
                    status(HttpStatus.BAD_REQUEST).
                    body("Incorrect status given " + status.getStatus());
        }
        Task updatedTask = taskListService.updateStatus(taskID, status);
        return isNull(updatedTask) ?
                ResponseEntity.
                        status(HttpStatus.INTERNAL_SERVER_ERROR).
                        body("Could not update status of the task with ID " + taskID) :
                ResponseEntity.
                        status(HttpStatus.OK).
                        body(updatedTask);
    }

    @GetMapping(value = "/task/count")
    public ResponseEntity getTaskListCount() {
        long taskCount = taskListService.getTaskCount();
        return ResponseEntity.
                status(HttpStatus.OK).
                body("Task count: " + taskCount);
    }

    /*
     User context
      */
    @GetMapping(value = "/user/{userID}")
    public ResponseEntity getUserByID(@Valid @PathVariable long userID) {
        User user = userService.getUser(userID);
        return isNull(user) ?
                ResponseEntity.
                        status(HttpStatus.NOT_FOUND).
                        body("User with ID " + userID + " not found") :
                ResponseEntity.
                        status(HttpStatus.OK).
                        body(user);
    }

    @GetMapping(value = "/user")
    public ResponseEntity getUserList() {
        List<User> userList = userService.getUserList();
        return userList.isEmpty() ?
                ResponseEntity.
                        status(HttpStatus.NOT_FOUND).
                        body("No users found") :
                ResponseEntity.
                        status(HttpStatus.OK).
                        body(userList);
    }

    @PostMapping(value = "/user")
    public ResponseEntity addNewUser(@Valid @RequestBody User user) {
        User addedUser = userService.addUser(user);
        return isNull(addedUser) ?
                ResponseEntity.
                        status(HttpStatus.INTERNAL_SERVER_ERROR).
                        body("Adding new user failed") :
                ResponseEntity.
                        status(HttpStatus.CREATED).
                        body(addedUser);
    }

    @PutMapping(value = "/user/{userID}")
    public ResponseEntity updateUser(@Valid @PathVariable long userID, @Valid @RequestBody UpdateUser updateBody) {
        if (!userExists(userID)) {
            return ResponseEntity.
                    status(HttpStatus.NOT_FOUND).
                    body(userNotFoundString(userID));
        }
        User updatedUser = userService.updateUser(userID, updateBody);
        return isNull(updatedUser) ?
                ResponseEntity.
                        status(HttpStatus.INTERNAL_SERVER_ERROR).
                        body("Could not update task with ID " + userID) :
                ResponseEntity.
                        status(HttpStatus.OK).
                        body(updatedUser);
    }

    @DeleteMapping(value = "/user/{userID}")
    public ResponseEntity deleteUser(@Valid @PathVariable long userID) {
        if (!userExists(userID)) {
            return ResponseEntity.
                    status(HttpStatus.NOT_FOUND).
                    body(userNotFoundString(userID));
        }
        userService.deleteUser(userID);
        return ResponseEntity.
                status(HttpStatus.NO_CONTENT).
                body(null);
    }

    /*
    Helper methods
    */
    public String taskNotFoundString(long taskID) {
        return "Task with ID " + taskID + " not found";
    }

    public String userNotFoundString(long userID) {
        return "User with ID " + userID + " not found";
    }

    public boolean userExists(long userID) {
        return userService.isUserIdValid(userID);
    }

    private boolean taskExists(long taskID) {
        return taskListService.isTaskIdValid(taskID);
    }


}
