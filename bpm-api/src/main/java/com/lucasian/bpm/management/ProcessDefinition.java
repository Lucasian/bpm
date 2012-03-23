package com.lucasian.bpm.management;

public interface ProcessDefinition {

	String getName();
	String getVersion();
	ProcessDefinitionStatus getStatus();
	String getDeployedBy();
	
	
}
