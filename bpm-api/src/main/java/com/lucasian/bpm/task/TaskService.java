package com.lucasian.bpm.task;

import java.util.Collection;
import java.util.Map;

public interface TaskService {

	Collection<Task> listPendingTasks();
	
	Collection<Task> listPendingTasks(String processId);
	
	Collection<Task> listActiveTasks();

	Collection<Task> listActiveTasks(String processId);

	void assign(String taskId, String userId);
	
	void unassign(String taskId);

	void start(String taskId);
	
	void start(String taskId, Map<String, Object> variables);

	void complete(String taskId);
	
	void complete(String taskId, Map<String, Object> variables);

	/*
	 * Executing SHOULD be the same as starting and then completing
	 */
	void execute(String taskId, Map<String, Object> variables);

	void suspend(String taskId);

	void resume(String taskId);

}
