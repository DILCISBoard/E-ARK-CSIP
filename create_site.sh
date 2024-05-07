#!/usr/bin/env bash
echo "Generating GitHub pages site from markdown"
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$SCRIPT_DIR" || exit-site

echo " - Cleaning up site directory and copying spec-publisher site..."
git clean -f site/ specification/
cp -rf spec-publisher/site/* site/
cp specification/postface/postface.md doc/site/
echo " - Generating main site specification and PDF markdown..."

mvn clean package -f spec-publisher/pom.xml
java -jar ./spec-publisher/target/mets-profile-processor-0.1.0-SNAPSHOT.jar -f ./specification.yaml -o doc/site profile/E-ARK-CSIP.xml

echo " - MARKDOWN-PP: generating site page with TOC..."
cd doc/site || exit
bash "$SCRIPT_DIR/spec-publisher/scripts/create-venv.sh"
command -v markdown-pp >/dev/null 2>&1 || {
  tmpdir=$(dirname "$(mktemp -u)")
  source "$tmpdir/.venv-markdown/bin/activate"
}
markdown-pp body.md -o body_toc.md

echo " - MARKDOWN-PP: generating site index.md..."
markdown-pp SITE.md -o index.md

cd "$SCRIPT_DIR" || exit

echo " - copying files to site directory..."
cp -rf spec-publisher/res/md/figs site/
# Copy remaining collaterel
cp -rf profile archive examples specification/figs site/

if [ -d _site ]
then
  echo " - Removing old site directory"
  rm -rf _site
fi
