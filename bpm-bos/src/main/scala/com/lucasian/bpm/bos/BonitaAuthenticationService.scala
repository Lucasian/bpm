package com.lucasian.bpm.bos

import org.ow2.bonita.services.AuthenticationService
import com.lucasian.bpm.ProcessEngineFactory

class BonitaAuthenticationService extends AuthenticationService { 

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