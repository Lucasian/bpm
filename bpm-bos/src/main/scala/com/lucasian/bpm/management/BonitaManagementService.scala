package com.lucasian.bpm.management

import java.io.File
import java.net.URI
import java.util.HashSet
import java.util.Set
import java.util.Map

import scala.collection.JavaConversions.iterableAsScalaIterable

import org.ow2.bonita.facade.ManagementAPI
import org.ow2.bonita.facade.QueryDefinitionAPI
import org.ow2.bonita.util.AccessorUtil
import org.ow2.bonita.util.BusinessArchiveFactory
import org.springframework.stereotype.Service

import com.lucasian.bpm.BonitaAuth
import com.lucasian.bpm.BonitaConverter

@Service
class BonitaManagementService extends ManagementService with BonitaAuth {

  @Override
  def deployFile(filepath: String): Unit = {
    val barFile = new File(filepath)
    val businessArchive = BusinessArchiveFactory.getBusinessArchive(barFile)

    bonitaLogin {
      getManagementApi().deploy(businessArchive)
    }
  }

  @Override
  def deployResource(classpath: String): Unit = {
    val fileUri = new URI("classpath:" + classpath)
    val barFile = new File(fileUri)
    val businessArchive = BusinessArchiveFactory.getBusinessArchive(barFile)

    bonitaLogin {
      getManagementApi().deploy(businessArchive)
    }
  }

  @Override
  def deleteProcessDefinition(name: String, version: String): Unit = {
    bonitaLogin {
      val process = getQueryDefinitionApi().getProcess(name, version)
      getManagementApi().deleteProcess(process.getUUID())
    }
  }

  @Override
  def activateProcessDefinition(name: String, version: String): Unit = {
    bonitaLogin {
      val process = getQueryDefinitionApi().getProcess(name, version)
      getManagementApi().enable(process.getUUID())
    }
  }

  @Override
  def deactivateProcessDefinition(name: String, version: String): Unit = {
    bonitaLogin {
      val process = getQueryDefinitionApi().getProcess(name, version)
      getManagementApi().disable(process.getUUID())
    }
  }

  @Override
  def listProcessDefinitions(): Set[ProcessDefinition] = {
    val bonitaProcesses =
      bonitaLogin {
        iterableAsScalaIterable(getQueryDefinitionApi().getProcesses())
      }
    
    val processes = new HashSet[ProcessDefinition](bonitaProcesses.size)

    for (bonitaProcess <- bonitaProcesses) {
      processes.add(BonitaConverter.convertProcessDefinition(bonitaProcess))
    }

    processes
  }

  private def getQueryDefinitionApi(): QueryDefinitionAPI =
    AccessorUtil.getQueryDefinitionAPI()

  private def getManagementApi(): ManagementAPI =
    AccessorUtil.getManagementAPI()

}