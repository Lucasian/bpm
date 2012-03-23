package com.other.context
import com.lucasian.bpm.ProcessUserFinder

class TestUserFinder extends ProcessUserFinder {

  @Override
  def findCurrentUser(): String =
    "admin"
    
  @Override
  def isUserAdmin(userId: String): Boolean =
    "admin".equals(userId)
    
  @Override
  def isUserValid(userId: String): Boolean =
    "admin".equals(userId)
  
}