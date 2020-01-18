#!/usr/bin/env bash

ROOT=$(pwd)
TOOLS_PATH=$(dirname $0)

docker run --rm \
    -v ${ROOT}/${TOOLS_PATH}/../client/api/generated:/local/out \
    -v ${ROOT}/${TOOLS_PATH}/../api/doc:/local/doc \
        openapitools/openapi-generator-cli generate \
            -i /local/doc/index.yaml \
            -g typescript-axios \
            -o /local/out/ \
            --server-variables environment=${OPENAPI_DOMAIN:-localhost} \
            --server-variables port=${OPENAPI_PORT:-8080}