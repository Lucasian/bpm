package com.lucasian.bpm.bos

import org.ow2.bonita.util.AccessorUtil
import org.specs2.mutable.Specification
import com.lucasian.bpm.ProcessEngineFactory


class EngineTest extends Specification with BonitaTestEnvironment {
  
  "The engine" should {
    "get the management service" in {
      bonitaTestEnv {
        val processEngine     = ProcessEngineFactory.getProcessEngine()
        val managementService = processEngine.getManagementService()

        managementService must not be null
      }
    }
  }

}
