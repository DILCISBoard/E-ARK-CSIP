## 5.3 Use of METS
The main requirement for METS files in a CSIP Information Package is that these need to follow the official METS Schema version 1.12 <http://www.loc.gov/standards/mets/mets-schemadocs.html> (by CSIP used version in May 2019) and the extension schema developed for the CSIP and published by the DILCIS Board. As new versions of METS Schema become available the DILCIS Board will evaluate these and, if necessary, update the CSIP respectively.

The following text assumes knowledge of the principles of the METS specification. If this is not the case, please consult the official documentation  before continuing.

METS allows metadata to be both embedded and referenced. The CSIP itself allows both the embedding of metadata within the `METS.xml` file but note that for scalability concerns the CSIP only recommends the use of referencing. This means that the CSIP only describes referencing of metadata.

The rest of this Section describes the use of each of the METS elements: mets, metsHdr, dmdSec, amdSec, fileSec and structMap. For each element we concisely explain restrictions imposed by the CSIP on top or those in the official METS documentation. A CSIP implementation can choose to extend the restrictions in line with requirements specific to the implementation. In these cases follow the METS documentation and create an implementation which uses the CSIP as a base profile.

Differences between creating a package METS file and representation METS file are described when relevant. 

All names of elements and attributes below are expressed using the XPath notation (i.e. `element/sub-element/@attribute`)
