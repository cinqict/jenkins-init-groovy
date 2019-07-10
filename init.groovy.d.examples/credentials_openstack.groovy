import jenkins.*
import hudson.*
import hudson.model.*
import jenkins.model.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.common.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.plugins.credentials.impl.*
import com.cloudbees.jenkins.plugins.sshcredentials.impl.*
import jenkins.plugins.openstack.*

// Required plugins: 
// - openstack-cloud
//

// SECURITY WARNING: Dirty way to insert secret
// FOR DEVELOPMENT ONLY
// NOTE: For real use, there should be proper secret management.

// Variables
credentialProjectDomain="ldap"
credentialProjectName="ec1090"
credentialUserDomain="ldap"
credentialUser="ec1090"
credentialPassword=""
credentialId="Openstack_User"
credentialDescription="Openstack User"

//global_domain = Domain.global()
def global_domain = com.cloudbees.plugins.credentials.domains.Domain.global()
credentials_store = Jenkins.instance.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0].getStore()


// Create the OpenstackCredentialv3 credential
def credentials = new jenkins.plugins.openstack.compute.auth.OpenstackCredentialv3(
        CredentialsScope.GLOBAL,
        credentialId,
        credentialDescription,
        credentialUser,
        credentialUserDomain,
        credentialProjectName,
        credentialProjectDomain,
        credentialPassword
)

credentials_store.addCredentials(global_domain, credentials)
