package com.wsbjavalab.todo_list.app.task;

import org.springframework.data.repository.CrudRepository;

public interface TaskListRepository extends CrudRepository<Task, Long> {
}
