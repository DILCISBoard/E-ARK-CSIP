#!/usr/bin/env bash
echo "Generating PDF document from markdown"
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$SCRIPT_DIR" || exit
bash "$SCRIPT_DIR/spec-publisher/utils/create-venv.sh"
source "$SCRIPT_DIR/.venv/markdown/bin/activate"
markdown-pp PDF.md -o docs/eark-csip-pdf.md -e tableofcontents
deactivate
if [ ! -d ~/.pandoc/templates ]
then
  mkdir -p ~/.pandoc/templates
fi
cp spec-publisher/pandoc/templates/eisvogel.latex ~/.pandoc/templates/eisvogel.latex

if [ ! -d "$SCRIPT_DIR/docs/pdf" ]
then
  mkdir -p "$SCRIPT_DIR/docs/pdf/"
fi

cd docs || exit
pandoc  --from markdown \
        --template eisvogel \
        --listings \
        --toc \
        --metadata-file ../pandoc/metadata.yaml \
        eark-csip-pdf.md \
        -o /pdf/eark-csip.pdf

cd "$SCRIPT_DIR" || exit
# if [ -e docs/eark-csip-pdf.md ]
# then
#   rm docs/eark-csip-pdf.md
# fi
