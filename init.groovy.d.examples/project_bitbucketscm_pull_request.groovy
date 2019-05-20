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
import com.cloudbees.jenkins.plugins.bitbucket.*
import com.cloudbees.jenkins.plugins.bitbucket.endpoints.*;

// Variables
String bitbucketServerName = "Bitbucket_Server_Name"  // Bitbucket server name, defined in global Jenkins conf (bitbbucketName)
String credentialsId = "credential_reference_id_usr_pwd"
String repoOwner = ""
String repository = ""
String jobName = ""
String jobDescription = "FooBar"
String jobScript = "Jenkinsfile"
String periodicFolderTrigger = "1m"


// Create MultiBranch pipeline
Jenkins jenkins = Jenkins.instance
WorkflowMultiBranchProject mbp = jenkins.createProject(WorkflowMultiBranchProject.class, jobName)
mbp.description = jobDescription
mbp.getProjectFactory().setScriptPath(jobScript)

// Add trigger if exists
if ( periodicFolderTrigger?.trim() ) {
    mbp.addTrigger(new com.cloudbees.hudson.plugins.folder.computed.PeriodicFolderTrigger(periodicFolderTrigger));
} else {
    println("WARNING: No periodic trigger set, hence new pull requests will NOT be detected.")
}

// Get BitBucket server from general Jenkins config
//String bitbucketServerUrl = println BitbucketEndpointConfiguration.get().getEndpoints()[1].getServerUrl()
String bitbucketServerUrl;
for (AbstractBitbucketEndpoint endpoint in BitbucketEndpointConfiguration.get().getEndpoints()) {
    if ( endpoint.getDisplayName() == bitbucketServerName ) {
        bitbucketServerUrl = endpoint.getServerUrl();
    }
}


// Define Git Behaviours (traits) ---> DOES NOT WORK YET, see workaround below.
//Ref: https://github.com/jenkinsci/bitbucket-branch-source-plugin/blob/master/src/main/java/com/cloudbees/jenkins/plugins/bitbucket/BitbucketSCMSource.java
//
//List<SCMSourceTrait> traits = new
//traits = new List<SCMSourceTrait>
//traits.add(new BranchDiscoveryTrait(true, true));
//traits.add(new OriginPullRequestDiscoveryTrait(EnumSet.of(ChangeRequestCheckoutStrategy.MERGE)));
//traits.add(new ForkPullRequestDiscoveryTrait(EnumSet.of(ChangeRequestCheckoutStrategy.MERGE), new ForkPullRequestDiscoveryTrait.TrustTeamForks()));


// Add git repo
//WORKAROUND: Add "dummy-id" to trigger the default/deprecated values of the traits/Behaviour.
BitbucketSCMSource bitbucketSCMSource = new BitbucketSCMSource("dummy-id", repoOwner, repository)
bitbucketSCMSource.setServerUrl(bitbucketServerUrl)
bitbucketSCMSource.setCredentialsId(credentialsId)
BranchSource branchSource = new BranchSource(bitbucketSCMSource)


// Remove and replace?
PersistedList sources = mbp.getSourcesList()
sources.clear()
sources.add(branchSource)

// Trigger initial build (scan)
//jenkins.getItem(jobName).scheduleBuild()

jenkins.save()