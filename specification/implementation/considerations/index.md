# 6. Implementation Considerations
This Section touches on some additional issues which are relevant in respect to implementing the CSIP in real-life scenarios.

## 6.1 Content Information Type Specifications

### 6.1.1 What is a Content Information Type Specification?
A Content Information Type Specification is a mechanism used to extend the scope of the CSIP by defining additional requirements for specific Content Information Types. The OAIS Reference Model defines Content Information as “A set of information that is the original target of preservation or that includes part or all of that information. It is an Information Object composed of its Content Data Object and its Representation Information”.

Content Information Types can be regarded as categories of Content Information, e.g. relational databases, scientific data or digitised maps. A Content Information Type Specification defines the format and structure, mainly in regard to the Information Object, within an Information Package. This facilitates interoperability when exchanging specific Content Information Types.

The following elements should be at the core of a Content Information Type Specification:

- Required file formats for content data;
- Specification of information package structure, i.e. specific sub-folders under the "Data" folder for particular categories of content data;
- Requirements for specific representation metadata that should be available in PREMIS for understanding and rendering the Content Data Object;
- Details of specific (binary) documentation or other components (e.g. software, emulators, etc.) which must be available for rendering and understanding the Content Data Object.

Pragmatically it may not be sufficient to deal only with the Information Object. For complex Content Information Types or large IPs it may be necessary to provide explicit requirements for other metadata (descriptive, administrative) relevant to the specific content type. For example, the ERMS Content Information Type Specification does specific a method for referencing data (i.e. computer files) from descriptive metadata in ERMS format, to ensure the integrity of data and metadata. Stating these requirements in a general specification allows archival institutions receiving SIPs including ERMS extracts or whole systems to understand and validate potentially complex information packages.

Content Information Type Specification can be domain specific and there might be multiple specifications to cover a particular domain. For example, archival institutions might define a Content Information Type Specification for archiving web sites with descriptive metadata in EAD format, while libraries might define a specification for archiving web sites with MARC metadata.

### 6.1.2 Maintaining Content Information Type Specifications
We hope to see the development of many Content Information Type Specifications with the wider community to creating new specifications for domains of interest to them. The DILCIS Board aims to work with the community to maintain a list of available specifications following some simple principles:

- The DILCIS Board is responsible for establishing reasonable guidelines and quality requirements for new Content Information Type Specifications, and publishing these on the Board website;
- The Board has the responsibility and mandate to manage a registry of available Content Information Type Specifications which meet the guidelines and quality requirements;
- The Board does NOT take ownership of or have responsibility for maintaining and sustaining any Content Information Type Specifications;
- There shall be no limitations to who is allowed to propose additional Content Information Type Specifications; and
- To ensure the quality of available specifications, the Board validates each proposed specification against the guidelines and quality requirements mentioned above. The validation shall be carried out free of charge and within a reasonable time-frame.

## 6.2. Handling large packages
By default a Common Specification IP should be contained in a single folder or archive file. However, the amount of data and metadata within a single IP can easily grow to several GB or even TB and become difficult to manage and inefficient to process e.g. due to insufficient storage capacity.

The Common Specification can, in principle, be extended in different ways to support the segmentation of large packages into more manageable parts. This Section describes such an extension which exploits the Common Specification “representation METS” concept and extends it to cover physical segmentation.

Please be aware that this is a “recommended approach” and, at this point in time, is not a part of the Common Specification. It is also not expected that all tools will support such a mechanism.

### 6.2.1 The structure for IP, their representations and their segments
E-ARK Common Specification Information packages may comprise multiple representations of the same intellectual content. The segmentation approach described here is based on the following considerations:

- The majority of the size of an IP consists of the content (data) which, according to the Common Specification, resides in the representations folder of the IP. As such any segmentation should take place within the representations layer of the Common Specification;
- According to the Common Specification each representation is essentially a Common Specification IP itself, consisting of a METS metadata file, data, metadata, and any additional components;
- A segment of an IP must also adhere to the Common Specification format, i.e. it shall be possible to validate each individual segment as a Common Specification IP;
- Each IP shall consist of a parent segment (including at least the package METS file) and any number of child segments;
- It shall be possible to add new physical child segments (as an example a new representation) to the whole IP without necessitating the update of other child segments.

### 6.2.2 Using METS to refer from parent IP to child IP(s)
The method used to refer from parent to child is based on the ID of the IP of the child. One reason for using ID and not URL or other more direct references to a location of the referenced METS file is the flexibility it gives to move the segmented IPs across storage locations. This flexibility is often needed for segmented IPs that can be very large.

The value of the xlink:href attribute in the <mptr> element in the METS file of the parent IP is used.

This vis to be set to the value of the OBJID attribute of the <mets> element in the METS file of the child IP. According to the Common Specification, the OBJID attribute must have the value of the ID of the IP. This is therefore sufficient for having the parent know the ID of the child, but the parent does not know the exact child location.

### 6.2.3 Using METS to refer from child IP to parent IP
The optional reference from child to the parent is based on the ID of the IP of the parent.

The value of the xlink:href attribute in <mptr> element in the METS file of the child IP is used.

This value is to be set to the value of the OBJID attribute of the <mets> element in the METS file of the parent IP. According to the Common Specification, the OBJID attribute must have the value of the ID of the
IP.

This is therefore sufficient for having the child know the ID of the parent, but the child does not know the exact parent location.

### 6.2.4 An example for the Northwind database
Here follows a partial example, where the value of the xlink:href attribute in the `<mptr>` element (inside the `<div>` element inside the `<structMap>` element) is `ID.AVID.RA.18005.rep0.seg0` after the urn NID part (`urn:<NID>:<NSS>`).

The value `ID.AVID.RA.18005.rep0.seg0` must now match the value of the OBJID attribute for the `<mets>` element in the child IP package METS file.
(Note that in order to save space in this example the CS mandatory ID attribute for the `<div>` elements have been left out.)
Parent METS file

```xml
<!-- this top package level METS.xml IP only refers to the package level METS files in the representations using the <mptr> element -->
<div LABEL="representations">
<!-- the value of the attribute LABEL is the ID of the representation -->
   <div LABEL="representations/ID.AVID.RA.18005.rep0" ORDER="0" >
<!-- we use the attribute LABEL value 'child IP' in the 'div' element for representations in accordance with the AIP spec.3.3.1.9 -->
      <div LABEL="child IP" TYPE="representation child">
<!-- each package level METS file in the representations refer to its own METS files in the segments and in the representations folder using
the <mptr> element -->
<!-- this is a METS reference to another METS file, and this file is in another segment -->
        <mptr xlink:href="urn:sa.dk:ID.AVID.RA.18005.rep0.seg0" xlink:title="package level METS file for representation 0" xlink:type="simple"
LOCTYPE="URN"/>
      </div>
   </div>
<!-- the value of the attribute LABEL is the ID of the representation -->
   <div LABEL="representations/ID.AVID.RA.18005.rep1" ORDER="1">
      <div LABEL="child IP" TYPE="representation child">
<!-- this is an indirect METS reference to another METS file, and this file is in another segment -->
         <mptr xlink:href="urn:sa.dk:ID.AVID.RA.18005.rep1.seg0" xlink:title="package level METS file for representation 1" xlink:type="simple"
LOCTYPE="URN"/>
      </div>
   </div>
</div>
```

Child METS file

```xml
<mets xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://www.loc.gov/METS/"
xmlns:xlink="http://www.w3.org/1999/xlink"
xsi:schemaLocation="http://www.loc.gov/METS/ schemas/mets.xsd"
PROFILE="http://www.ra.ee/METS/v01/IP.xml" TYPE="Database segment child" OBJID="ID.AVID.RA.18005.rep0.seg0" LABEL="package
level METS file for a representation segment">
..
..
..
   <div LABEL="parent IP" TYPE="Godfather IP"> <!-- working title - maybe master IP is more appropriate -->
<!-- this is an indirect METS reference to another METS file. However, the referenced file is in another segment -->
      <mptr xlink:href="urn:sa.dk:ID.AVID.RA.18005.godfather" xlink:title="package level METS file for godfather IP" xlink:type="simple"
LOCTYPE="URN"/>
   </div>
```
### 6.2.5 Illustration of references between METS files in a segmented IP
We need to segment an IP at the data folder in the representations level, but according to the Common Specification this can only be done at the IP level. Therefore this IP has been segmented at the top IP level, and not at the representations level.

![CSIP Example](figs/mets_file_ref.png "Illustration of references between files.")

Please note the following about the example:

- The Master IP MUST NOT contain representations
- A representation MAY be segmented
- The IDs are not just unique but have implicit value for example purposes only
- In representation 0 the limits on folder size and amount of files requires three segments (0, 1 and 2)
- In representation 1 these limits have been increased and we only need two segments. Further the .bin files have been migrated to .tif.

### 6.3 Handling descriptive metadata within the Common Specification
Descriptive metadata are used to describe the intellectual contents of archival holdings, and they support finding and understanding individual information packages. The CSIP allows essentially for the inclusion of any kind of descriptive metadata in the IP. However, it is required that all descriptive metadata must be placed into the “metadata” folder of the IP, and that it is recommended (should) to also exploit the possibility of creating a specific sub-folder “descriptive” as seen in [Figure 11](#fig11) below (cf. EAD.xml).

<a name="fig11"></a>
![CSIP Example](figs/fig_11_eark_ip_desc_md.png "EARK IP descriptive metadata.")

**Figure 11:** E-ARK IP descriptive metadata

Further, all descriptive metadata need itself to be described in and referenced from METS metadata (i.e. the `METS.xml` file) using the element `<dmdSec>` ([Figure 12](#fig12)) and as such descriptive metadata are not to be embedded into the METS file directly.

<a name="fig12"></a>
![METS desc md](figs/fig_12_mets_desc_md.png "METS descriptive metadata.")

**Figure 12:** METS descriptive metadata

Following the requirement of explicitly and physically separating descriptive metadata and data we would also like to note, that for interoperability purposes appropriate descriptive metadata elements must explicitly refer to the data content they describe (unless the whole data portion is a single intellectual unit described as a discrete set of descriptive metadata). For example, in the case of EAD elements `<dao>` and `<daogrp>` shall be used to refer to content files from the descriptive metadata. However, regardless of the descriptive metadata standard in question the references from descriptive metadata must always follow the requirement posed in Section 5.1 above (i.e. create references according to the format defined in RFC 3986, or to express references as a relative path to the data files).

Finally we would also note that the recommendation of the CSIP is to always include detailed metadata about intellectual access restrictions and copyright into descriptive metadata (i.e. not into the METS or PREMIS portions of the IP).
