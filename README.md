# Example Groovy files to Configure Jenkins

A while ago I developed some Groovy files to configure Jenkins from code. Since this is terribly bad documented it took me quite some time, hence I think it is worth sharing the code.

## Configure Jenkins using Groovy

Jenkins offers a hook, the `init.groovy.d` directory, where you can add your Groovy code for all initial Jenkins configurations. Since there is no proper documentation, you have basicly two options to create your own Groovy files: search for github repo's with examples or dive into Jenkins or plugin code.

If you are not a developer, like me, don't be scared of this code. Just look and try to find the classes and methods that are used in the Groovy file you have found. When you recognize them, you will be able to make modifications yourself.

In the `init.groovy.d.example` directory you can find Groovy examples which I have created this way.

## Developing Groovy files yourself

Jenkins contains a Script Console `http://localhost/script`, offers a simple way to run your Groovy directly. This will give you the fastest feedback, but it is also applied to your Jenkins instance.

If you prefer a more isolated Jenkins you can use the `Dockerfile` in this repo. This is just a simple Jenkins server based on a previous blog [Building a Jenkins development Docker image](https://github.com/cinqict/jenkinsdev). Using this image, you can quickly create and destroy your own Jenkins server to verify your Groovy files or test new plugins.

Note that when you import a plugin class in your Groovy file, be sure to add the plugin also to the `plugin.txt` file.

```bash
# Build images
docker build -t jenkinsdev .

# Create container
docker run -it --rm -p 80:8080 jenkinsdev
```

## References

Here are some other repositories which contain nice Groovy examples to configure Jenkins.

- [samrocketman](https://github.com/samrocketman/jenkins-bootstrap-shared/tree/master/scripts)
- [MoveingBlocks](https://github.com/MovingBlocks/GroovyJenkins/tree/master/src/main/groovy)
- [peterjenkins1](https://github.com/peterjenkins1/jenkins-scripts)
- [hayderimran7](https://github.com/hayderimran7/useful-jenkins-groovy-init-scripts)

## ToDO

- [x] Rewrite blog
- [x] Review