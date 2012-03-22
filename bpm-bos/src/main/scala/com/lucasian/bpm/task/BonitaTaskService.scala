package com.lucasian.bpm.task

import java.util.Collection
import java.util.Map
import org.springframework.stereotype.Service

@Service
class BonitaTaskService extends TaskService {

  @Override
  def listPendingTasks(): Collection[Task] = {
    null
  }
  
  @Override
  def listPendingTasks(processId: String): Collection[Task] = {
    null
  }
  
  @Override
  def listActiveTasks(): Collection[Task] = {
    null
  }
  
  @Override
  def listActiveTasks(processId: String): Collection[Task] = {
    null
  }
  
  @Override
  def assign(taskId: String, userId: String): Unit = {
    null
  }
  
  @Override
  def unassign(taskId: String): Unit = {
    null
  }

  @Override
  def start(taskId: String): Unit = {
    null
  }
  
  @Override
  def start(taskId: String, variables: Map[String, Object]): Unit = {
    null
  }
  
  @Override
  def finish(taskId: String): Unit = {
    null
  }
  
  @Override
  def finish(taskId: String, variables: Map[String, Object]): Unit = {
    null
  }
  
  @Override
  def execute(taskId: String): Unit = {
    null
  }
  
  @Override
  def execute(taskId: String, variables: Map[String, Object]): Unit = {
    null
  }
  
  @Override
  def suspend(taskId: String): Unit = {
    null
  }
  
  @Override
  def resume(taskId: String): Unit = {
    null
  }
  
}