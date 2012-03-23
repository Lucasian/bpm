package com.lucasian.bpm.bos

import java.io.File
import org.ow2.bonita.identity.auth.DomainOwner
import org.ow2.bonita.util.BonitaConstants
import com.lucasian.bpm.ProcessEngineFactory
import com.other.context.TestUserFinder

trait BonitaTestEnvironment {

  val environmentPath =
    System.getProperty("user.dir") + File.separator +
      "bonita" + File.separator +
      "bonita-environment.xml"

  def bonitaTestEnv[T](proc: => T): T = {

    System.setProperty("org.ow2.bonita.environment", environmentPath)
    ProcessEngineFactory.registerUserFinder(new TestUserFinder())

    proc
  }

}