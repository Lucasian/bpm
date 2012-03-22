package com.lucasian.bpm.runtime

import java.util.Map
import com.lucasian.bpm.BonitaLogin

import org.springframework.stereotype.Service

@Service
class BonitaRuntimeService extends RuntimeService with BonitaLogin {

  @Override
  def startProcess(processId: String): Process = {
    bonitaLogin {
      null
    }
  }
  

  @Override
  def startProcess(processId: String, variables: Map[String, Object]): Process = {
    null
  }

  @Override
  def findProcess(processId: String): Process = {
    null
  }
  
  @Override
  def deleteProcess(processId: String, deleteReason: String): Unit = {
    
  } 
    
  

}