#!/usr/bin/env bash
echo "Generating PDF document from markdown"
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$SCRIPT_DIR" || exit

echo "Generating PDF from markdown"
bash "$SCRIPT_DIR/spec-publisher/utils/create-venv.sh"

command -v markdown-pp >/dev/null 2>&1 || {
  tmpdir=$(dirname "$(mktemp -u)")
  # shellcheck source=/tmp/.venv-markdown/bin/activate
  source "$tmpdir/.venv-markdown/bin/activate"
}
echo " - MARKDOWN-PP: Preparing Postface markdown"
markdown-pp "./specification/postface/postface-pdf.md" -o ./docs/postface.md -e tableofcontents

cd docs || exit

echo " - PANDOC: Generating Preface from markdown"
pandoc  --from gfm \
        --to latex \
        --metadata-file "../spec-publisher/pandoc/metadata.yaml" \
        "../spec-publisher/res/md/common-intro.md" \
        -o "./preface.tex"
sed -i 's%fig_1_dip.svg%fig_1_dip.png%' ./preface.tex
sed -i 's%section{%section*{%' ./preface.tex

echo " - PANDOC: Generating Postface from markdown"
pandoc  --from markdown \
        --to latex \
        --metadata-file "../spec-publisher/pandoc/metadata.yaml" \
        "./postface.md" \
        -o "./postface.tex"
sed -i 's%section{%section*{%' ./postface.tex
rm postface.md

cd "$SCRIPT_DIR" || exit

if [ ! -d "$SCRIPT_DIR/docs/pdf" ]
then
  echo " - creating docs/pdf directory"
  mkdir -p "$SCRIPT_DIR/docs/pdf/"
fi

command -v markdown-pp >/dev/null 2>&1 || {
  tmpdir=$(dirname "$(mktemp -u)")
  # shellcheck source=/tmp/.venv-markdown/bin/activate
  source "$tmpdir/.venv-markdown/bin/activate"
}
echo " - MARKDOWN-PP: Preparing PDF markdown"
markdown-pp PDF.md -o docs/eark-csip-pdf.md -e tableofcontents
sed -i 's%fig_2_csip_scope.svg%fig_2_csip_scope.png%' docs/eark-csip-pdf.md

cp -R specification/figs docs/
cp -R spec-publisher/res/md/figs docs/

cd docs || exit

echo " - PANDOC: Generating PDF document from markdown and Tex sources"
pandoc  --from markdown \
        --template ../spec-publisher/pandoc/templates/eisvogel.latex \
        --listings \
        --table-of-contents \
        --metadata-file "../spec-publisher/pandoc/metadata.yaml" \
        --include-before-body "./preface.tex" \
        --include-after-body "./postface.tex" \
        --number-sections \
        eark-csip-pdf.md \
        -o "./pdf/eark-csip.pdf"

rm eark-csip-pdf.md preface.tex postface.tex

echo " - Finished"
