#!/usr/bin/env groovy
import jenkins.*
import jenkins.model.*
import hudson.*
import hudson.model.*
import hudson.util.PersistedList
import jenkins.model.Jenkins
import jenkins.branch.*
import jenkins.plugins.git.*
import org.jenkinsci.plugins.workflow.multibranch.*
import com.cloudbees.hudson.plugins.folder.*


// Required plugins: 
// - workflow-multibranch
//

// Based on: https://github.com/peterjenkins1/jenkins-scripts/blob/master/add-multibranch-pipeline-job.groovy 


// Project variables
String gitRepo = "https://github.com/Dirc/jenkins-init-groovy.git"
String credentialsId = ""
String jobName = "multibranch_pipeline"
String jobDescription = "FooBar"
String jobScript = "Jenkinsfile_hello_world"
String periodicFolderTrigger = "15"


// Create MultiBranch pipeline
Jenkins jenkins = Jenkins.instance
WorkflowMultiBranchProject mbp = jenkins.createProject(WorkflowMultiBranchProject.class, jobName)
mbp.description = jobDescription
mbp.getProjectFactory().setScriptPath(jobScript)
mbp.addTrigger(new com.cloudbees.hudson.plugins.folder.computed.PeriodicFolderTrigger(periodicFolderTrigger));

// Add git repo
String id = "unneeded-non-null-ID"
String remote = gitRepo
String includes = "*"
String excludes = ""
boolean ignoreOnPushNotifications = false
GitSCMSource gitSCMSource = new GitSCMSource(id, remote, credentialsId, includes, excludes, ignoreOnPushNotifications)
BranchSource branchSource = new BranchSource(gitSCMSource)

// Remove and replace?
PersistedList sources = mbp.getSourcesList()
sources.clear()
sources.add(branchSource)

// Trigger initial build (scan)
jenkins.getItem(jobName).scheduleBuild()

jenkins.save()