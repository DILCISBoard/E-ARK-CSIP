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

if [ -d ./docs/pdf ]
then
  echo " - removing existing pdf directory"
  rm -rf ./docs/pdf]
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

if [ -d ./docs/_data ]
then
  echo " - removing existing menu data"
  rm -rf ./docs/_data
fi

if [ -d ./docs/_includes ]
then
  echo " - removing existing includes"
  rm -rf ./docs/_includes
fi

if [ -d ./docs/_layouts ]
then
  echo " - removing existing layouts"
  rm -rf ./docs/_layouts
fi

if [ -d ./docs/img ]
then
  echo " - removing existing images"
  rm -rf ./docs/img
fi

if [ -d ./docs/css ]
then
  echo " - removing existing css"
  rm -rf ./docs/css
fi

if [ -d ./docs/release-notes ]
then
  echo " - removing existing release notes"
  rm -rf ./docs/release-notes
fi
mkdir ./docs/release-notes
cp ./RELEASENOTES.md ./docs/release-notes/index.md

bash "$SCRIPT_DIR/spec-publisher/utils/create-venv.sh"

command -v markdown-pp >/dev/null 2>&1 || {
  tmpdir=$(dirname "$(mktemp -u)")
  # shellcheck source=/tmp/.venv-markdown/bin/activate
  source "$tmpdir/.venv-markdown/bin/activate"
}

echo " - MARKDOWN-PP: generating temp-site"
markdown-pp SITE_BASE.md -o /tmp/site.md
echo " - MARKDOWN-PP: generating site index.md"
markdown-pp SITE.md -o ./docs/index.md

echo " - copying files to docs directory"
cp -R specification/figs docs/
cp -R spec-publisher/res/md/figs docs/
cp -Rf spec-publisher/site/* docs/
cp -R profile docs/
cp -R schema docs/
cp -R archive docs/
cp -R examples docs/
