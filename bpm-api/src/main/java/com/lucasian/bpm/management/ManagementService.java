package com.lucasian.bpm.management;

import java.util.Set;
import com.lucasian.bpm.runtime.Process;

public interface ManagementService {

	void deployFile(String filepath);

	void deployResource(String classpath);

	void deleteProcessDefinition(String name, String version);

	void activateProcessDefinition(String name, String version);

	void deactivateProcessDefinition(String name, String version);

	Set<ProcessDefinition> listProcessDefinitions();

}
