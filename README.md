# tsk2

## dev

### db
```
cd infra
docker-compose up -d
```
### IDE
lombokのannotation processorを有効にする  
https://reasonable-code.com/intellij-lombok/

## prd

### 環境構築（EC2）
* make swap 
```
https://qiita.com/madaran0805/items/ae0532a7436e1c684e72
```

* install MySQL client
```
$ sudo rpm -Uvh https://dev.mysql.com/get/mysql80-community-release-el7-1.noarch.rpm
$ sudo yum install mysql-community-client
```

### clone


### java setup
```
$ wget https://download.java.net/java/GA/jdk11/9/GPL/openjdk-11.0.2_linux-x64_bin.tar.gz
$ tar xzvf openjdk-11.0.2_linux-x64_bin.tar.gz
$ sudo mv jdk-11.0.2/ /opt/
$ cat ~/.bash_profile |sed -e "s/.*JAVA_HOME.*/export JAVA_HOME=/opt/jdk-11.0.2/bin/g" > ~/.bash_profile
$ vi ~/.bash_profile
export JAVA_HOME=/opt/jdk-11.0.2/bin
PATH=$PATH:$HOME/.local/bin:$HOME/bin:$JAVA_HOME
$ source ~/.bash_profile
$ java -version
openjdk version "11.0.2" 2019-01-15
OpenJDK Runtime Environment 18.9 (build 11.0.2+9)
OpenJDK 64-Bit Server VM 18.9 (build 11.0.2+9, mixed mode)
```

### system environment variable
```
vi ~/.bash_profile
export MYSQL_URL=jdbc:mysql://mysql_host:3306/tsk2?useSSL=false
export OPENAPI_DOMAIN=api.tsk2.me
export OPENAPI_PORT=80
```

### generate source
```
.tools/generateApiSourcesServer.sh 
```

### build (server)
```
$ cd {srcRootPath}
$ ./gradlew server:bootJar -x generateTablesJooqSchemaSource
```

### deploy
```
$ docker-compose -f docker-compose_prd.yml up -d
```

