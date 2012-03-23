package com.lucasian.bpm.bos
import org.specs2.mutable.Specification

import com.lucasian.bpm.ProcessEngineFactory

class ManagementServiceTest extends Specification with BonitaTestEnvironment {
  sequential
  
  val processEngine = ProcessEngineFactory.getProcessEngine()
  val managementService = processEngine.getManagementService()
  
  "The management service" should {
    "be able to deploy and undeploy a bar file" in {
      bonitaTestEnv {
        managementService.deployFile("bonita/processes/simple.bar")
        
        managementService.deleteProcessDefinition("MiProceso1", "1.0")
        
        //Worked if got here
        true
      }
    }
    "be able to deploy, deactive and activate a process" in {
      bonitaTestEnv {
        managementService.deployFile("bonita/processes/simple.bar")
        
        managementService.deactivateProcessDefinition("MiProceso1", "1.0")
        
        managementService.activateProcessDefinition("MiProceso1", "1.0")
        
        managementService.deleteProcessDefinition("MiProceso1", "1.0")
        
        //Worked if got here
        true
      }
    }
    "deploy, list and undeploy processes" in {
      bonitaTestEnv {
        managementService.deployFile("bonita/processes/simple.bar")
        
        assert(managementService.listProcessDefinitions().size() must be equalTo 1)
        
        managementService.deleteProcessDefinition("MiProceso1", "1.0")
        
        //Worked if got here
        true
      }
    }
  }
  
}