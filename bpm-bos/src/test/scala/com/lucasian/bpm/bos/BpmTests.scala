package com.lucasian.bpm.bos

import org.specs2.mutable.Specification
import com.lucasian.bpm.ProcessEngineFactory
import scala.collection.JavaConversions._
import com.lucasian.bpm.management.ProcessDefinitionStatus

class BpmTests extends Specification with BonitaTestEnvironment {
  sequential

  "The bpm engine" should {
    val bpmEngine = ProcessEngineFactory.getProcessEngine()
    val managementService = bpmEngine.getManagementService()
    val runtimeService = bpmEngine.getRuntimeService()
    val taskService = bpmEngine.getTaskService()

    "find and 'admin' logged user" in {
      bonitaTestEnv {
        ProcessEngineFactory.findCurrentUser() must be equalTo "admin"
      }
    }
    "'admin' should be an admin" in {
      bonitaTestEnv {
        ProcessEngineFactory.isUserAdmin("admin") must be equalTo true
      }
    }
    "be able to deploy a process" in {
      bonitaTestEnv {
        managementService.deployFile("bonita/processes/simple.bar")
        managementService.listProcessDefinitions().size() must be equalTo 1
      }
    }
    "be able to deactivate a process" in {
      bonitaTestEnv {
        managementService.deactivateProcessDefinition("MiProceso1", "1.0")
        val definitions = iterableAsScalaIterable(managementService.listProcessDefinitions())
        definitions.forall(_.getStatus() == ProcessDefinitionStatus.INACTIVE) must be equalTo true
      }
    }
    "be able to reactivate a process" in {
      bonitaTestEnv {
        managementService.activateProcessDefinition("MiProceso1", "1.0")
        val definitions = iterableAsScalaIterable(managementService.listProcessDefinitions())
        definitions.forall(_.getStatus() == ProcessDefinitionStatus.ACTIVE) must be equalTo true
      }
    }
    "have no pending tasks when there are none" in {
      bonitaTestEnv {
        taskService.listPendingTasks().size() must be equalTo 0
      }
    }
    "have no active tasks when there are none" in {
      bonitaTestEnv {
        taskService.listActiveTasks().size() must be equalTo 0
      }
    }
    "start a process, have a pending task and no active tasks" in {
      bonitaTestEnv {
        runtimeService.startProcess("MiProceso1")
        taskService.listPendingTasks().size() must be equalTo 1
        taskService.listActiveTasks().size() must be equalTo 0
      }
    }
    "claim a task, still have a pending task and no active tasks" in {
      bonitaTestEnv {
        val oldPendingTasks = iterableAsScalaIterable(taskService.listPendingTasks())
        oldPendingTasks.size() must be equalTo 1
        val task = oldPendingTasks.first
        taskService.claim(task.getId())
        taskService.listPendingTasks().size() must be equalTo 1 //The new pending tasks
        taskService.listActiveTasks().size() must be equalTo 0
      }
    }
    "start a task, have no pending task and an active task" in {
      bonitaTestEnv {
        val oldPendingTasks = iterableAsScalaIterable(taskService.listPendingTasks())
        oldPendingTasks.size() must be equalTo 1
        val task = oldPendingTasks.first
        taskService.start(task.getId())
        taskService.listPendingTasks().size() must be equalTo 0
        taskService.listActiveTasks().size() must be equalTo 1
      }
    }
    "finish the task, have no more tasks" in {
      bonitaTestEnv {
        val oldActiveTasks = iterableAsScalaIterable(taskService.listActiveTasks())
        oldActiveTasks.size() must be equalTo 1
        val oldTask = oldActiveTasks.first
        taskService.finish(oldTask.getId())
        taskService.listPendingTasks().size() must be equalTo 0
        taskService.listPendingTasks().size() must be equalTo 0
      }
    }
    "be able to undeploy a process" in {
      bonitaTestEnv {
        managementService.deleteProcessDefinition("MiProceso1", "1.0")
        managementService.listProcessDefinitions().size() must be equalTo 0
      }
    }
  }

}