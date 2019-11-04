# spring-boot-example
an example project to learn spring boot

## build
```
gradle clean build -q tarz
docker build -t {image}:{tag} .
```


## startup

Spring-Boot-Example service can deploy in VM, docker-compose and kubernetes.

After build success, you will get file : **{project.basepath}/build/spring-boot-example-{version}.tar.gz** and docker image, then you can startup web service

#### VM

1. upload tar.gz to VM
2. decompression
3. setup configuration
4. startup
```
vi conf/application.properties
vi flyway/conf/flyway.conf
./bin/spring-boot-example-start.sh
```

#### docker-compose
```
docker-compose -f spring-boot-example-docker-compose.yaml up -d
```
Notice : Need to set env and docker image name before startup

#### k8s

```
kubectl apply -f spring-boot-example-kubernetes.yaml
```

Notice : Need to set env and docker image name before startup