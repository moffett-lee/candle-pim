# Docker命令总结
- docker 官网 https://hub.docker.com/
# 镜像命令

命令语法参数解释 ： [OPTIONS] 命令参数  比如 -itd  --name
## pull命令

`docker pull nginx:latest`

## images命令

- `docker images`
- `docker image ls`

## save命令

- `docker save nginx:latest -o nginxtar.tar`

- -o :输出到的文件

## load命令
`docker load -i linux.tar`

常用参数

--input , -i : 指定导入的文件。

--quiet , -q : 精简输出信息。


## search命令
`docker search tomcat`

常用参数

- -f, --filter filter : 过滤输出的内容；
- --limit int ：指定搜索内容展示个数;
- --no-index : 不截断输出内容；
- --no-trunc ：不截断输出内容；

## inspect命令
`docker inspect tomcat:9.0.20-jre8-alpine`

## history命令
`docker history tomcat:9.0.20-jre8-alpine

## tag命令
`docker tag tomcat:9.0.20-jre8-alpine leyuze/tomcat:9`

## rmi命令
`docker rmi tomcat:9.0.20-jre8-alpine `
`docker image rm tomcat:9.0.20-jre8-alpine`
`docker rmi <id>`

## 清理镜像
`docker image prune`

# 容器命令

## 新建并启动容器
`docker run [OPTIONS] IMAGE [COMMAND] [ARG...]`

`docker run -it --rm -p  8080:80 nginx:latest`

常用参数
- -d, --detach=false: 后台运行容器，并返回容器ID
- -i, --interactive=false: 以交互模式运行容器，通常与 -t 同时使用 docker run [OPTIONS] IMAGE [COMMAND] [ARG...] docker run -it --rm -p 8080:8080 tomcat:9.0.20-jre8-alpine
- -P, --publish-all=false: 随机端口映射，容器内部端口随机映射到主机的端口。不推荐各位小伙伴使用该参数
- -p, --publish=[]: 指定端口映射，格式为：主机(宿主)端口:容器端口，推荐各位小伙伴们使用
- -t, --tty=false: 为容器重新分配一个伪输入终端，通常与 -i 同时使用 --name="nginx-lb": 为容器指定一个名称
- -h , --hostname="laosiji": 指定容器的hostname
- -e , --env=[]: 设置环境变量，容器中可以使用该环境变量 --net="bridge": 指定容器的网络连接类型，支持 bridge/host/none/container: 四种类型 --link=[]: 添加链接到另一个容器；不推荐各位小伙伴使用该参数
- -v, --volume : 绑定一个卷 --privileged=false: 指定容器是否为特权容器，特权容器拥有所有的capabilities --restart=no：指定容器停止后的重启策略
- no：容器退出时不重启
- on-failure：容器故障退出（返回值非零）时重启
- always：容器退出时总是重启,推荐各位小伙伴们使用
- --rm=false: 指定容器停止后自动删除容器,不能以docker run -d启动的容器


## 容器日志

`docker logs [OPTIONS] CONTAINER`

`docker run -itd --name nginx -p 80:80 nginx:latest`

`docker logs -f nginx`


## 删除容器
`docker rm [OPTIONS] CONTAINER [CONTAINER...]`

`docker run -itd --name nginx -p 80:80 nginx:latest`

- 需要先停止运行中的容器再删除，否则无法删除容器
- docker stop nginx    docker rm nginx
## 列出容器
- 查看运行中的容器
  `docker ps [OPTIONS]`
- 查看所有容器
  `docker ps -a [OPTIONS]`

## 实用技巧
- 停止所有运行容器 docker stop $(docker ps -qa)
- 删除所有的容器 docker rm $(docker ps -aq) docker rm $(docker stop $(docker ps -q))
- 删除所有的镜像 docker rmi $(docker images -q)


## 创建容器
`docker create [OPTIONS] IMAGE [COMMAND] [ARG...]`

`docker create -it --name nginx -p 80:80 nginx1`


## 启动、重启、终止容器
- docker start :启动一个或多个已经被停止的容器
- docker stop :停止一个运行中的容器
- docker restart :重启容器
- docker start [OPTIONS] CONTAINER [CONTAINER...]
- docker stop [OPTIONS] CONTAINER [CONTAINER...]
- docker restart [OPTIONS] CONTAINER [CONTAINER...]


## 进入容器
`docker exec [OPTIONS] CONTAINER COMMAND [ARG...]`

`有bash命令的linux系统：
例如centos docker run -it --name tomcat9.1 -p 8080:8080 tomcat:9.0.20-jre8-slim docker exec -it tomcat9.1 /bin/bash 没有bash命令的linux系统：例如alpine系统 docker run -it --name tomcat9.2 -p 8081:8080 tomcat:9.0.20-jre8-alpine docker exec -it tomcat9.2 sh
`
## 查看容器
`docker inspect [OPTIONS] NAME|ID [NAME|ID...]`

`docker inspect nginx`
## 更新容器
`docker update [OPTIONS] CONTAINER [CONTAINER...]`

## 杀掉容器
`docker kill [OPTIONS] CONTAINER [CONTAINER...]`

# 安装常用镜像

## 安装mysql

`docker pull mysql:latest`
- 运行
  `docker run -itd --name mysql --restart always --privileged=true -p 3306:3306 -e MYSQL_ROOT_PASSWORD=admin mysql:latest --character-set-server=utf8 --collation-server=utf8_general_ci`

- -e , --env=[]: 设置环境变量，容器中可以使用该环境变量
- 官网中给出进入容器的第三种方式，前边我们学习了/bin/bash，sh
- 向my.cnf文件中追加相关配置项


# docker网络
`docker network ls`

使用命令查看docker网络部分 `docker info`

## 安装brctl
`yum install -y bridge-utils`

`brctl show`

## 多容器之间通讯

```docker
docker run -itd --name nginx1 nginx:1.19.3-alpine 
docker run -itd --name nginx2 nginx:1.19.3-alpine 
docker network inspect bridge 
docker exec -it nginx1 sh ping 172.17.0.2 
  docker exec -it nginx2 sh 
  ping 172.17.0.2 
  ping www.baidu.com 
  ping nginx1
```
## 新建bridge网络
```
  docker network create -d bridge yuze-bridge
```

## 查看网络

  ```
查看网络 – docker network ls 
  # 作用：查看已经建立的网络对象 
  # 命令格式： docker network ls [OPTIONS] 
  # 命令参数(OPTIONS)： -f, --filter filter 过滤条件(如 'driver=bridge’)
  
```
# docker数据卷
## cp命令
```docker
  宿主机文件复制到容器内 
  docker cp [OPTIONS] SRC_PATH CONTAINER:DEST_PATH 
  容器内文件复制到宿主机 
  docker cp [OPTIONS] CONTAINER:SRC_PATH DEST_PATH
```
## 宿主机数据卷
 ```
 docker run -v /宿主机绝对路径目录:/容器内目录 镜像名
```
eg:
```
docker run -itd --name mysql --restart always --privileged=true -p 3306:3306 -e MYSQL_ROOT_PASSWORD=admin -v /data/mysql:/var/lib/mysql mysql:latest -- character-set-server=utf8 --collation-server=utf8_general_ci  
```
## 容器目录权限
  ```
通过 -v 容器内路径： ro rw 改变读写权限 
  ro:readonly 只读 
  rw:readwrite 可读可写 
  docker run -it -v /宿主机绝对路径目录:/容器内目录:ro 镜像名 
  docker run -it -v /宿主机绝对路径目录:/容器内目录:rw 镜像名 
  例如： 
  docker run -d -P --name nginx05 -v yuze1:/etc/nginx:ro nginx 
  docker run -d -P --name nginx05 -v yuze2:/etc/nginx:rw nginx 
  ro 只要看到ro就说明这个路径只能通过宿主机来操作，容器内部是无法操作！
```
eg:

```
  docker run -d -p 8081:8081 --name nexus3 -v /data/nexus3/:/nexus-data/ sonatype/nexus3:3.28.1

```  

## 命名的数据卷
## 匿名数据卷
## 数据卷容器

```
  docker run -d --name data-volume -v /data/nginx:/usr/share/nginx/html -v /data/mysql:/var/lib/mysql centos:7.8.2003
```


##
##
##
##
##
##
##
##