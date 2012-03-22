package com.lucasian.bpm.bos
import org.specs2.mutable.Specification
import com.lucasian.bpm.ProcessEngineFactory

class EngineTest extends Specification with BonitaTestEnvironment {

  val processEngine = ProcessEngineFactory.getProcessEngine()
  
  "The engine" should {
    "return a management service" in {
      bonitaTestEnv {
    	 val managementService = processEngine.getManagementService()
    	 managementService must not be null
      }
    }
    "return a runtime service" in {
      bonitaTestEnv {
        val runtimeService = processEngine.getRuntimeService()
    	 runtimeService must not be null
      }
    }
    "return a task service" in {
      bonitaTestEnv {
        val taskService = processEngine.getTaskService()
    	 taskService must not be null
      }
    }
  }
  
}