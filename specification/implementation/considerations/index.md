
# Implementation Considerations
This section touches on some additional issues which are relevant in respect to implementing the CSIP in real-life scenarios.

## Content Information Type Specifications

### What is a Content Information Type Specification?
A Content Information Type Specification is a mechanism used to extend the scope of the CSIP by defining additional requirements for specific Content Information Types. The OAIS Reference Model defines Content Information as “A set of information that is the original target of preservation, or that includes part or all of that information. It is an Information Object composed of its Content Data Object and its Representation Information”.

Content Information Types can be regarded as categories of Content Information (e.g. relational databases, scientific data or digitised maps). A Content Information Type Specification defines the format and structure, mainly in regard to the Information Object, within an Information Package. This facilitates interoperability when exchanging specific Content Information Types.

The following elements should be at the core of a Content Information Type Specification:

- Required file formats for content data;
- Specification of the information package structure (i.e. specific sub-folders under the "data" folder for particular categories of content data);
- Requirements for specific representation metadata that should be available in PREMIS for understanding and rendering the Content Data Object;
- Details of specific (binary) documentation or other components (e.g. software, emulators, etc.) which must be available for rendering and understanding the Content Data Object.

Pragmatically it may not be sufficient to deal only with the Information Object. For complex Content Information Types or large IPs, it may be necessary to provide explicit requirements for other metadata relevant to the specific content type. For example, the ERMS Content Information Type Specification prescribes a method for referencing data (i.e. computer files) from descriptive metadata in ERMS format, ensuring package integrity. Stating these requirements in a general specification allows archival institutions receiving SIPs, including ERMS extracts or whole systems to understand and validate potentially complex information packages.

Content Information Type Specifications can be domain-specific, and there may be multiple specifications to cover a particular domain. For example, archival institutions might define a Content Information Type Specification for archiving web sites with descriptive metadata in EAD format, while libraries might define a specification for archiving web sites with MARC metadata.

### Maintaining Content Information Type Specifications
It is hoped that many Content Information Type Specifications will be developed with the wider community to create new specifications for domains of interest to them. The DILCIS Board aims to work with the community to maintain a list of available specifications following some simple principles:

- The DILCIS Board is responsible for establishing reasonable guidelines and quality requirements for new Content Information Type Specifications, and publishing these on the Board website;
- The Board has the responsibility and mandate to manage a registry of available Content Information Type Specifications which meet the guidelines and quality requirements;
- The Board does NOT take ownership of or have responsibility for maintaining and sustaining any Content Information Type Specifications;
- There shall be no limitations to who is allowed to propose additional Content Information Type Specifications; and
- To ensure the quality of available specifications, the Board validates each proposed specification against the guidelines and quality requirements mentioned above. The validation shall be carried out free of charge and within a reasonable time-frame.

## Handling large packages
By default, a Common Specification IP should be contained in a single folder or archive file. However, the amount of data and metadata within a single IP can easily grow to several GB or even TB and become difficult to manage and inefficient to process (e.g. due to insufficient storage capacity).

The Common Specification can, in principle, be extended in different ways to support the segmentation of large packages into more manageable parts.

The handling of large packages will be specified in a later version of CSIP. Please be aware that this will be a “recommended approach” and, at this point in time, it is not a part of the CSIP instead segmentation of large packages depends on the local implementation. It is also not expected that all tools will support the segmentation of large packages.

### Handling descriptive metadata within the Common Specification
Descriptive metadata are used to describe the intellectual contents of archival holdings, and they support finding and understanding individual information packages. The CSIP allows essentially for the inclusion of any kind of descriptive metadata in the IP. However, it is required that all descriptive metadata must be placed into the “metadata” folder of the IP, and that it is recommended (should) to also exploit the possibility of creating a specific sub-folder “descriptive” as seen in [Figure 11](#fig11) below (cf. EAD.xml).

<a name="fig11"></a>
![CSIP Example](figs/fig_11_eark_ip_desc_md.svg "EARK IP descriptive metadata.")

**Figure 11:** E-ARK IP descriptive metadata

Further, all descriptive metadata need to be described in and referenced from METS metadata (i.e. the `METS.xml` file) using the element `<dmdSec>` ([Figure 12](#fig12)) and as such descriptive metadata should not be embedded directly in the METS file.

<a name="fig12"></a>
![METS desc md](figs/fig_12_mets_desc_md.svg "METS descriptive metadata.")

**Figure 12:** METS descriptive metadata

Following the requirement of explicitly and physically separating descriptive metadata and data it should be noted that for interoperability purposes appropriate descriptive metadata elements must explicitly refer to the data content they describe (unless the whole data portion is a single intellectual unit described as a discrete set of descriptive metadata). For example, in the case of EAD elements `<dao>` and `<daogrp>` shall be used to refer to content files from the descriptive metadata. However, regardless of the descriptive metadata standard in question, the references from descriptive metadata must always follow the requirement posed in Section 5.1 above (i.e. create references according to the format defined in RFC 3986, or to express references as a relative path to the data files).

Finally, it is recommended that detailed metadata about intellectual access restrictions and copyright is included in descriptive metadata (i.e. not into the METS or PREMIS portions of the IP).
