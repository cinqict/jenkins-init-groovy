import hudson.model.*
import jenkins.model.*
import hudson.slaves.*
import hudson.slaves.EnvironmentVariablesNodeProperty.Entry
import hudson.plugins.sshslaves.verifiers.*

// Required plugins: 
// (none)
//

// References:
// - https://support.cloudbees.com/hc/en-us/articles/218154667-Create-a-Permanent-Agent-from-Groovy-Console
// - https://github.com/MovingBlocks/GroovyJenkins/blob/master/src/main/groovy/AddSSHNodeToJenkins.groovy


// Variables
agentName="agent"
agentHost="agent_hostname"
agentSSHCredentialsID="SSH_ID"
agentHome="/var/jenkins_home"
agentNumExecutors=1


// Define a "Permanent Agent"
Slave agent = new DumbSlave(
        agentName,
        agentHome,
        launcher)
agent.nodeDescription = agentName
agent.numExecutors = agentNumExecutors
agent.labelString = agentName
agent.mode = Node.Mode.EXCLUSIVE // "Usage" field, EXCLUSIVE is "only tied to node", NORMAL is "any"
agent.retentionStrategy = new RetentionStrategy.Always()

// Define a "Launch method": "Launch slave agents via SSH"

// Pick one of the strategies from the comments below this line
SshHostKeyVerificationStrategy hostKeyVerificationStrategy = new NonVerifyingKeyVerificationStrategy()
//= new KnownHostsFileKeyVerificationStrategy()
//= new ManuallyProvidedKeyVerificationStrategy("<your-key-here>")
//= new ManuallyTrustedKeyVerificationStrategy(false /*requires initial manual trust*/)
//= new NonVerifyingKeyVerificationStrategy()

ComputerLauncher launcher = new hudson.plugins.sshslaves.SSHLauncher(
        agentHost, // Host
        22, // Port
        agentSSHCredentialsID, // Credentials
        (String)null, // JVM Options
        (String)null, // JavaPath
        (String)null, // Prefix Start Slave Command
        (String)null, // Suffix Start Slave Command
        (Integer)null, // Connection Timeout in Seconds
        (Integer)null, // Maximum Number of Retries
        (Integer)null, // The number of seconds to wait between retries
        hostKeyVerificationStrategy // Host Key Verification Strategy
)

// Create a "Permanent Agent"
Jenkins.instance.addNode(agent)

println("Node has been created successfully.")


// Connect agent
for (slave in hudson.model.Hudson.instance.slaves) {
    if (slave.getNodeName() == agentName) {
        Computer c = slave.getComputer();
        c.connect(true);
    }
}

println("Node has been connected successfully.")
