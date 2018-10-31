## 5.1. General requirements for metadata in a CS IP Information Package
The number one consideration when discussing metadata requirements is, as with the rest of this specification, the need for interoperability. In more detail, the focus is on high-level technical interoperability and tasks which allow an Information Package to be prepared, transferred and received regardless of the institutions and tools involved. These tasks include:

- Identifying uniquely an Information Package and its components;
- Validating an Information Package;
- Validating the contents of an Information Package;
- Proving the authenticity of the Information Package;
- Accessing the contents of an Information Package.

In more technical terms the CS IP makes an effort to control metadata which allows any tool or user to negotiate the data and metadata components of the package (i.e. packaging metadata), to validate that no component has come to harm during transfer or preservation (i.e. fixity information), to understand the processes behind the creation and management of the package (i.e. provenance and preservation metadata) and finally to understand how the data within the package could be accessed (i.e. representation information).

Most crucially, we regard descriptive metadata and most of detailed technical metadata to not belong in the scope of the CS IP. As such, the CS IP itself does not aim to provide detailed semantic interoperability between different systems. However, as noted in Section 1.2, implementers are welcome to use the
construct of Content Information Type Specifications to achieve an even higher level of interoperability.

We implement the core metadata requirements with METS (Metadata Encoding & Transmission Standard, http://www.loc.gov/standards/mets/ ). In this specification we describe the core elements used, more elements are available in the METS standard and can be used in the own implementation. 

Some of the core metadata requirements are already visible from the structure presented in the previous Section. As seen in the previous section one or more METS files can be present. The METS file describing the whole package is from now called "Root METS" and the METS file present in the Representation folder is called "Representation METS" in the rest of this document. The detailed specification of using METS within the CS IP is available in Section 5.3.

In addition to the METS files the CS IP recommends the inclusion of PREMIS metadata (PREservation Metadata Implementation Strategies, http://www.loc.gov/standards/premis/ ) in appropriate preservation metadata folders. This is especially relevant when aiming for an interoperable approach towards provenance and access to Information Packages. However, we recognise that, especially in the
case of SIPs, appropriate preservation metadata is not always available. As such this is also not an absolute requirement though highly desirable. The detailed specification of the use of PREMIS within the CS IP is available in Section 5.4.

The use of any additional metadata is not restricted in CS IP Information Packages.

## 5.2.	General requirements for the use of metadata
Before we describe the detailed requirements for the use of METS and PREMIS we would like to highlight some general aspects which need to be implemented commonly across all metadata.

### The use of identifiers
The ID data type in XML does define that a legit value must begin with a letter, or the underscore character (‘_’), and contain no characters other than letters, digits, hyphens, underscores, full stops, and certain combining and extension characters. To overcome this limitation and in order to allow for interoperable package identification all identifiers within Common Specification metadata MUST start with a prefix, followed by the actual value of the identifier.

Examples:

Example 1: using a prefix which consists of the abbreviation of the identifier and a hyphen.

```xml
<dmdSec ID="uuid-906F4F12-BA52-4779-AE2C-178F9206111F" CREATED="2018-04-
24T14:37:49.609+01:00">
```

Example 2: using a fixed prefix “ID”
```xml
<dmdSec ID="ID906F4F12-BA52-4779-AE2C-178F9206111F" CREATED="2018-04-
24T14:37:49.609+01:00">
```

Note that identifier-type elements and attributes specified within the CS IP are mainly used for internal referencing between the components of an Information Package. As such there is no need to require the use of any specific prefix syntax but it is required that any selected prefix is used
consistently throughout the package.

### Referencing between files within a CS IP Information Package
This specification recommends strongly to format all components of the information package (i.e. all data, metadata and other parts) as distinct computer files within the package. While such an approach simplifies the overall management of the Information Package and makes it easier to
include, validate and modify the package, it also brings the need for a clear method for referencing between these various files.

For example, the METS specification requires for referencing to:

- descriptive and administrative metadata files described in the `amdSec` and `dmdSec` elements;
- content (data) files, components of documentation and schemas described in the `fileSec` element;
- representation METS files described in the root METS file’s `structMap` element.

A common approach towards referencing between metadata, and between metadata and other components of the package, is one of the core needs in Information Package validation and integrity checking. Different technical solutions are available for referencing and not all of these are supported across all digital preservation tools.

In order to guarantee interoperability, all references within a CS IP Information Package must follow the requirements below:
- in all occurrences of the METS `@LOCTYPE` attribute the value of it MUST be “URL”;
- the W3C recommendation XML Linking Language (XLink) version 1.1  MUST be used for expressing references in metadata;
  - The XLink `@type` attribute MUST be used with the fixed value “simple”;
  - The value of the XLink `@href` attribute MUST be expressed as a valid URI according to RFC 3986 ;
  - Further, the value of the XLink `@href` attribute SHOULD NOT use the protocol part of the URI (e.g. `file://` or `http://`) and, in this case, MUST be interpreted as a relative path to the file (from the metadata file into which the reference is included).

Example:
```xml
<mdRef LOCTYPE="URL" xlink:type="simple" xlink:href="metadata/preservation/premis2.xml"
... />
```

### Referencing other packages
As with internal referencing it is crucial that external references to other related packages are expressed in an interoperable manner. As such all references to other CS IP Information Packages MUST use the value of the `mets/@OBJID` attribute of the package.
