### 	Use of the METS administrative metadata section (element amdSec)
METS defines an administrative metadata section, used to embed metadata or provide references to files containing metadata for the information package content. The CSIP encourages the use of references to metadata files, rather than embedding metadata in the `amdSec` element, and the examples reflect this.

The administrative metadata section contains four sub-sections each used to record different types of metadata for package content:
- technical metadata (element techMD) records technical metadata;
- rights metadata (element rightsMD) records intellectual property rights information;
- source metadata (element sourceMD) records descriptive, technical or rights metadata for an analog source for a digital library object; and
- digital provenance metadata (element digiprovMD) records digital preservation information, e.g. audit information covering a digital library object's life-cycle.

The CSIP only describes use of the elements `digiprovMD` and `rightsMD`. The population of the other metadata sections are left to local policy and practise.

The CSIP (and METS) categorises preservation metadata as administrative metadata, specifically Digital Provenance metadata (following the avaiable guidelines published by the PREMIS EC guidelines: <http://www.loc.gov/standards/premis/guidelines2017-premismets.pdf>), hence all preservation metadata should be referenced from a `digiprovMD` element within the `amdSec`.

The METS `amdSec` element SHOULD include references to all relevant metadata located in the folder “metadata/preservation”. The package level `METS.xml` file SHOULD only reference package level preservation metadata. Representation level `METS.xml` files SHOULD only reference representation level preservation metadata.

The specific requirements for the `amdSec` element, its sub-elements and attributes are presented in the following table.
