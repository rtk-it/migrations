FROM bellsoft/liberica-openjdk-alpine:11
#FROM 2chat/openjdk:liberica-openjdk-alpine-11.0.9.1-1

# Application artifact name (should be updated according to application name in corresponding settings.gradle file)
ARG ARTIFACT=migrations
# Application artifact version (should be updated according to version in corresponding build.gradle file)
ARG VERSION=0.1
# Application artifact type (jar/war)
ARG EXTENSION=jar

# Aplication root directory
RUN mkdir /usr/share/app
# Aplication logs root directory
RUN mkdir /usr/share/app/logs
# Mount logs directory to volume
VOLUME /usr/share/app/logs
# Set application root as a work directory
WORKDIR /usr/share/app/
# Copy artifact <app-name>-<app-version>.jar to /app/root/directory/app.jar
COPY build/libs/${ARTIFACT}-${VERSION}.${EXTENSION} /usr/share/app/app.jar

# entrypoint.sh is a app entrypoint script
# Set up docker entrypoint script
COPY entrypoint.sh /usr/share/app/entrypoint.sh
RUN chmod +x /usr/share/app/entrypoint.sh

# application external port
EXPOSE 8080
# application external debug port
EXPOSE 5050

ENTRYPOINT ["/usr/share/app/entrypoint.sh"]
