## 5.4 Use of PREMIS
The CS IP recommends and advocates the use of the PREMIS metadata standard for recording preservation and technical metadata about digital objects contained within CS IP Information Packages. The CS IP implements version 3.0 of the PREMIS Data Dictionary.  Note that use of PREMIS is not mandatory
because a SIP will not always be able to include preservation metadata.

Although the CS IP allows both the embedding of metadata within the METS file, and its inclusion in the IP in a separate metadata file, we strongly recommend keeping PREMIS metadata in discrete PREMIS XML files inside the IP. If PREMIS metadata is included in the IP in separate files, the naming and numbering of the PREMIS files are not restricted, meaning that implementations can choose to either store all preservation metadata in a single PREMIS file or split them into multiple files. The only requirement in this case is that all PREMIS files must be listed in the appropriate METS file, i.e. root PREMIS files from the root METS file and representation PREMIS files from the representation METS files, and referenced in the METS
file(s) using the mdRef attributes and elements.

Therefore, the main recommendation of the CS IP is that preservation metadata are included in the information package in PREMIS format. Although this is not mandatory, all tools claiming to be able to validate CS IP compliant Information Packages must also be able to validate PREMIS metadata once it exists within the package. The two high level requirements for use of PREMIS in Common Specification IPs are that:

- All preservation metadata is created according to official PREMIS guidelines ;

- All PREMIS metadata is either embedded in or referenced from the amdSec/digiprovMD element of the appropriate METS file.

Further, to enhance the interoperability scope of the CS IP and to strengthen management of IPs in an archive, this specification imposes additional requirements in regard to use of PREMIS for describing Information Packages. The principles adopted in the CS IP for deciding the additional PREMIS semantic units required are:

- PREMIS should be used to record detailed technical metadata. In METS the technical metadata included should only include the checksums and size of files;

- As much technical information as possible should be included in PREMIS metadata by using extension schemas;

- Information about agents carrying out preservation actions should be recorded in PREMIS metadata and not in METS. The use of METS agents should be limited to those agents who are relevant for generic IP level events (for example, the creation of the package, submitting agency);

- Event descriptions should be included in PREMIS metadata as much as possible. Use of the official PREMIS event vocabulary is recommended ;

- Detailed rights information should be included in PREMIS and not described in METS. The METS file should only include information about the whole package – is it totally open, partially restricted, needs review etc.  Where high level rights information in METS indicates restrictions, detailed,
object-specific, rights information will be included in the PREMIS metadata;

- File format information for all files should be included as PUID  values in the appropriate PREMIS semantic units.

In addition to the mandatory semantic units required by PREMIS itself, the CS IP requires, based on the requirements specified above, the following additional semantic units:

| ID | Name | Element/Attribute | Description and usage | Cardinality |
| -- | ---- | ----------------- | --------------------- | ----------- |
| CSIP? | File checksum | 1.5.2 | fixity | PREMIS requires the use of the objectCharacteristics semantic unit but leaves use of the fixity component optional. The CS IP requires fixity for validation of the structure and content of IPs. | 1..n |
| CSIP? | Relationship to other content | 1.13 | relationship | Required by the CS IP for structural, provenance and contextual purposes. | 1..n |
| CSIP? | Event outcome | 2.5 | eventOutcomeInformation | The only place to record the outcome of an event. Needed for authenticity. One of the sub elements, eventOutcome or eventOutcomeDetail, is required. | 1..n |
| CSIP? | Linked agent ID | 2.6 | linkingAgentIdentifier | The CS IP strongly recommends that most agent information be recorded in PREMIS metadata rather than METS, this semantic unit is required for authenticity and archival management purposes. | 1..n |
| CSIP? | Linked object ID | 2.7 | linkingObjectIdentifier | Because event information is recorded in PREMIS, the CS IP requires this semantic unit to link preservation events to objects for contextual metadata and audit logging purposes. | 1..n |
| CSIP? | Agent Name | 3.2 | agentName | Required by the CS IP for recording human readable names for the agents associated with archival events performed on objects. | 1..n |

Table 1: PREMIS elements further restricted within the CS IP

**Vocabularies**

This specification does not present a definitive list of vocabularies for use with PREMIS semantic units but does recommend the use of the Library of Congress vocabularies developed specifically to provide values for various PREMIS semantic units.

In PREMIS each of the entities (objects, events, agents, rights) are identified by a generic set of identifier containers. These containers follow an identical syntax and structure consisting of an [entity]Identifier
container holding two semantic units:

- [entity]IdentifierType
- [entity]IdentifierValue

The PREMIS data dictionary recognizes that the use of identifier types is an implementation specific issue and does not recommend or require particular vocabularies for identifier types. The Library of Congress has developed its own identifier type vocabulary  and the CS IP recommends its use in lieu of implementation specific identifier type vocabularies, where these have not yet been developed.
