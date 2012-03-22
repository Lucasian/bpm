package com.lucasian.bpm
import org.specs2.mutable.Specification

class EngineTests extends Specification {

  "The engine factory" should {
    "get an engine" in {
      ProcessEngineFactory.getProcessEngine() must not be null 
    }
  }
  
}
