# Create PDF using pandoc

## Installation

Make sure latex is available. Extra packages are required:

    sudo apt-get install texlive texlive-base texlive-latex-extra texlive-fonts-extra

Download and install pandoc (latest release 2.5):

    https://github.com/jgm/pandoc/releases/tag/2.5

Install on Debian/Ubuntu linux:

    wget https://github.com/jgm/pandoc/releases/download/2.5/pandoc-2.5-linux.tar.gz
    tar -xzvf pandoc-2.5-linux.tar.gz
    sudo mv pandoc-2.5 /opt
    sudo chmod +x /opt/pandoc-2.5/bin/pandoc
    sudo ln -s /opt/pandoc-2.5/bin/pandoc /usr/local/bin/pandoc
    sudo ln -s /opt/pandoc-2.5/bin/pandoc-citeproc /usr/local/bin/pandoc-citeproc

Create the pandoc templates directory if it does not exist:

    mkdir -p ~/.pandoc/templates/

Copy the latex template to pandoc's template directory:

    cp pandoc/templates/eisvogel.latex ~/.pandoc/templates/

## Run

Run `pandoc` from the project's root folder (where the markdown source file `index.md` is located):

    pandoc --include-before-body pandoc/include-before.tex \
        --reference-links \
        --filter pandoc-citeproc \
        --from markdown \
        --template eisvogel \
        --listings \
        --toc \
        --metadata pandoc/metadata.yaml \
        --number-sections \
        pandoc/pdf.md \
        -o csip-specification.pdf

## Configuration

* Pandoc parameters are defined in the `pandoc/metadata.yaml` metadata file.
* Sections which are not listed in the table of contents, are included in the `pandoc/include-before.tex` file

## Troubleshooting

Error:

    Error running filter pandoc-citeproc:
    Could not find executable pandoc-citeproc

Make sure `pandoc-citeproc` is in the executable path:

    sudo ln -s /opt/pandoc-2.5/bin/pandoc-citeproc /usr/local/bin/pandoc-citeproc

## Acknowledgement

Latex template based on: https://github.com/Wandmalfarbe/pandoc-latex-template
