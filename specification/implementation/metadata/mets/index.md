## 5.3 Use of METS
The main requirement for METS files in a CSIP Information Package is that these need to follow the official METS Schema version 1.12 (by CSIP used version in November 2018) and the extension schema developed for the CSIP and published by the DILCIS Board. As new versions of METS Schema become available the DILCIS Board will evaluate these and, if necessary, update the CSIP respectively.

The following text assumes knowledge of the principles of the METS specification. If this is not the case, please consult the official documentation  before continuing.

METS allows metadata to be both embedded and referenced. The CSIP itself allows both the embedding of metadata within the METS.xml file but note that for scalability concerns the CSIP only recommends the use of referencing. This means that the CSIP only describes referencing of metadata.

The rest of this Section is structured according to the METS elements: `mets`, `header`, `dmdSec`, `amdSec`, `fileSec` and `structMap`. In each of these sections we explain in a concise way limitations imposed by the CSIP implementation when compared to the official METS documentation. When an implementation of the CSIP is created a choice can be made to extend the limitations with limitations needed by the implementation. If this is the case follow the offical documentation and create an implementation which base is CSIP.

Differences between creating a root METS file and representation METS file are described when relevant.

All names of elements and attributes below are expressed using the XPath notation (i.e. `element/sub-element/@attribute`)

## Contents

- [5.3. Use of METS](/)
  - [5.3.1.	Use of the METS root element (element mets)](mets-root/)
  - [5.3.2.	Use of the METS header (element metsHdr)](metshdr/)
  - [5.3.3 Use of the METS descriptive metadata section (element dmdSec)](dmdsec/)
  - [5.3.4 Use of the METS administrative metadata section (element amdSec)](amdsec/)
  - [5.3.5 Use of the METS file section (element fileSec)](filesec/)
  - [5.3.6 Use of the METS structural map (element structMap)](structmap/)
