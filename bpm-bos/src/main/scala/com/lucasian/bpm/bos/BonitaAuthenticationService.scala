package com.lucasian.bpm.bos

import org.ow2.bonita.services.AuthenticationService
import com.lucasian.bpm.ProcessEngineFactory

class BonitaAuthenticationService(serviceName: String) extends AuthenticationService {

  val persistenceServiceName: String = serviceName
  
  @Override
  def isUserAdmin(username: String): Boolean =
    ProcessEngineFactory.isUserAdmin(username)

  @Override
  def checkUserCredentials(username: String, password: String): Boolean =
    ProcessEngineFactory.isUserValid(username)

  @Override
  def checkUserCredentialsWithPasswordHash(username: String, password: String): Boolean =
    ProcessEngineFactory.isUserValid(username)

}