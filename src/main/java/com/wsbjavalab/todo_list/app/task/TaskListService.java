package com.wsbjavalab.todo_list.app.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskListService {
    @Autowired
    private TaskListRepository taskListRepository;

    public Task getTask(long taskID) {
        return taskListRepository.findById(taskID).orElse(null);
    }

    public List<Task> getTaskList() {
        List<Task> taskList = new ArrayList<>();
        taskListRepository.findAll().forEach(taskList::add);
        return taskList;
    }

    public List<Task> getUserTaskList(long userID) {
        // FIXME has to be a better way than this but can't bother tbh
        List<Task> userTaskList = new ArrayList<>();
        List<Task> taskList = getTaskList();
        for (Task task : taskList) {
            if (task.getUserID() == userID){
                userTaskList.add(task);
            }
        }
        return userTaskList;
    }

    public Task addTask(Task task) {
        task.setStatus("TO_DO");
        taskListRepository.save(task);
        return getTask(task.getId());
    }

    public void deleteTask(long taskID) {
        taskListRepository.deleteById(taskID);
    }

    public Task updateTask(long taskID, UpdateTask updateBody) {
        Task taskToUpdate = getTask(taskID);
        taskListRepository.save(taskToUpdate.updateFields(updateBody));
        return getTask(taskID);
    }

    public Task updateStatus(long taskID, Status status) {
        Task taskToUpdate = getTask(taskID);
        taskToUpdate.setStatus(status.getStatus());
        taskListRepository.save(taskToUpdate);
        return getTask(taskID);
    }

    public long getTaskCount() {
        return taskListRepository.count();
    }

    public boolean isTaskIdValid(long id) {
        return taskListRepository.findById(id).isPresent();
    }

}
