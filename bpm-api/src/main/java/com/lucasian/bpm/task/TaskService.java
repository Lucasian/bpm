package com.lucasian.bpm.task;

import java.util.Collection;

public interface TaskService {

	Collection<Task> listActiveTasks();

	Collection<Task> listActiveTasks(String processId);

	void assign(String taskId, String userId);

	void start(String taskId);

	void complete(String taskId);

	/*
	 * Executing SHOULD be the same as starting and then completing
	 */
	void execute(String taskId);

	void suspend(String taskId);

	void resume(String taskId);

}
