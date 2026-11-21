#!/bin/bash

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

INIT_ENV_SCRIPT="${SCRIPT_DIR}/bitbake-builds/yocto-assignments-wrynose/build/init-build-env"
if [ ! -e ${INIT_ENV_SCRIPT} ]; then
    ${SCRIPT_DIR}/setup.sh
fi

BUILD_DIR="${SCRIPT_DIR}/bitbake-builds/yocto-assignments-wrynose/build"
rm -f "${BUILD_DIR}/bitbake.lock" "${BUILD_DIR}/bitbake.sock" "${BUILD_DIR}/bitbake.server.log" 2>/dev/null || true

source "${INIT_ENV_SCRIPT}"

bitbake core-image-aesd
