#!/bin/bash

VENV=~/deploy.edge.telekom.com.venv
. ~/.seqam_fh_dortmund_project_emulate/edgexr-secret

export API_HOST
export USER
export PASS

cd "$(dirname "$0")" || exit $?

cd ../.. || exit $?
docker compose push || exit $?
cd - || exit $?

python3 -m venv "$VENV" || exit $?

. "$VENV/bin/activate"

pip install -r requirements.txt

python deploy.py
