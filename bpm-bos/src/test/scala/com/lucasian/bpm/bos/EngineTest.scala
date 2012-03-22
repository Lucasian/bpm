package com.lucasian.bpm.bos

import org.specs2.mutable._
import org.ow2.bonita.util.AccessorUtil
import org.ow2.bonita.facade.runtime.ActivityState


class EngineTest extends Specification with BonitaEnvironment {
  
  "The engine" should {
    "get the management api" in {
      bonitaTestEnv {
        val managementApi   = AccessorUtil.getManagementAPI()

        managementApi must not be null
      }
    }
    "get the runtime api" in {
      bonitaTestEnv {
        val runtimeApi      = AccessorUtil.getRuntimeAPI()

        runtimeApi must not be null
      }
    }
    "get the query runtime api" in {
      bonitaTestEnv {
        val queryRuntimeApi = AccessorUtil.getQueryRuntimeAPI()

        queryRuntimeApi must not be null
      }
    }
  }

}
