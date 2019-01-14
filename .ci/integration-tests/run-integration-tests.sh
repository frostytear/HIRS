#!/bin/bash

# Script to run the Integration Tests for HIRS

set -e

# Start Integration Testing Docker Environment
# docker-compose -f .ci/docker/docker-compose.yml up -d

# Check to see if Environment Stand-Up is Complete
# TODO: Refine to handle multiple container IDs
#container_id_regex='([a-f0-9]{12})\s+hirs\/hirs-ci:tpm2provisioner'
#while : ; do
#    docker_containers=$(docker container ls)
#    if [[ $docker_containers =~ $container_id_regex ]]; then
#        container_id=${BASH_REMATCH[1]}
#        break
#    fi
#    echo "Containers not found. Waiting 5 seconds."
#    sleep 5
#done

#tpm2_provisioner_started_regex='TPM2 Provisioner Loaded!'
#while : ; do
#    docker_logs=$(docker logs $container_id)
#    if [[ $docker_logs =~ $tpm2_provisioner_started_regex ]]; then
#        break
#    fi
#    echo "Containers not completely booted. Waiting 10 seconds."
#    sleep 10
#done

docker run --rm --privileged -v /lib/modules:/lib/modules -v /usr/src/kernels:/usr/src/kernels:ro -v ../../:/HIRS hirs/hirs-ci:tpmprovisioner /bin/bash -c "/HIRS/.ci/integration-tests/setup-tpmprovisioner.sh"

echo "Environment Stand-Up Complete!"
