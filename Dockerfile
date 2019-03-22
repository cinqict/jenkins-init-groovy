# Extended from https://github.com/jenkinsci/docker/blob/master/README.md
FROM jenkins/jenkins:lts

# Skip setup wizard
ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

# Get plugins
# Strangely, the defaut JENKINS_UC url does not work for the install-plugins.sh script?
ENV JENKINS_UC="http://updates.jenkins-ci.org"
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

# Add groovy script to Jenkins hook
COPY --chown=jenkins:jenkins init.groovy.d/ /var/jenkins_home/init.groovy.d/

