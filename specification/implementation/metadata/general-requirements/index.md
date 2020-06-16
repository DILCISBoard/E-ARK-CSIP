## General requirements for metadata in a CSIP Information Package
The primary consideration with metadata is, as with the rest of this specification, interoperability. Specifically, the high-level technical interoperability and tasks that allow an Information Package to be prepared, transferred and received, regardless of the institutions or tools involved. These tasks include:

- Uniquely identifying an Information Package and its components;
- Validating an Information Package and its contents;
- Establishing the authenticity of the Information Package;
- Accessing the contents of an Information Package.

In technical terms, the CSIP tries to control metadata needed by tools or users to:

- navigate data and metadata components within the package (i.e. packaging metadata);
- validate that no component has been damaged during transfer or preservation (i.e. fixity information);
- understand the processes used when creating and managing the package (i.e. provenance and preservation metadata); and
- understand how the data within the package can be accessed (i.e. representation information).

Descriptive metadata and most detailed technical metadata is outside the scope of the CSIP. As such, the CSIP does not provide detailed semantic interoperability between different systems. However, as noted in Section 1.2, implementers are welcome to use the Content Information Type Specifications to achieve interoperability at a more detailed level.

The core metadata requirements are described using METS (Metadata Encoding & Transmission Standard, <http://www.loc.gov/standards/mets/>). We describe the core elements used, METS describes other available elements which may be used in addition to those detailed in the CSIP.

Some metadata requirements are implied by the structure presented in the previous section. As seen, one or more METS files can be present in a package. The METS file describing the whole package is from now called the "Package METS", and the METS file in the representation folders are called "Representation METS". The detailed specification of using METS within the CSIP is available in Section 5.3.

In addition to the METS files, the CSIP recommends the inclusion of PREMIS metadata (PREservation Metadata Implementation Strategies, <http://www.loc.gov/standards/premis/>), placed in appropriate preservation metadata folders. This facilitates interoperable approach towards provenance and access to Information Packages. However, it is recognised that, especially in the case of SIPs, appropriate preservation metadata is not always available. As such, this is also not an absolute requirement though highly desirable. A specification of the use of PREMIS within the CSIP is available in Section 5.4.

The specification does not prohibit the use of additional metadata within Information Packages.

## General requirements for the use of metadata
Before detailing the requirements covering the use of METS and PREMIS, it is necessary to highlight some general metadata concerns which should be handled consistently.

### The use of identifiers
Both METS, and by derivation the CSIP, make extensive use of the XML ID data type (see: <https://www.w3.org/TR/xml-id/>). Valid XML IDs must also conform to the NCName restrictions (see: <https://www.w3.org/TR/REC-xml-names/#NT-NCName>), and must begin a letter or an underscore character (‘_’), and contain no characters other than letters, digits, hyphens, underscores, full stops, plus some extension and combination characters.

If a package makes use of IDs that do not conform to this specification, one mitigating strategy is to add a prefix to all identifiers. For example, UUIDs are a common choice for arbitrary identifiers and comprise of hex characters and hyphens (e.g. the value `906F4F12-BA52-4779-AE2C-178F9206111F`). NCName and xml:id values cannot legally begin with numeric characters making many UUIDs, including the example, invalid. The examples below show two possible solutions, using prefixes:

Example 1: The prefix consists of the identifier type acronym and a hyphen: `uuid-`.

```xml
<dmdSec ID="uuid-906F4F12-BA52-4779-AE2C-178F9206111F" CREATED="2018-04-24T14:37:49.609+01:00">
```

Example 2: A generic prefix:`ID`.

```xml
<dmdSec ID="ID906F4F12-BA52-4779-AE2C-178F9206111F" CREATED="2018-04-24T14:37:49.609+01:00">
```

The identifiers specified within the CSIP are mainly used as internal references between Information Package components. Prefixes are not mandatory, but if they are used, it is recommended that a single prefix is chosen and used consistently across all IDs in the package.

Note that while we recommend the use of generated unique IDs in real world information packages we used logical unique names for the examples in this document. This is for readability purposes, particularly for any readers trying to follow reference links.

### Referencing between files within a CSIP Information Package
It is strongly recommended that all components of an information package (i.e. all data, metadata and other parts) are stored as discrete files within the package. While this approach simplifies the management of the Information Package, making it easier to aggregate, validate and modify the package, it also necessitates a clear method for recording and resolving referencing between files.

For example, when using the CSIP referencing can occur between:

- descriptive and administrative metadata files described in the `amdSec` and `dmdSec` elements;
- content (data) files, components of documentation, and schemas described in the `fileSec` element;
- representation METS files described in the Package METS file’s `fileSec` and `structMap` elements.

A consistent approach to referencing between package components is a fundamental requirement for Information Package validation and integrity checking. Different technical solutions exist for referencing and resolving, but they are not universally supported by all digital preservation tools. To ensure interoperability, all references within a CSIP Information Package must adhere to the requirements stated in this specification.

### Referencing other packages
It is important that external references (i.e. to other packages) are expressed consistently, in the same manner as internal references. All references to other packages MUST USE the `mets/@OBJID` value of the target package.

### Examples
This document illustrates the use of the specification with generic examples. These examples use values from vocabularies and elements defined by the CSIP to demonstrate some specific uses of the specification. Outside these CSIP specific values, the METS values are fictive and are not meant to constitute real-life examples. More complete examples will be provided in the CSIP user guidelines that will be published at a future date.
