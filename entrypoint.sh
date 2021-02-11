#!/bin/sh -x
set -e

if [ -z "$SPRING_DATASOURCE_URL" ]; then
  SPRING_DATASOURCE_URL=jdbc:postgresql://postgres.backend:5432/migrations_prod
fi

if [ -z "$SPRING_DATASOURCE_USERNAME" ]; then
  SPRING_DATASOURCE_USERNAME=migrations
fi

if [ -z "$SPRING_DATASOURCE_PASSWORD" ]; then
  SPRING_DATASOURCE_PASSWORD=migrations
fi

if [ -z "$SERVER_PORT" ]; then
  SERVER_PORT=8080
fi

if [ -z "$SPRING_APPLICATION_JSON" ]; then
  SPRING_APPLICATION_JSON='
{
  "spring": {
    "profiles": {
      "active": "prod"
    }
  }
}'
  SPRING_APPLICATION_JSON=$(echo $SPRING_APPLICATION_JSON | sed -e 's/\r//g')
  SPRING_APPLICATION_JSON=$(echo $SPRING_APPLICATION_JSON | sed -e 's/ //g')
fi

exec java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar \
 -Dspring.application.json=${SPRING_APPLICATION_JSON} \
 -Dserver.port=${SERVER_PORT} \
 -Dspring.datasource.url=${SPRING_DATASOURCE_URL} \
 -Dspring.datasource.username=${SPRING_DATASOURCE_USERNAME} \
 -Dspring.datasource.password=${SPRING_DATASOURCE_PASSWORD} \
 app.jar
