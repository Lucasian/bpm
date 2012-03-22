package com.lucasian.bpm

import org.springframework.context.annotation.AnnotationConfigApplicationContext

object ProcessEngineFactory {

  private val packageName =
    this.getClass().getPackage().getName()
  private val springContext =
    new AnnotationConfigApplicationContext(packageName)
  private var userFinder: ProcessUserFinder = _

  private val processEngine =
    springContext.getBean(classOf[ProcessEngine])

  def getProcessEngine(): ProcessEngine =
    processEngine

  def registerUserFinder(userFinder: ProcessUserFinder): Unit =
    this.userFinder = userFinder

  def findCurrentUser(): String = {
    if (userFinder == null) {
      null
    } else {
      userFinder.findCurrentUser()
    }
  }

}