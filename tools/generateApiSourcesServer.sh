#!/usr/bin/env bash

ROOT=$(pwd)
TOOLS_PATH=$(dirname $0)
TARGET_FILE_PATH=${ROOT}/${TOOLS_PATH}"/../api/doc"
SERVER_FILE_PATH=${ROOT}/${TOOLS_PATH}"/../server"
PACKAGE="com.tpodman172.tsk2.server.api"

PACKAGE_PATH=${PACKAGE} | tr '.' '/'

createApiGen() {
  docker run --rm \
    -v ${TARGET_FILE_PATH}:/local/document \
    -v ${SERVER_FILE_PATH}/src/generated/java/${PACKAGE_PATH}:/local/out/src/main/java/${PACKAGE_PATH} \
    openapitools/openapi-generator-cli:v3.3.4 \
    sh -c "rm -rf /local/out/src/main/java/${PACKAGE_PATH}* && \
    /usr/local/bin/docker-entrypoint.sh generate \
    -i /local/document/index.yaml \
    -g spring \
    -o /local/out \
    --additional-properties useTags=true \
    --additional-properties interfaceOnly=true \
    --additional-properties dateLibrary=java8 \
    --model-package ${PACKAGE}.appService.model \
    --api-package ${PACKAGE}.controller
    "
}

createApiGen