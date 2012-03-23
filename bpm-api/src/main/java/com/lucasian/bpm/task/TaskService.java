package com.lucasian.bpm.task;

import java.util.Collection;
import java.util.Map;

public interface TaskService {

	Collection<Task> listPendingTasks();

	Collection<Task> listPendingTasks(String processId);

	Collection<Task> listActiveTasks();

	Collection<Task> listActiveTasks(String processId);

	Collection<Task> listPendingTasksByUser(String userId);

	Collection<Task> listPendingTasksByUser(String userId, String processId);

	Collection<Task> listActiveTasksByUser(String userId);

	Collection<Task> listActiveTasksByUser(String userId, String processId);

	void assign(String taskId, String userId);

	void unassign(String taskId);
	
	void delegate(String taskId, String userId);

	void start(String taskId);

	void start(String taskId, Map<String, Object> variables);

	void finish(String taskId);

	void finish(String taskId, Map<String, Object> variables);

	/*
	 * Executing SHOULD be the same as starting and then finishing
	 */
	void execute(String taskId);

	/*
	 * Executing SHOULD be the same as starting and then finishing
	 */
	void execute(String taskId, Map<String, Object> variables);

	void suspend(String taskId);

	void resume(String taskId);

}
