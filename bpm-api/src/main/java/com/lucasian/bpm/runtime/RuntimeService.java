package com.lucasian.bpm.runtime;

import java.util.Map;

public interface RuntimeService {

	Process startProcess(String processName);
	
	Process startProcess(String processName, Map<String, Object> variables);
	
	Process startProcess(String userId, String processName);
	
	Process startProcess(String userId, String processName, Map<String, Object> variables);
	
	Process findProcess(String processId);
	
	void deleteProcess(String processId, String deleteReason);
	
}
