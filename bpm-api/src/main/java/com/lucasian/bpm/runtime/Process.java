package com.lucasian.bpm.runtime;

import java.util.Map;

public interface Process {

	String getId();
	
	public Map<String, Object> getVariables();
	
	boolean isEnded();
	
}
