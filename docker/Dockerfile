FROM maven:3

MAINTAINER Florian Lopes <florian.lopes@outlook.com>

WORKDIR /app

COPY ../pom.xml ./

# Install needed tests dependencies, enable Docker to cache dependencies
RUN mvn install clean --fail-never -B -DfailIfNoTests=false

COPY ../ ./