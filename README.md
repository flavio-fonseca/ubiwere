# ubiwere

# Build and run review

## linux with docker installed

If you don't have a mongodb running run a instance using

docker run --name mongodb -d mongo

cd establishment-review

To buld and run

./mvnw package && java -jar target/ubiwhere-establishment-review-0.1.0.jar

Access at http://localhost:8090/swagger-ui.html


To buld docker image

./mvnw install dockerfile:build

To run docker image

docker run  -p 8090:8090 --name review --link="mongodb:mongodb" -d uw-test/ubiwhere-establishment-review



# Build and run service

## linux with docker installed

cd establishment-service

To buld and run

./mvnw package && java -jar target/ubiwhere-establishment-service-0.1.0.jar

Access at http://localhost:8080/swagger-ui.html


To buld docker image

./mvnw install dockerfile:build

To run docker image

docker run  -p 8080:8080 --name service --link="review:review"  -d uw-test/ubiwhere-establishment-service


# Usefull scripts at bin

## Build docker images
bin/build-docker-images.sh

## Start all docker images
bin/startall-docker-images.sh 

## Stop all docker images
bin/stopall-docker-images.sh 

