#!/usr/bin/env bash
#!/usr/bin/env bash
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$SCRIPT_DIR" || exit

echo "PANDOC: Processing postface markdown"
tmpdir=$(dirname $(mktemp -u))
if [ ! -d "$tmpdir/.venv-markdown/" ]
then
  virtualenv -p python3 "$tmpdir/.venv-markdown"
  source "$tmpdir/.venv-markdown/bin/activate"
  pip install markdownPP
  deactivate
fi

source "$tmpdir/.venv-markdown/bin/activate"
markdown-pp "./specification/postface/postface-pdf.md" -o ./docs/postface.md -e tableofcontents
deactivate

cd docs || exit

###
# Pandoc options:
# --from markdown \                               # Source fromat Markdown
# --template ../pandoc/templates/eisvogel.latex \ # Use this latex template
# --listings \                                    # Use listings package for code blocks
# --table-of-contents \                           # Generate table of contents
# --metadata-file ../pandoc/metadata.yaml \       # Additional Pandoc metadata
# --include-before-body "../spec-publisher/res/md/common-intro.md" \
# --include-after-body "../specification/postface/postface.md" \
# --number-sections \                             # Generate Heading Numbers
# eark-csip-pdf.md \                              # Input Markdown file
# -o ./pdf/eark-csip.pdf                          # PDF Destinaton
echo "PANDOC: Generating Preface document from markdown"
pandoc  --from gfm \
        --to latex \
        --metadata-file "../spec-publisher/pandoc/metadata.yaml" \
        "../spec-publisher/res/md/common-intro.md" \
        -o "./preface.tex"
echo "PANDOC: Finished"
sed -i 's%fig_1_dip.svg%fig_1_dip.png%' ./preface.tex
sed -i 's%section{%section*{%' ./preface.tex

echo "PANDOC: Generating Postface document from markdown"
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
  mkdir -p "$SCRIPT_DIR/docs/pdf/"
fi

echo "MARKDOWN-PP: Preparing PDF markdown"
source "$tmpdir/.venv-markdown/bin/activate"
markdown-pp PDF.md -o docs/eark-csip-pdf.md -e tableofcontents
deactivate
sed -i 's%fig_2_csip_scope.svg%fig_2_csip_scope.png%' docs/eark-csip-pdf.md

cp -R specification/figs docs/
cp -R spec-publisher/res/md/figs docs/

cd docs || exit

###
# Pandoc options:
# --from markdown \                               # Source fromat Markdown
# --template ../pandoc/templates/eisvogel.latex \ # Use this latex template
# --listings \                                    # Use listings package for code blocks
# --table-of-contents \                           # Generate table of contents
# --metadata-file ../pandoc/metadata.yaml \       # Additional Pandoc metadata
# --include-before-body "../spec-publisher/res/md/common-intro.md" \
# --include-after-body "../specification/postface/postface.md" \
# --number-sections \                             # Generate Heading Numbers
# eark-csip-pdf.md \                              # Input Markdown file
# -o ./pdf/eark-csip.pdf                          # PDF Destinaton
echo "PANDOC: Generating PDF document from markdown"
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
echo "PANDOC: Finished"

rm eark-csip-pdf.md preface.tex postface.tex

cd "$SCRIPT_DIR" || exit
