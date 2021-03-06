#!/usr/bin/env bash

##############################################################################
##
##  Script for compile test-kafka-avro with local environment and start Docker
##
##############################################################################

YELLOW="\033[0;33m"
BLUE="\033[0;34m"
NC="\033[0m"

# Use platform-agnostic sed
function replaceVal () {
    sed --version >/dev/null 2>&1 && sed -i -- "$@" || sed -i "" "$@"
}

echo -e "${BLUE}[BUILD-SERVICE]${NC} Debugging is enabled per default, use localhost:8791 to connect to container."

# clean build
#./gradlew clean build test iT installDist
./gradlew clean build installDist

# enable debug
sed -i '' 's/DEFAULT_JVM_OPTS=""/DEFAULT_JVM_OPTS="-Xdebug -agentlib:jdwp=transport=dt_socket,address=8787,server=y,suspend=n"/g' build/install/test-kafka-avro/bin/test-kafka-avro

docker-compose -f docker-compose.yml up -d --build
