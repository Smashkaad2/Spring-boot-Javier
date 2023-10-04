#!/bin/bash

docker run -d --name mysql-db \
    -e MYSQL_ROOT_PASSWORD=secret \
    -e MYSQL_DATABASE=ludaku \
    -e MYSQL_USER=ludaku \
    -e MYSQL_PASSWORD=secret \
    -p 3306:3306 \
    mysql:8.0.34
