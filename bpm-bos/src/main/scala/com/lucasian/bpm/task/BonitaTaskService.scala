package com.lucasian.bpm.task

import java.util.Map
import java.util.Collection
import java.util.HashSet
import scala.collection.JavaConversions._
import org.ow2.bonita.facade.runtime.ActivityState
import org.ow2.bonita.facade.uuid.ProcessInstanceUUID
import org.ow2.bonita.facade.QueryRuntimeAPI
import org.ow2.bonita.facade.RuntimeAPI
import org.ow2.bonita.util.AccessorUtil
import org.springframework.stereotype.Service
import com.lucasian.bpm.BonitaAuth
import com.lucasian.bpm.BonitaConverter
import com.lucasian.bpm.ProcessEngineFactory
import org.ow2.bonita.facade.uuid.ActivityInstanceUUID

@Service
class BonitaTaskService extends TaskService with BonitaAuth {

  @Override
  def listPendingTasks(): Collection[Task] = {
    listPendingTasksByUser(ProcessEngineFactory.findCurrentUser())
  }

  @Override
  def listPendingTasks(processId: String): Collection[Task] = {
    listPendingTasksByUser(ProcessEngineFactory.findCurrentUser(), processId)
  }

  @Override
  def listActiveTasks(): Collection[Task] = {
    listActiveTasksByUser(ProcessEngineFactory.findCurrentUser())
  }

  @Override
  def listActiveTasks(processId: String): Collection[Task] = {
    listActiveTasksByUser(ProcessEngineFactory.findCurrentUser(), processId)
  }

  @Override
  def listPendingTasksByUser(userId: String): Collection[Task] = {
    bonitaLogin(userId) {
      val bonitaTasks =
        iterableAsScalaIterable(getQueryRuntimeApi().getTaskList(ActivityState.READY))
      val tasks = new HashSet[Task](bonitaTasks.size)
      for (bonitaTask <- bonitaTasks) {
        tasks.add(BonitaConverter.convertTask(bonitaTask, this))
      }
      tasks
    }
  }

  @Override
  def listPendingTasksByUser(userId: String, processId: String): Collection[Task] = {
    bonitaLogin(userId) {
      val processUUID = new ProcessInstanceUUID(processId)
      val bonitaTasks =
        iterableAsScalaIterable(getQueryRuntimeApi().getTaskList(processId, ActivityState.READY))
      val tasks = new HashSet[Task](bonitaTasks.size)
      for (bonitaTask <- bonitaTasks) {
        tasks.add(BonitaConverter.convertTask(bonitaTask, this))
      }
      tasks
    }
  }

  @Override
  def listActiveTasksByUser(userId: String): Collection[Task] = {
    bonitaLogin(userId) {
      val bonitaTasks =
        iterableAsScalaIterable(getQueryRuntimeApi().getTaskList(ActivityState.EXECUTING))
      val tasks = new HashSet[Task](bonitaTasks.size)
      for (bonitaTask <- bonitaTasks) {
        tasks.add(BonitaConverter.convertTask(bonitaTask, this))
      }
      tasks
    }
  }

  @Override
  def listActiveTasksByUser(userId: String, processId: String): Collection[Task] = {
    bonitaLogin(userId) {
      val processUUID = new ProcessInstanceUUID(processId)
      val bonitaTasks =
        iterableAsScalaIterable(getQueryRuntimeApi().getTaskList(processId, ActivityState.EXECUTING))
      val tasks = new HashSet[Task](bonitaTasks.size)
      for (bonitaTask <- bonitaTasks) {
        tasks.add(BonitaConverter.convertTask(bonitaTask, this))
      }
      tasks
    }
  }

  @Override
  def assign(taskId: String, userId: String): Unit = {
    bonitaLogin {
      val taskUUID = new ActivityInstanceUUID(taskId)
      getRuntimeApi().assignTask(taskUUID, userId)
    }
  }

  @Override
  def unassign(taskId: String): Unit = {
    bonitaLogin {
      val taskUUID = new ActivityInstanceUUID(taskId)
      getRuntimeApi().unassignTask(taskUUID)
    }
  }

  @Override
  def start(taskId: String): Unit = {
    bonitaLogin {
      val taskUUID = new ActivityInstanceUUID(taskId)
      getRuntimeApi().startTask(taskUUID, false)
    }
  }

  @Override
  def start(taskId: String, variables: Map[String, Object]): Unit = {
    bonitaLogin {
      val taskUUID = new ActivityInstanceUUID(taskId)
      //TODO: ASSIGN VARIABLES
      getRuntimeApi().startTask(taskUUID, false)
    }
  }

  @Override
  def finish(taskId: String): Unit = {
    bonitaLogin {
      val taskUUID = new ActivityInstanceUUID(taskId)
      getRuntimeApi().finishTask(taskUUID, false)
    }
  }

  @Override
  def finish(taskId: String, variables: Map[String, Object]): Unit = {
    bonitaLogin {
      val taskUUID = new ActivityInstanceUUID(taskId)
      //TODO: ASSIGN VARIABLES
      getRuntimeApi().finishTask(taskUUID, false)
    }
  }

  @Override
  def execute(taskId: String): Unit = {
    start(taskId)
    finish(taskId)
  }

  @Override
  def execute(taskId: String, variables: Map[String, Object]): Unit = {
    start(taskId, variables)
    finish(taskId)
  }

  @Override
  def suspend(taskId: String): Unit = {
    bonitaLogin {
      val taskUUID = new ActivityInstanceUUID(taskId)
      getRuntimeApi().suspendTask(taskUUID, false)
    }
  }

  @Override
  def resume(taskId: String): Unit = {
    bonitaLogin {
      val taskUUID = new ActivityInstanceUUID(taskId)
      getRuntimeApi().resumeTask(taskUUID, false)
    }
  }

  @Override
  def delegate(taskId: String, userId: String): Unit = {
    bonitaLogin {
      val taskUUID = new ActivityInstanceUUID(taskId)
      getRuntimeApi().assignTask(taskUUID, userId)
    }
  }

  private def getQueryRuntimeApi(): QueryRuntimeAPI =
    AccessorUtil.getQueryRuntimeAPI()

  private def getRuntimeApi(): RuntimeAPI =
    AccessorUtil.getRuntimeAPI()

}