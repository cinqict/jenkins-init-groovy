import jenkins.*
import jenkins.model.*
import hudson.*
import hudson.model.*
import hudson.security.*

// Required plugins:
// - ldap

def hudsonRealm = new HudsonPrivateSecurityRealm(false)
def instance = Jenkins.getInstance()

// Authentication
String server = 'ldap.example.com'      // or list two, e.g. 'ldap-1.example.com ldap-2.example.com'
String rootDN = ''                      // e.g. 'DC=example,DC=com'
String userSearchBase = ''              // e.g. 'OU=People'  
String userSearch = ''                  // e.g. 'sAMAccountName={0}'
String groupSearchBase = ''             // e.g. 'OU=Groups'
String managerDN = ''  
String password = 'password'  
boolean inhibitInferRootDN = true

SecurityRealm ldap_realm = new LDAPSecurityRealm(server, rootDN, userSearchBase, userSearch, groupSearchBase, managerDN, password, inhibitInferRootDN)
instance.setSecurityRealm(ldap_realm)

// Authorization
def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
instance.setAuthorizationStrategy(strategy)

instance.save()