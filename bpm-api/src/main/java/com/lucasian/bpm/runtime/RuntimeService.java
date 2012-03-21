package com.lucasian.bpm.runtime;

import java.util.Map;

public interface RuntimeService {

	Process startProcess(String processId);
	
	Process startProcess(String processId, Map<String, Object> variables);
	
	Process findProcess(String processId);
	
	void deleteProcess(String processId, String deleteReason);
	
}
