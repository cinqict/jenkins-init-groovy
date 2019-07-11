import jenkins.*
import hudson.*
import hudson.model.*
import jenkins.model.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.common.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.jenkins.plugins.sshcredentials.impl.*

// Required plugins: 
// (none)
//

// SECURITY WARNING: Dirty way to get secret
// FOR DEVELOPMENT ONLY
// NOTE: For real use, there should be proper secret management.


def instance = Jenkins.getInstance()


// General Variables
credentialId="credential_reference_id_key"
credentialDescription="Example credential"
credentialUser="FooBar"
keyPassphrase=""
// Key options: DirectEntryPrivateKeySource
// Put full PEM key here:
key=""


global_domain = Domain.global()
credentials_store = Jenkins.instance.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0].getStore()

credentials = new BasicSSHUserPrivateKey(
        CredentialsScope.GLOBAL,
        credentialId,
        credentialUser,
        // Add correct key option here:
        new BasicSSHUserPrivateKey.DirectEntryPrivateKeySource(key),
        keyPassphrase,
        credentialDescription)

credentials_store.addCredentials(global_domain, credentials)
