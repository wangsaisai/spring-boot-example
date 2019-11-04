#!/usr/bin/env bash

curDir=$(cd `dirname $0`; pwd)
cd $curDir

VERSION=1.0-SNAPSHOT

./gradlew clean build -x test -x check -q tarz
docker build -t spring-boot-example-web:$VERSION .
