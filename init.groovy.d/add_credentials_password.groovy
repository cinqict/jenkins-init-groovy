import jenkins.*
import hudson.*
import hudson.model.*
import jenkins.model.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.common.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.plugins.credentials.impl.*
import com.cloudbees.jenkins.plugins.sshcredentials.impl.*


// SECURITY WARNING: Dirty way to get secret
// FOR DEVELOPMENT ONLY
// NOTE: For real use, there should be proper secret management.


// Variables
credentialId="credential_reference_id"
credentialUser="FooBar"
credentialPassword="secret"
credentialDescription="Example credential username and password"


def instance = Jenkins.getInstance()

global_domain = Domain.global()
credentials_store = Jenkins.instance.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0].getStore()

credentials = new UsernamePasswordCredentialsImpl(
        CredentialsScope.GLOBAL,
        credentialId,
        credentialDescription,
        credentialUser,
        credentialPassword)

credentials_store.addCredentials(global_domain, credentials)
println "--> Added credential"
