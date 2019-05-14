### 5.3.4.	Use of the METS administrative metadata section (element amdSec)
The purpose of the METS administrative data section is to embed or refer to files containing administrative metadata about the IP content objects. CSIP is only using referencing of files containing administrative metadata. The CSIP (and METS) categorises preservation metadata as administrative metadata, specifically Digital Provenance metadata (following the avaiable guidelines), hence all preservation metadata should be referenced from a digiprovMD element within the amdSec.

The administrative metadata section also contains rights statements. Regardless of used standard used to record the rights metadata it should be referenced from a rightsMD element within the amdSec. Suitable standards include the collaborative work from RIGHTSTATMENTS.ORG <http://rightsstatements.org> , Europena right statements <https://pro.europeana.eu/page/available-rights-statements> , the METS Rights Schema <https://github.com/mets/METS-Rights-Schema> created and maintained by the METS Board, the rights sections of PREMIS <http://www.loc.gov/standards/premis/> as well as any local rights statements.

The METS amdSec element must include references to all relevant metadata which should be located in the “metadata/preservation” folder. This means also that the package level `METS.xml` file must refer only to the package level preservation metadata and the representation `METS.xml` file must refer only to the representation level preservation metadata.

The placement of PREMIS metadata for preservation in this section follows the PREMIS EC guidelines: <http://www.loc.gov/standards/premis/guidelines2017-premismets.pdf>

The specific requirements for the amdSec element, its sub-elements and attributes are presented in the following table.
