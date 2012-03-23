package com.lucasian.bpm

import java.util.concurrent.locks.ReentrantLock

import org.ow2.bonita.identity.auth.DomainOwner
import org.ow2.bonita.identity.auth.UserOwner
import org.ow2.bonita.util.BonitaConstants

import com.lucasian.bpm.bos.BonitaExecutor

trait BonitaAuth {

  val executorLock = new ReentrantLock()
  
  private def setupDomain(): Unit = {
    DomainOwner.setDomain(BonitaConstants.DEFAULT_DOMAIN)
  }

  def bonitaLogin[T](user: String)(proc: => T): T = {
    
    def prepareAuth() = {
      UserOwner.setUser(user)
      setupDomain()
    }

    executorLock.lock()
    try {
      //We prepare the thread with our user's info
      BonitaExecutor.execute(prepareAuth)
      BonitaExecutor.execute(proc)
    } finally {
      executorLock.unlock()
    }
  }

  def bonitaLogin[T](proc: => T): T = {
    bonitaLogin(ProcessEngineFactory.findCurrentUser())(proc)
  }

  def bonitaContext[T](proc: => T): T = {
    setupDomain()
    proc
  }  

}


