# BANCO
### Requirements to deploy the project
### Install Postman
### Install DBeaver
### Install Docker
### Install Postgres 16 
### Install Open Java 21 Corretto
https://docs.aws.amazon.com/corretto/latest/corretto-21-ug/downloads-list.html

# To Compile in Docker Both Micro services Client, Accounts and Movement
### Go to this folder in your command line
[Link to Docker file](./deploy/docker/)
### To build docker image in this directory insert following command
- `docker pull gradle:8.4-jdk21`
- `docker pull eclipse-temurin:21`
- `docker compose up -d`

### In DBeaver insert the tables from following folder
#### DBeaver CONFIG
##### Host: localhost
##### Database: postgres
##### username: postgres
##### password: postgres
![alt text](./config/postgreSQL/postgres.png)

### In DBeaver insert the tables from following folder
#### Client SQL
[Link to Client SQL](./client/config/sql/initialize.sql)
#### Account and Movement SQL
[Link to Account and Movement SQL](./account_movement/config/sql/initialize.sql)
### Load Postman collection
[Link to Postman Collection](./config/postman/)

# Documentations of the microservices
## Link to Client README.md
[Link to Client README](./client/README.md)

## Link to Accounts and Movement README.md
[Link to Accounts and Movement README](./account_movement/README.md)

## Postman Folder /config/postman/
[Postman folder directory](./config/postman/)
