# Use an official Ubuntu as a parent image
FROM ubuntu:24.04

# Set environment variables
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV GRADLE_HOME=/opt/gradle
ENV PATH=${GRADLE_HOME}/bin:${JAVA_HOME}/bin:${PATH}

# Update the package list and install necessary packages
RUN apt-get update && apt-get install -y \
    wget \
    unzip \
    curl \
    git \
    openjdk-17-jdk \
    && rm -rf /var/lib/apt/lists/*

# Download and install Gradle
ARG GRADLE_VERSION=7.5.1
RUN wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -P /tmp \
    && unzip -d /opt/gradle /tmp/gradle-${GRADLE_VERSION}-bin.zip \
    && rm /tmp/gradle-${GRADLE_VERSION}-bin.zip

# Set the working directory
WORKDIR /app

# Copy the source code into the container
COPY . .

# Build the application using Gradle
RUN gradle build

# Run the application
#CMD ["java", "-jar", "build/libs/your-app.jar"]
