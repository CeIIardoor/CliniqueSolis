#!/bin/bash

# Change to the appropriate directory where the git repository is cloned
cd /root/CliniqueSolis

# Pull the latest changes from the git repository
git pull

# Build and install the Maven project using the included wrapper
./mvnw install

# Give executable permissions to the generated JAR file
chmod +x /root/CliniqueSolis/target/CliniqueSolis-0.0.1-SNAPSHOT.jar

# Restart the Tomcat service
service tomcat-cliniquesolis restart
