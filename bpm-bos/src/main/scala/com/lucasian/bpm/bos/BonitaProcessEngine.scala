package com.lucasian.bpm

import org.springframework.stereotype.Service

import com.lucasian.bpm.management.ManagementService
import com.lucasian.bpm.runtime.RuntimeService
import com.lucasian.bpm.task.TaskService

import javax.inject.Inject

@Service
class BonitaProcessEngine extends ProcessEngine {

  @Inject
  private var managementService: ManagementService = _
  @Inject
  private var runtimeService: RuntimeService = _
  @Inject
  private var taskService: TaskService = _

  @Override
  def getManagementService: ManagementService = managementService

  @Override
  def getRuntimeService: RuntimeService = runtimeService

  @Override
  def getTaskService: TaskService = taskService

}