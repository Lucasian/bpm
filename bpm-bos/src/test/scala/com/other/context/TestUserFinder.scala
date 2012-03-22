package com.other.context
import com.lucasian.bpm.ProcessUserFinder

class TestUserFinder extends ProcessUserFinder {

  @Override
  def findCurrentUser(): String =
    "admin"
  
}