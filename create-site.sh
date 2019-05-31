#!/usr/bin/env bash
echo "Generating GitHub pages site from markdown"
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$SCRIPT_DIR" || exit

if [ ! -d ./docs ]
then
  mkdir ./docs
fi

if [ -d ./docs/figs ]
then
  rm -rf ./docs/figs
fi

if [ -e ./docs/index.md ]
then
  rm docs/index.md
fi

bash "$SCRIPT_DIR/spec-publisher/utils/create-venv.sh"
source "$SCRIPT_DIR/.venv/markdown/bin/activate"
markdown-pp SITE.md -o docs/index.md -e tableofcontents
deactivate

cp -R specification/figs docs/
cp -R spec-publisher/res/md/figs docs/
cp -R profile docs/
cp -R schema docs/
cp -R archive docs/
