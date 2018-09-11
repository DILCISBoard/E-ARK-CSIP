# 6. Implementation considerations
This Section touches on some additional issues which are relevant in respect to implementing the CS IP in real-life scenarios.

## 6.1.	Content Information Type Specifications
### 6.1.1.	What is a Content Information Type Specification?
The concept of Content Information Type Specification is essentially an extension method which allows for widening the interoperability scope of the CS IP into a content specific level.

As defined by the OAIS Reference Model, Content Information is “A set of information that is the original target of preservation or that includes part or all of that information. It is an Information Object composed of its Content Data Object and its Representation Information”.

A Content Information Type can therefore be understood as a category of Content Information, for example relational databases, scientific data or digitised maps. And finally a Content Information Type Specification
defines in technical terms how data and metadata (mainly in regard to the Information Object) must be formatted and placed within a CS IP Information Package in order to achieve interoperability in exchanging
specific Content Information.

As such, the following elements can be at the core of a Content Information Type Specification:

- The required file format of data;
- Description of how data must be placed and structured within the CS IP folder structure (i.e. a sub-structure for the “Data” folder);
- Clearly defined requirements for specific representation metadata that needs to be available in PREMIS for rendering and understanding the Content Data Object appropriately;
- Clearly defined list of specific (binary) documentation or other components (like software, emulators, etc.) which have to be available for rendering and understanding the Content Data Object appropriately.

However, for practical purposes it is not sufficient to only deal with the Information Object. Especially for complex Content Information Types and large IPs it might also be relevant to describe explicitly requirements for other metadata (descriptive, administrative) which are relevant and crucial only for this specific content type. For example, the SMURF Content Information Type Specification, developed within the E-ARK project, does set specific requirements for how data (i.e. computer files) need to be referenced
from descriptive metadata (in EAD format) in order to guarantee the integrity of data and metadata. Setting these requirements in a central specification will allow archival institutions to receive SIPs including ERMS extracts or whole systems and still be able to understand and validate the potentially complex structure of the whole data and metadata composition within it.

Concluding from the previous we can also see that Content Information Type Specification can potentially also be sector specific, and that there might be multiple specifications to cover a single content type. For example, archival institutions would be able to define a Content Information Type specification for archiving web sites along with descriptive metadata in EAD format, while libraries might define a specification for archiving web sites along with metadata in MARC.

### 6.1.2.	Maintaining Content Information Type Specifications
The number of possible Content Information Type Specifications is potentially unlimited. As well, it is the intention of the authors of the CS IP to allow everybody in the wider community to create new specifications.

The maintenance of such a living environment is the role of the DILCIS Board. The core principles of the maintenance regime are as follows:
- The DILCIS Board is responsible for establishing reasonable guidelines and quality requirements for new Content Information Type specifications, and publishing these on the Board website;
- The Board has the responsibility and mandate to manage a registry of available Content Information Type specifications which meet the guidelines and quality requirements;
- The Board does NOT take ownership of and have responsibility of maintaining and sustaining any Content Information Type specifications;
- There shall be no limitations to who is allowed to propose additional Content Information Type specifications;
- To ensure good quality of available specifications, the Board validates each proposed specification against the guidelines and quality requirements mentioned above. The validation shall be carried out free of charge and within a reasonable timeframe.

## 6.2. Handling large packages
By default a Common Specification IP is supposed to reside in a single folder or file (in case compression has been applied). However, the amount of data and metadata within a single IP can easily grow into sizes of several GB or even TB and as such can become difficult to manage and inefficient to process because, for example, of lacking media capacity.

The Common Specification itself can in principle be extended in multiple ways to support the segmenting of large packages into more manageable physical pieces. This Section describes one way which exploits the Common Specification “representation METS” concept and extends it into a physical segmentation scenario.

However, it is worth noting that this is a “recommended approach” and is, at this point in time, not a part of the core Common Specification, as such it is also not expected that all tools support such a mechanism.

### 6.2.1.	The structure for IP, their representations and their segments
According to the E-ARK Common Specification for IPs an IP can have several representations. All representations contain the same intellectual content, but as the name implies is another representation; in its most simple form this could be another file format such as TIFF instead of JPEG.

The segmenting approach described here is based on the following considerations:
- Most of the size of an IP is the content (data) which according to the Common Specification resides in the representations folder of the IP. As such also any segmenting should take place within the representations layer of the Common Specification;
- According to the Common Specification each representation is essentially a Common Specification IP itself, as it can consist of a METS metadata file, data, metadata, and any additional components;
- A segment of an IP must also be in the Common Specification format, i.e. it shall be possible to validate each individual segment as a Common Specification IP;
- Each IP shall consist of a parent segment (including at least the root METS file) and any number of child segments;
- It shall be possible to add new physical child segments (as an example a new representation) to the whole IP without having to update other child segments.

### 6.2.2.	Using METS to refer from parent IP to child IP(s)
The method used to refer from parent to child is based on the ID of the IP of the child.

One reason for using ID and not URL or other more direct references to a location of the referenced METS file is the flexibility it gives to move the segmented IPs around in different storage locations. This is a flexibility often needed for segmented IPs that accumulated can be very large.

The value of the xlink:href attribute in the <mptr> element in the METS file of the parent IP is used.

This value is to be set to the value of the OBJID attribute of the <mets> element in the METS file of the child IP. According to the Common Specification, the OBJID attribute must have the value of the ID of the IP.
This is therefore sufficient for having the parent know the ID of the child, but the parent does not know the exact child location.

### 6.2.3.	Using METS to refer from child IP to parent IP
The optional reference from child to the parent is based on the ID of the IP of the parent.

The value of the xlink:href attribute in <mptr> element in the METS file of the child IP is used.

This value is to be set to the value of the OBJID attribute of the <mets> element in the METS file of the parent IP. According to the Common Specification, the OBJID attribute must have the value of the ID of the
IP.

This is therefore sufficient for having the child know the ID of the parent, but the child does not know the exact parent location.

### 6.2.4.	An example for the Northwind database
Here follows a partial example, where the value of the xlink:href attribute in the `<mptr>` element (inside the `<div>` element inside the `<structMap>` element) is `ID.AVID.RA.18005.rep0.seg0` after the urn NID part (`urn:<NID>:<NSS>`).

The value `ID.AVID.RA.18005.rep0.seg0` must now match the value of the OBJID attribute for the `<mets>` element in the child IP root METS file.
(Note that in order to save space in this example the CS mandatory ID attribute for the `<div>` elements have been left out.)
Parent METS file
```xml
<!-- this top root level METS.xml IP only refers to the root level METS files in the representations using the <mptr> element -->
<div LABEL="representations">
<!-- the value of the attribute LABEL is the ID of the representation -->
   <div LABEL="representations/ID.AVID.RA.18005.rep0" ORDER="0" >
<!-- we use the attribute LABEL value 'child IP' in the 'div' element for representations in accordance with the AIP spec.3.3.1.9 -->
      <div LABEL="child IP" TYPE="representation child">
<!-- each root level METS file in the representations refer to its own METS files in the segments and in the representations folder using
the <mptr> element -->
<!-- this is a METS reference to another METS file, and this file is in another segment -->
        <mptr xlink:href="urn:sa.dk:ID.AVID.RA.18005.rep0.seg0" xlink:title="root level METS file for representation 0" xlink:type="simple"
LOCTYPE="URN"/>
      </div>
   </div>
<!-- the value of the attribute LABEL is the ID of the representation -->
   <div LABEL="representations/ID.AVID.RA.18005.rep1" ORDER="1">
      <div LABEL="child IP" TYPE="representation child">
<!-- this is an indirect METS reference to another METS file, and this file is in another segment -->
         <mptr xlink:href="urn:sa.dk:ID.AVID.RA.18005.rep1.seg0" xlink:title="root level METS file for representation 1" xlink:type="simple"
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
PROFILE="http://www.ra.ee/METS/v01/IP.xml" TYPE="Database segment child" OBJID="ID.AVID.RA.18005.rep0.seg0" LABEL="root
level METS file for a representation segment">
..
..
..
   <div LABEL="parent IP" TYPE="Godfather IP"> <!-- working title - maybe master IP is more appropriate -->
<!-- this is an indirect METS reference to another METS file. However, the referenced file is in another segment -->
      <mptr xlink:href="urn:sa.dk:ID.AVID.RA.18005.godfather" xlink:title="root level METS file for godfather IP" xlink:type="simple"
LOCTYPE="URN"/>
   </div>
```
### 6.2.5.	Illustration of references between METS files in a segmented IP
We need to segment an IP at the data folder in the representations level, but according to the Common Specification this can only be done at the IP level. Therefore this IP has been segmented at the top IP level, and not at the representations level.

![CS IP Example](mets_file_ref.png "Illustration of references between files.")

Please note the following about the example:
- The Master IP MUST NOT contain representations
- A representation MAY be segmented
- The IDs are not just unique but haves implicit value for example purposes only
- In representation 0 the limits on folder size and amount of files requires three segments (0, 1 and 2)
- In representation 1 these limits have been increased and we only need two segments. Further the .bin files have been migrated to .tif.

### 6.3.	Handling descriptive metadata within the Common Specification
Descriptive metadata are used to describe the intellectual contents of archival holdings, and they support finding and understanding individual information packages. The CS IP allows essentially for the inclusion of any kind of descriptive metadata in the IP. However, it is required that all descriptive metadata must be placed into the “metadata” folder of the IP, and that it is recommended (should) to also exploit the possibility of creating a specific sub-folder “descriptive” as seen in Figure 11 below (cf. EAD.xml).

<a name="fig11"></a>
![CS IP Example](fig_11_eark_ip_desc_md.png "EARK IP descriptive metadata.")

**Figure 11:** E-ARK IP descriptive metadata

Further, all descriptive metadata need itself to be described in and referenced from METS metadata (i.e. the METS.xml file) using the element `<dmdSec>` (Figure 12) and as such descriptive metadata are not to be embedded into the METS file directly.

<a name="fig12"></a>
![METS desc md](fig_12_mets_desc_md.png "METS descriptive metadata.")

**Figure 12:** METS descriptive metadata

Following the requirement of explicitly and physically separating descriptive metadata and data we would also like to note, that for interoperability purposes appropriate descriptive metadata elements must explicitly refer to the data content they describe (unless the whole data portion is a single intellectual unit described as a discrete set of descriptive metadata). For example, in the case of EAD elements <dao> and `<daogrp>` shall be used to refer to content files from the descriptive metadata. However, regardless of the descriptive metadata standard in question the references from descriptive metadata must always follow the requirement posed in Section 5.1 above (i.e. create references according to the format defined in RFC 3986, or to express references as a relative path to the data files).

Finally we would also note that the recommendation of the CS IP is to always include detailed metadata about intellectual access restrictions and copyright into descriptive metadata (i.e. not into the METS or PREMIS portions of the IP).
