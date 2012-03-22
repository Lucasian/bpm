package com.lucasian.bpm.bos

import org.ow2.bonita.util.AccessorUtil
import org.specs2.mutable.Specification
import com.lucasian.bpm.ProcessEngineFactory


class FactoryTest extends Specification with BonitaTestEnvironment {
  
  "The factory" should {
    "find the admin user" in {
      bonitaTestEnv {
        val user = ProcessEngineFactory.findCurrentUser()

        user must be equalTo "admin"
      }
    }
    "return a process engine" in {
      bonitaTestEnv {
        val processEngine = ProcessEngineFactory.getProcessEngine()

        processEngine must not be null
      }
    }
  }

}
