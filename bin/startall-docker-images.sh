#!/bin/bash
##
docker stop review
docker rm review

docker stop service
docker rm service

docker run --name mongodb -d mongo 

docker run  -p 8090:8090 --name review --link="mongodb:mongodb" -d uw-test/ubiwhere-establishment-review
  
docker run  -p 8080:8080 --name service --link="review:review"  -d uw-test/ubiwhere-establishment-service
