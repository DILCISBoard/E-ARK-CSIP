#!/usr/bin/env bash
echo "Generating GitHub pages site from markdown"
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$SCRIPT_DIR" || exit

if [ ! -d ./docs ]
then
  echo " - creating docs directory"
  mkdir ./docs
fi

if [ -d ./docs/figs ]
then
  echo " - removing existing figs directory"
  rm -rf ./docs/figs
fi

if [ -d ./docs/profile ]
then
  echo " - removing existing profile directory"
  rm -rf ./docs/profile
fi

if [ -d ./docs/schema ]
then
  echo " - removing existing schema directory"
  rm -rf ./docs/schema
fi

if [ -d ./docs/archive ]
then
  echo " - removing existing archive directory"
  rm -rf ./docs/archive
fi

bash "$SCRIPT_DIR/spec-publisher/utils/create-venv.sh"

command -v markdown-pp >/dev/null 2>&1 || {
  tmpdir=$(dirname $(mktemp -u))
  source "$tmpdir/.venv-markdown/bin/activate"
}

echo " - MARKDOWN-PP: generating temp-site"
markdown-pp SITE_BASE.md -o /tmp/site.md
echo " - MARKDOWN-PP: generating site index.md"
markdown-pp SITE.md -o ./docs/index.md

echo " - copying files to docs directory"
cp -R specification/figs docs/
cp -R spec-publisher/res/md/figs docs/
cp -R profile docs/
cp -R schema docs/
cp -R archive docs/
