## Use of PREMIS
The CSIP recommends and advocates the use of the PREservation Metadata Implementation Strategies (PREMIS, <http://www.loc.gov/standards/premis/>) metadata standard for recording preservation and technical metadata about digital objects contained within CSIP Information Packages. The CSIP implements version 3.0 of the PREMIS Data Dictionary. Note that use of PREMIS is not mandatory.

It is strongly recommended that PREMIS metadata is kept in discrete PREMIS XML files inside the IP. The PREMIS metadata can be included in the IP in separate files, and there is no convention regarding the naming and numbering of the PREMIS files. Implementations can choose to either store all preservation metadata in a single PREMIS file or split them into multiple files. In this case, the only requirement is that all PREMIS files must be listed in the appropriate METS file, i.e. package PREMIS files from the package METS file and representation PREMIS files from the representation METS files, and referenced in the METS file(s) using the mdRef attributes and elements.

Therefore, the main recommendation of the CSIP is that preservation metadata are included in the information package in PREMIS format. Although this is not mandatory, all tools that claim to be able to validate CSIP compliant Information Packages must also be able to validate PREMIS metadata once it exists within the package. The two high-level requirements for using PREMIS in Common Specification IPs are that:

- All preservation metadata is created according to official PREMIS guidelines;

- All PREMIS metadata is referenced from the amdSec/digiprovMD element of the appropriate METS file.

Further, to enhance the interoperability scope of the CSIP and to strengthen the management of IPs in an archive, this specification imposes additional requirements with regard to use of PREMIS for describing Information Packages. The principles adopted in the CSIP for deciding the additional PREMIS semantic units required are:

- PREMIS should be used to record detailed technical metadata;

- Technical information should be included in PREMIS metadata by using the possibility of extension schemas in PREMIS;

- Information about agents carrying out preservation actions must be recorded in the PREMIS metadata (e.g. the METS agents describe agents that are relevant for generic IP level events, such as the creation or submission of the package);

- Event descriptions should be included in PREMIS metadata. Use of the official PREMIS event vocabulary (<http://id.loc.gov/vocabulary/preservation/eventType.html>) is recommended;

- Detailed rights information should be included in PREMIS. High-level rights information in METS indicates restrictions. Detailed, object-specific, rights information will be included in the PREMIS metadata;

- File format information for all files should be included as PUID values in the appropriate PREMIS semantic units.

### Vocabularies

This specification does not present a definitive list of vocabularies for use with PREMIS semantic units but does recommend the use of the Library of Congress vocabularies developed specifically to provide values for various PREMIS semantic units. All relevant vocabularies are presented in the PREMIS Data Dictionary.

### Identifiers

In PREMIS each of the entities (objects, events, agents, rights) are identified by a generic set of identifier containers. These containers follow an identical syntax and structure consisting of an [entity]Identifier container holding two semantic units:

- [entity]IdentifierType
- [entity]IdentifierValue

The PREMIS data dictionary recognises that the use of identifier types is an implementation-specific issue and does not recommend or require particular vocabularies for identifier types. The Library of Congress has a Standard Identifiers Scheme: <http://id.loc.gov/vocabulary/identifiers.html> and the CSIP recommends its use in lieu of implementation-specific vocabularies.
