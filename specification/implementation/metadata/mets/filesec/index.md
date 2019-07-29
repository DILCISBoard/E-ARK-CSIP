### 	Use of the METS file section (element fileSec)
The CSIP does not make the use of the METS fileSec element mandatory, but it is strongly recommended. The fileSec should describe every component of the IP not already described in the amdSec and dmdSec elements. Location and checksum values must be provided for all file entries.

The METS file section serves as a manifest, allowing users to ensure all files are present and that a package is complete, as well as testing the integrity of package files using checksum values.

The files are described in a file group element named `fileGrp`. The `fileGrp` element contains the descriptions of the files including their checksum and location. It is possible to nest `fileGrp` elements within other `fileGrp` elements, however the CSIP states that hierarchical nesting SHOULD NOT be used.

The CSIP requires that both package and representation level METS files include at least three file group (fileGrp) elements. The CSIP divides the referenced files into categories, i.e. documentation. schemas, data, each contained within its own fileGrp element. It is possible to add own additional file groups following the same file groups requirements described in CSIP. Representation level METS files should not reference files outside of their representation. That is they should not contain references to package level files or files from other representations. The package level METS file should contain a fileGrp element for each representation which contains a single reference to the representation's METS.xml file. This is to ensure that the package lists all representations and their locations for completeness and integrity checks.

The specific requirements for elements, sub-elements and attributes are listed in the following table. Note that the use of the stream and transformFile elements is not discussed below. Implementers wishing to use either of these METS elements should follow the requirements in the METS documentation.
