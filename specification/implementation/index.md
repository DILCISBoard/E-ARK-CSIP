---
title: Implementation of the CSIP
---
# PART II: Implementation of the CSIP

In this part of the document we present an implementation of the requirements and principles discussed in Part I of the CS IP. The implementation consists of two core elements: a fixed physical structure of a CS IP Information Package (Section 4) and the exact use of metadata in METS and PREMIS format (Section 5).

As explained above, any implementation is destined to be outdated sooner or later. However, the authors of the CS IP have made their best effort to reuse already available best practices and established core standards, and to carry out intensive discussions within the digital preservation community. All of the above should guarantee that the implementation can be used with only minor updates (for example minor updates to metadata elements) throughout the next few decades.

## Contents

- [4.	CS IP structure](structure/)
  - [4.1 Folder structure of the CS IP](structure/folders)
  - [4.2 Implementing the structure](structure/implementation)
- [5. Use of metadata](metadata/)
	- [5.1. General requirements for metadata in a CS IP Information Package](metadata/general-requirments/#51-general-requirements-for-metadata-in-a-cs-ip-information-package)
	- [5.2. General requirements for the use of metadata](metadata/general-requirments/#52-general-requirements-for-the-use-of-metadata)
	- [5.3. Use of METS](metadata/mets/use)
    - [5.3.1.	Use of the METS root element (element mets)](metadata/mets/mets-root/)
    - [5.3.2.	Use of the METS header (element metsHdr)](metadata/mets/metshdr/)
    - [5.3.3 Use of the METS descriptive metadata section (element dmdSec)](metadata/mets/dmdsec/)
    - [5.3.4.	Use of the METS administrative metadata section (element amdSec)](metadata/mets/amdsec/)
    - [5.3.5.	Use of the METS file section (element fileSec)](metadata/mets/filesec/)
    - [5.3.6.	Use of the METS structural map (element structMap)](metadata/mets/structmap/)
	- [5.4. Use of PREMIS](metadata/premis/)
- [6. Implementation considerations](considerations/)
  - [6.1.	Content Information Type Specifications](considerations/#61-content-information-type-specifications)
    - [6.1.1.	What is a Content Information Type Specification?](considerations/#611-what-is-a-content-information-type-specification)
    - [6.1.2.	Maintaining Content Information Type Specifications](considerations/#612-maintaining-content-information-type-specifications)
  - [6.2. Handling large packages](considerations/#62-handling-large-packages)
    - [6.2.1.	The structure for IP, their representations and their segments](considerations/#621-the-structure-for-ip-their-representations-and-their-segments)
    - [6.2.2.	Using METS to refer from parent IP to child IP(s)](considerations/#622-using-mets-to-refer-from-parent-ip-to-child-ips)
    - [6.2.3.	Using METS to refer from child IP to parent IP](considerations/#623-using-mets-to-refer-from-child-ip-to-parent-ip)
    - [6.2.4.	An example for the Northwind database](considerations/#624-an-example-for-the-northwind-database)
    - [6.2.5.	Illustration of references between METS files in a segmented IP](considerations/#625-illustration-of-references-between-mets-files-in-a-segmented-ip)
  - [6.3.	Handling descriptive metadata within the Common Specification](considerations/#63-handling-descriptive-metadata-within-the-common-specification)
