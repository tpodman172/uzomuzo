#!/usr/bin/env bash

docker run --rm \
    -v ~/uzomuzo/client/api/generated:/local/out \
    -v ~/uzomuzo/api/doc:/local/doc \
        openapitools/openapi-generator-cli generate \
            -i /local/doc/petstore.yaml \
            -g typescript-axios \
            -o /local/out/