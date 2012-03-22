package com.lucasian.bpm.bos

import java.io.File
import org.ow2.bonita.identity.auth.DomainOwner
import org.ow2.bonita.util.BonitaConstants
import com.lucasian.bpm.ProcessEngineFactory
import com.other.context.TestUserFinder

trait BonitaTestEnvironment {

  def createFS(bonitaHome: String) = {
    var bonitaHomeFile = new File(bonitaHome)
    if (!bonitaHomeFile.exists()) {
      bonitaHomeFile.mkdirs()
    }
  }

  def bonitaTestEnv[T](proc: => T): T = {

    val bonitaHome = System.getProperty("user.dir") + File.separator + "bonita"
    System.setProperty("BONITA_HOME", bonitaHome)

    createFS(bonitaHome)
    
    ProcessEngineFactory.registerUserFinder(new TestUserFinder())

    try {
      proc
    } finally {
      System.clearProperty("BONITA_HOME")
    }

  }

}