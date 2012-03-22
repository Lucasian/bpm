package com.lucasian.bpm
import org.ow2.bonita.identity.auth.DomainOwner
import org.ow2.bonita.identity.auth.UserOwner
import org.ow2.bonita.util.BonitaConstants

trait BonitaLogin {

  def bonitaLogin[T](proc: => T): T = {
    UserOwner.setUser(ProcessEngineFactory.findCurrentUser())
    DomainOwner.setDomain(BonitaConstants.DEFAULT_DOMAIN)

    try {
      proc
    } finally {
      //Clear the user, only this process should be able to use it
      UserOwner.setUser(null)
    }
  }

}