#!/bin/bash
##
BASEDIR=$(dirname $0)

cd ${BASEDIR}/../establishment-service/
./mvnw install dockerfile:build


cd ${BASEDIR}/../establishment-review/
./mvnw install dockerfile:build

#mvn -f ${BASEDIR}/../establishment-service/pom.xml install dockerfile:build

#mvn -f ${BASEDIR}/../establishment-review/pom.xml install dockerfile:build
