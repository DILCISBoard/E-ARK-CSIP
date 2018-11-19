E-ARK Common Specification for Information Packages
===================================================

Quick Start
-----------

### Layout
```
E-ARK-CSIP/
Project root
  |
  |- README.md
  |  This documentation file.
  |
  |- index.md
  |  The Markdown source for the site home page.
  |
  |- archive/
  |  Archive of old versions of the specification (possibly schema, see TODOs).
  |
  |- examples/
  |  Sample information packages showing the Common Specification in use.
  |
  |- profile/
  |  The CSIP METS Profile and supporting documentation.
  |
  |- schema/
  |  DILCIS METS extensions and defined vocabularies for the CSIP.
  |
  |- specification/
  |  The Markdown source for the common specification.
  |
  |- utils/
     Tools and utilities used to create and QA the specification documents.
```

TODO ?
------
- [ ] Decide if we want to retain old schema in the archive. Perhaps the archive should store previous versions of the specification site?
- [ ] Travis build and test
 + [ ] build and test requirement table generator
 + [ ] test generation of requirements and site
 + [ ] broken links test for site
 + [ ] spell check for text, if feasible and useful
- [ ] Navigation for specification documents
  + Possibly via GH pages YAML metadata
