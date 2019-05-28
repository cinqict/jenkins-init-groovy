# Example Groovy files to Configure Jenkins

A while ago I developed some Groovy files to configure Jenkins from code. Since this is terribly bad documented it took me quite some time, hence I think it is worth sharing the code.

## Configure Jenkins using Groovy

Jenkins offers a hook, the `init.groovy.d` directory, where you can add your Groovy code for all initial Jenkins configurations.

Since there is no proper documentation you have basicly two options when you want to create your own Groovy files: search for github repo's with examples or dive into Jenkins or plugin code.

In the `init.groovy.d.example` directory you can find Groovy examples which you can use.

## Developing Groovy files yourself

You can use the Script Console `http://localhost/script` to test your Groovy code. This way you do not have to restart Jenkins.

Note that when you import a plugin class in your Groovy file, be sure to add the plugin also to the `plugin.txt` file.

### Simple Jenkins

In this repo you find a simple `Dockerfile` based on a previous blog [Building a Jenkins development Docker image](https://github.com/cinqict/jenkinsdev). Using this image, you can quickly create and destroy a Jenkins server to verify new plugins from `plugins.txt` and your Groovy files from the `init.groovy.d` directory.

```bash
# Build images
docker build -t jenkinsdev .

# Create container
docker run -it --rm -p 80:8080 jenkinsdev
```

## References

Here are some other repositories with Groovy files.

- [samrocketman](https://github.com/samrocketman/jenkins-bootstrap-shared/tree/master/scripts)
- [MoveingBlocks](https://github.com/MovingBlocks/GroovyJenkins/tree/master/src/main/groovy)
- [peterjenkins1](https://github.com/peterjenkins1/jenkins-scripts)
- [hayderimran7](https://github.com/hayderimran7/useful-jenkins-groovy-init-scripts)

## ToDO

- [ ] Rewrite blog

## OLD

The strategy that works best for me is first search the web for Groovy examples which come close to what you want. You can then try to write some Groovy yourself or, if you have some minimal programming knowledge, look in the source code of Jenkins or the particular plugin and see if there are any methods or other classes that can help.