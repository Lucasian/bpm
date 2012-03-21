package com.lucasian.bpm.task;

import java.util.Date;

public interface Task {

	String getId();
	
	String getAsignee();
	
	String getStartedBy();
	
	String getName();
	
	Date getStartedDate();
	
	Date getDueDate();
	
	void delegate(String userId);
			
}
