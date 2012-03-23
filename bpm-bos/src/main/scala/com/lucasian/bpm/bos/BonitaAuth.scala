package com.lucasian.bpm
import org.ow2.bonita.identity.auth.DomainOwner
import org.ow2.bonita.identity.auth.UserOwner
import org.ow2.bonita.util.BonitaConstants

trait BonitaAuth {

  private def setupDomain(): Unit = {
    DomainOwner.setDomain(BonitaConstants.DEFAULT_DOMAIN)
  }
  
  def bonitaLogin[T](user: String)(proc: => T):T = {
    UserOwner.setUser(user)
    setupDomain()

    try {
      proc
    } finally {
      //Clear the user, only this process should be able to use it
      UserOwner.setUser(null)
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