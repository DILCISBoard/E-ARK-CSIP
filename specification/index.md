---
title: E-ARK CSIP Table Of Contents
---
E-ARK CSIP Table Of Contents
============================

Version: 1.0

February 1, 2017

Front Matter
------------
1. [Authors](authors)
2. [Revision History](history)

Contents
--------

- [1 Introduction](introduction)
	- [1.1 The Common Specification for Information Packages and OAIS](introduction#11-the-common-specification-for-information-packages-and-oais)
	- [1.2 The Common Specification for Information Packages and Content Information Type Specifications](introduction#12-the-common-specification-for-information-packages-and-content-information-type-specifications)
	- [1.3 Common Specification for Information Packages, OAIS Information Packages’ specifications and Content Information Type Specifications](introduction#13-common-specification-for-information-packages-oais-information-packages-specifications-and-content-information-type-specifications)
	- [1.4. Relation to other documents](introduction#14-relation-to-other-documents)
		- [International standards and best-practices](introduction#international-standards-and-best-practices)
		- [E-ARK project (2014 – 2017) deliverables](introduction#e-ark-project-2014--2017-deliverables)
		- [Other E-ARK specifications](introduction#other-e-ark-specifications)
	- [1.5. Structure of the document](introduction#15-structure-of-the-document)
- [PART I: Common Specification for Information Packages](specification#part-i-common-specification-for-information-packages)
	- [2.	Need for establishing common ground](specification/common-ground)
	- [3. Requirements for CS IP Information Packages](specification/principles)
		- [3.1. General principles](specification/principles#31-general-principles)
		- [3.2. Identification of the Information Package](specification/principles#32-identification-of-the-information-package)
		- [3.3.	Structure of the Information Package](specification/principles#33-structure-of-the-information-package)
		- [3.4.	Information Package Metadata](specification/principles#34-information-package-metadata)
- [PART II: Implementation of the CS IP](implementation)
	- [4.	CS IP structure](implementation/structure/)
	  - [4.1 Folder structure of the CS IP](implementation/structure/folders)
	  - [4.2 Implementing the structure](implementation/structure/implementation)
	- [5. Use of metadata](implementation/metadata/)
		- [5.1. General requirements for metadata in a CS IP Information Package](implementation/metadata/general-requirements/#51-general-requirements-for-metadata-in-a-cs-ip-information-package)
		- [5.2. General requirements for the use of metadata](implementation/metadata/general-requirements/#52-general-requirements-for-the-use-of-metadata)
		- [5.3. Use of METS](implementation/metadata/mets/)
	    - [5.3.1.	Use of the METS root element (element mets)](implementation/metadata/mets/mets-root/)
	    - [5.3.2.	Use of the METS header (element metsHdr)](implementation/metadata/mets/metshdr/)
	    - [5.3.3 Use of the METS descriptive metadata section (element dmdSec)](implementation/metadata/mets/dmdsec/)
	    - [5.3.4.	Use of the METS administrative metadata section (element amdSec)](implementation/metadata/mets/amdsec/)
	    - [5.3.5.	Use of the METS file section (element fileSec)](implementation/metadata/mets/filesec/)
	    - [5.3.6.	Use of the METS structural map (element structMap)](implementation/metadata/mets/structmap/)
		- [5.4. Use of PREMIS](implementation/metadata/premis/)
	- [6. Implementation considerations](implementation/considerations/)
	  - [6.1.	Content Information Type Specifications](implementation/considerations/#61-content-information-type-specifications)
	    - [6.1.1.	What is a Content Information Type Specification?](implementation/considerations/#611-what-is-a-content-information-type-specification)
	    - [6.1.2.	Maintaining Content Information Type Specifications](implementation/considerations/#612-maintaining-content-information-type-specifications)
	  - [6.2. Handling large packages](implementation/considerations/#62-handling-large-packages)
	    - [6.2.1.	The structure for IP, their representations and their segments](implementation/considerations/#621-the-structure-for-ip-their-representations-and-their-segments)
	    - [6.2.2.	Using METS to refer from parent IP to child IP(s)](implementation/considerations/#622-using-mets-to-refer-from-parent-ip-to-child-ips)
	    - [6.2.3.	Using METS to refer from child IP to parent IP](implementation/considerations/#623-using-mets-to-refer-from-child-ip-to-parent-ip)
	    - [6.2.4.	An example for the Northwind database](implementation/considerations/#624-an-example-for-the-northwind-database)
	    - [6.2.5.	Illustration of references between METS files in a segmented IP](implementation/considerations/#625-illustration-of-references-between-mets-files-in-a-segmented-ip)
	  - [6.3.	Handling descriptive metadata within the Common Specification](implementation/considerations/#63-handling-descriptive-metadata-within-the-common-specification)
- [Appendices](appendices/)
	- [Appendix A: E-ARK Information Package METS examples](appendices/examples/)
	- [Appendix B: External Schema and Vocabularies](appendices/schema/)
	- [Appendix C: A Full List of E-ARK CSIP Requirements](appendices/requirements/)

Acknowledgements
----------------
The Common Specification for Information Packages was first developed within the E-ARK project in 2014 – 2017. E-ARK was an EC-funded pilot action project in the Competiveness and Innovation Programme 2007- 2013, Grant Agreement no. 620998 under the Policy Support Programme.

We would like to thank the National Archives of Sweden and Karin Bredenberg for their support and the availability of the Swedish national Common Specifications, upon which most of this document has been built.

The authors of this deliverable would like to thank all national archives, tool developers and other stakeholders who provided valuable knowledge about their requirements for information packages and feedback to this specification!

Contact & Feedback
------------------
The Common Specification for Information Packages is maintained by the Digital Information LifeCycle
Interoperability Standard Board (DILCIS Board). For further information about the DILCIS Board or feedback
on the current document please consult the website http://www.dilcis.eu/ or contact us at
<dasboard@dlmforum.eu.>
 
