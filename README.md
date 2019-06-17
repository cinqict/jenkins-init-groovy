# Example Groovy files to Configure Jenkins

A while ago I developed some Groovy files to configure Jenkins from code. Since this is terribly bad documented it took me quite some time, hence I think it is worth sharing the code.

You can find these Groovy examples in the `init.groovy.d.example` directory.

## Developing Groovy files yourself

Jenkins offers a hook, the `init.groovy.d` directory, where you can add your Groovy code for all initial Jenkins configurations. Since there is no proper documentation, you have basically two options to create your own Groovy files: search for examples in GitHub repo's or dive into the code of Jenkins or a particular plugin.

If you are not a developer, like me, don't be scared of the Jenkins code or it's plugins. Just look and try to find the classes and methods that are used in the Groovy file you have found. When you recognize them, you will be able to make modifications yourself. This is how I've made most of these examples.

Jenkins contains a Script Console `http://localhost/script`, which offers a simple way to run your Groovy. Since it configures Jenkins directly, this is the fastest way to get feedback.

If you prefer a more isolated Jenkins you can use the `Dockerfile` in this repo. This is just a simple Jenkins server based on a previous blog [Building a Jenkins development Docker image](https://cinqict.nl/building-a-jenkins-development-docker-image/). Using this image, you can quickly create and destroy your own Jenkins server to verify your Groovy files or test new plugins. You can use the `plugin.txt` file to install the needed plugins.

```bash
# Build images
docker build -t jenkinsdev .

# Create container
docker run -it --rm -p 80:8080 jenkinsdev
```

When you have created your own Groovy code, feel free to commit them the repo!

## References

Here are other repositories which contain nice Groovy examples to configure Jenkins. Some of my example are based on these.

- [samrocketman](https://github.com/samrocketman/jenkins-bootstrap-shared/tree/master/scripts)
- [MoveingBlocks](https://github.com/MovingBlocks/GroovyJenkins/tree/master/src/main/groovy)
- [peterjenkins1](https://github.com/peterjenkins1/jenkins-scripts)
- [hayderimran7](https://github.com/hayderimran7/useful-jenkins-groovy-init-scripts)
