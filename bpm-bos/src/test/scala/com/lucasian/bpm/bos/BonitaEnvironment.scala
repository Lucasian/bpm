package com.lucasian.bpm.bos
import java.io.File
import org.ow2.bonita.identity.auth.UserOwner
import org.ow2.bonita.identity.auth.DomainOwner
import org.ow2.bonita.util.BonitaConstants

trait BonitaEnvironment {

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

    //UserOwner.setUser("admin")
    DomainOwner.setDomain(BonitaConstants.DEFAULT_DOMAIN)

    try {
      proc
    } finally {
      System.clearProperty("BONITA_HOME")
    }

  }

}