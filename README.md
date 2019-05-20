# Example Groovy files to Configure Jenkins

A while ago I developed some Groovy files to automaticaly configure Jenkins. Since this is terrribly bad documented it toke me quite some time, hence I think it is worth sharing the code.

## Configure Jenkins using Groovy

Jenkins offers a hook, the `init.groovy.d` directory, where you can add your Groovy code for all initial jenkins configurations. In the `init.groovy.d.example` directory you can find groovy examples.

## Developing Groovy files yourself

The strategy that works best for me is first search the web for Groovy examples which come close to what you want and then dive into the source code of Jenkins or the particulair plugin that you want to configure.

You can use the Script Console `http://localhost/script` to test your Groovy code. This way you do not have to restart Jenkins.

### Simple Jenkins

In this repo you find a simple `Dockerfile` based on a previous blog [Building a Jenkins development Docker image](https://github.com/cinqict/jenkinsdev). Using this image, you can quickly create and destory a Jenkins server to verify new plugins and your Groovy files from the `init.groovy.d` directory.

```bash
# Build images
docker build -t jenkinsdev .

# Create container
docker run -it -p 80:8080 jenkinsdev
```

## ToDO

- [x] minimal project setup MVP
  - [x] minimal Dockerfile
  - [x] minimal Groovy file
  - [x] minimal plugin list
  - [x] minimal README
- [ ] Make each Groovy example general
- [ ] write blog / readme
- [ ] Which default groovy file in `init.groovy.d`?
