# banco


 
## Requirements to deploy the project
## Install Postgres 16
## Install Open Java 21 Corretto

## Client Structure database from /config/sql/
[SQL folder directory](./client/config/sql/)

## Client Postman Folder /config/postman/
[Postman folder directory](../client//config/postman/)

## Swagger Api Call
http://localhost/banco/swagger-ui/index.html#/
## Install Localstack
[Localstack directory](./config/docker/)
[Localstack Documentation](./config/docker/README.md)

## Requirements to deploy the project
## Install Postgres 16 
## Install Open Java 21 Corretto
https://docs.aws.amazon.com/corretto/latest/corretto-21-ug/downloads-list.html

## Create database from /config/sql/
[SQL folder directory](./config/sql/)

## Postman Folder /config/postman/
[Postman folder directory](./config/postman/)


## Swagger Api Call
- `http://172.20.96.1:5066/ec-car-sales/swagger-ui/index.html#/`

## SDK man instalation
https://sdkman.io/

## Install sdk man
- `curl -s "https://get.sdkman.io" | bash`

## Gradle instalation 
https://gradle.org/install/

## Install gradle 8.8
- `sdk install Gradle 8.8`

#### Docker commands to start
[Docker commands](./deployment/docker/README.md)

#### AWS commands to create users, policies and roles
[AWS commands to create permissions](./config/secrets/README.md)

#### AWS S3 commands
[AWS S3 commands](./config/s3/README.md)

## Steps to follow to run in dev machine

### To clean app
- `gradle clean`

### Before compiling
- `gradle task --all`

### To build app
- `gradle build`

### Run tests
- `gradle test`
 
#### run this command on the command line to run in your local machine
- `gradle bootRun`

#### check network ip in windows
- `netsh interface ipv4 show neighbors`
#### check network ip in debian
- `ip addr  `
- `ifconfig `


