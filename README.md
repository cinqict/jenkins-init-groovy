# Example Groovy files to Configure Jenkins

A while ago I developed some Groovy files to automaticaly configure Jenkins. Since this is terrribly bad documented it toke me quite some time, hence I think it is worth sharing the code.

## Configure Jenkins using Groovy

Jenkins offers a hook, the `init.groovy.d` directory, where you can add your Groovy code for all initial jenkins configurations.

## Developing Groovy files yourself

The strategy that works best for me is first search for examples which come close to what you want and from there try to look into the source code of Jenkins or the plugin that you use.

- Use the Script Console...

### Test your files

In this repo you find a simple `Dockerfile` which you can use to quickly create and destory a Jenkins server to verify your Groovy file in the `init.groovy.d` directory.

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