package com.lucasian.bpm.bos

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


object BonitaExecutor {

  val executor = Executors.newSingleThreadExecutor()
  
  val bonitaTimeout = 30
  val bonitaTimeoutUnit = TimeUnit.SECONDS
  
  def execute[T](proc: => T):T = {
    val future = executor.submit(new Callable[T] {
      @Override
      def call(): T = {
        proc
      }
    })
    future.get(bonitaTimeout, bonitaTimeoutUnit)
  }
  
}