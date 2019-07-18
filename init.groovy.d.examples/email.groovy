import jenkins.model.*

// Required plugins: 
// - email-ext
//


// Project variables
String smtpServer = "smpt.example.com"
String smtpPort = "25"
String AdminAddress = "jenkins_admin@example.com"        // senders email adress


def jenkins = Jenkins.getInstance()
def mailServer = jenkins.getDescriptor("hudson.tasks.Mailer")
// extmailServer is not needed here since it inherits from mailServer and we only use default parameters
// def extmailServer = jenkins.getDescriptor("hudson.plugins.emailext.ExtendedEmailPublisher")


def jenkinsLocationConfiguration = JenkinsLocationConfiguration.get()
//Jenkins Location
println "--> Configuring JenkinsLocation"
jenkinsLocationConfiguration.setAdminAddress(AdminAddress)
jenkinsLocationConfiguration.save()


mailServer.setSmtpHost(smtpServer)
mailServer.setSmtpPort(smtpPort)
println("--> SMTP server configured")

jenkins.save()