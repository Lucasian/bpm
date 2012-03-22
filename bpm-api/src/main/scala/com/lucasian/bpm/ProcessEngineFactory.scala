package com.lucasian.bpm
import org.springframework.context.annotation.AnnotationConfigApplicationContext

object ProcessEngineFactory {

  private val packageName =
    this.getClass().getPackage().getName()
  private val springContext =
    new AnnotationConfigApplicationContext(packageName)
  private val processEngine =
    springContext.getBean(classOf[ProcessEngine])
    
  def getProcessEngine() =
    processEngine
    
  def getProcessUser() =
    springContext.getBean(classOf[ProcessUser])

}