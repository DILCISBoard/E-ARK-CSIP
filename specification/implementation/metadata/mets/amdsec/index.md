---
title: Use of the METS administrative metadata section
---
### 5.3.4.	Use of the METS administrative metadata section (element amdSec)
The purpose of the METS administrative data section is to embed or refer to files containing administrative metadata about the IP content objects. The CS IP (and METS) categorises preservation metadata as administrative metadata, specifically Digital Provenance metadata, hence all preservation metadata should be referenced from a digiprovMD element within the amdSec.

The CS IP allows both the embedding of metadata within the METS.xml file and keeping metadata in external files within the IP. Where preservation metadata is stored in external files (external to the METS file) it should be referenced using the mdRef element. Embedded metadata is wrapped using the `mdWrap` element. Note that for scalability concerns the CS IP recommends the use of mdRef over mdWrap.

The METS amdSec element must include references to all relevant metadata either embedded or in external files located in the folder “metadata/preservation”. This means also that the root level METS.xml
file must refer only to the root level preservation metadata and the representation METS.xml file must refer only to the representation level preservation metadata.

Decision regarding placement of PREMIS in this section is following the guide lines available from PREMIS EC <http://www.loc.gov/standards/premis/guidelines2017-premismets.pdf>.

The specific requirements for the amdSec element, its sub-elements and attributes are presented in the
following table.

| ID | Name | Element/Attribute | Description and usage | Cardinality |
| -- | ---- | ----------------- | --------------------- | ----------- |
| <a name="CSIP37"></a>CSIP37 | Administrative metadata | amdSec | In case administrative / preservation metadata is available, it must be described using the amdSec element. | 0..n |
| <a name="CSIP38"></a>CSIP38 | Provenance metadata | amdSec/digiprovMD | The CS IP recommends the use of PREMIS metadata for recording information about preservation events. If used, PREMIS metadata must appear in a digiprovMD element, either embedded or linked. It is mandatory to include one digiprovMD element for each external file in the “metadata/preservation” folder, or for each embedded set of PREMIS metadata. | 0..n |
| <a name="CSIP39"></a>CSIP39 | Rights metadata | amdSec/rightsMD | Optional. The CS IP recommends including a simple rights statement which describes the overall access status of the package with the following values:<br/>- Open<br/>- Closed<br/>- Partially closed<br/>- Not known.<br/>However, selecting the exact schema and element semantics for encoding this information is up to individual implementations to decide | 0..n |

The following attributes are available for use with the two specific metadata areas listed above.

| ID | Name | Element/Attribute | Description and usage | Cardinality |
| -- | ---- | ----------------- | --------------------- | ----------- |
| <a name="CSIP40"></a>CSIP40 | Metadata section ID | amdSec/digiprovMD/@ID; amdSec/rightsMD/@ID | Mandatory for the elements amdSec/digiprovMD, amdSec/rightsMD. Identifier must be unique within the package | 1..1 |
| <a name="CSIP41"></a>CSIP41 | Reference to administrative metadata | amdSec/digiprovMD/@ADMID; amdSec/rightsMD/@ADMID | In case administrative (provenance) metadata is available and described within METS about changes to the metadata occurrence described here, this element must reference the appropriate ID of the administrative metadata section. | 0..1 |
| <a name="CSIP42"></a>CSIP42 | Metadata creation date | amdSec/digiprovMD/@CREATED; amdSec/rightsMD/@CREATED | Optional, no further requirements | 0..1 |
| <a name="CSIP43"></a>CSIP43 | Metadata status | amdSec/digiprovMD/@STATUS; amdSec/rightsMD/@STATUS | Recommended for describing currency of metadata. If used, must include one of the two values “superseded” or “current” | 0..1 |

In the same way as with dmdSec, metadata files referenced in the amdSec should be linked using mdRef.

**Use of mdRef**

| ID | Name | Element/Attribute | Description and usage | Cardinality |
| -- | ---- | ----------------- | --------------------- | ----------- |
| <a name="CSIP44"></a>CSIP44 | External metadata link | amdSec/digiprovMD/mdRef; amdSec/rightsMD/mdRef | Reference to the descriptive metadata file stored in the “metadata” folder of the IP.<br/>In each occurrence of the dmdSec exactly one occurrence of the  mdRef element must be present. | 0..1 |

**Location group**

The following group of elements describes the actual location of the references metadata file

| ID | Name | Element/Attribute | Description and usage | Cardinality |
| -- | ---- | ----------------- | --------------------- | ----------- |
| <a name="CSIP45"></a>CSIP45 | Locator type | amdSec/digiprovMD/mdRef/@LOCTYPE; amdSec/rightsMD/mdRef/@LOCTYPE | Specifies the locator type used in the xlink:href which points to the file. The @LOCTYPE attribute is mandatory in the CS IP | 1..1 |
| <a name="CSIP46"></a>CSIP46 | Locator type | amdSec/digiprovMD/mdRef/@LOCTYPE; amdSec/rightsMD/mdRef/@LOCTYPE | Within the CS IP the value of @LOCTYPE attribute must be "URL". | 1..1 |
| <a name="CSIP47"></a>CSIP47 | XLink type | amdSec/digiprovMD/mdRef/@xlink:type; amdSec/rightsMD/mdRef/@xlink:type | The type of the link. The @xlink:type attribute is mandatory in the CS IP | 1..1 |
| <a name="CSIP48"></a>CSIP48 | XLink type | amdSec/digiprovMD/mdRef/@xlink:type; amdSec/rightsMD/mdRef/@xlink:type | The CS IP requires the use of @xlink:type attribute with the fixed value “simple” | 1..1 |
| <a name="CSIP49"></a>CSIP49 | XLink location | amdSec/digiprovMD/mdRef/@xlink:href; amdSec/rightsMD/mdRef/@xlink:href | The actual location of the resource.<br/>This specification requires the recording of an URL type filepath within this attribute.<br/>Further, the filepath must be decoded consistently throughout all mdRef elements within the information package, and follow the requirements for referencing as described in Section 5.2. | 1..1 |

**Metadata group**

The following group of elements describes the type and version of metadata being referenced.

| ID | Name | Element/Attribute | Description and usage | Cardinality |
| -- | ---- | ----------------- | --------------------- | ----------- |
| <a name="CSIP50"></a>CSIP50 | Type of metadata | amdSec/digiprovMD/mdRef/@MDTYPE; amdSec/rightsMD/mdRef/@MDTYPE | Specifies the type of metadata in the linked file. Values should be taken from the METS list provided. | 1..1 |

**File core group**

The following elements describe the core characteristics of the referenced metadata file.

| ID | Name | Element/Attribute | Description and usage | Cardinality |
| -- | ---- | ----------------- | --------------------- | ----------- |
| <a name="CSIP51"></a>CSIP51 | File mime type | amdSec/digiprovMD/mdRef/@MIMETYPE; amdSec/rightsMD/mdRef/@MIMETYPE | The IANA media type for the external file, mandatory in CS IP | 1..1 |
| <a name="CSIP52"></a>CSIP52 | File size | amdSec/digiprovMD/mdRef/@SIZE; amdSec/rightsMD/mdRef/@SIZE | Size of linked file in bytes, mandatory in CS IP | 1..1 |
| <a name="CSIP53"></a>CSIP53 | File creation date | amdSec/digiprovMD/mdRef/@CREATED; amdSec/rightsMD/mdRef/@CREATED | Date the linked file was created, mandatory in CS IP | 1..1 |
| <a name="CSIP54"></a>CSIP54 | File checksum | amdSec/digiprovMD/mdRef/@CHECKSUM; amdSec/rightsMD/mdRef/@CHECKSUM | The checksum of the linked file, mandatory in CS IP | 1..1 |
| <a name="CSIP55"></a>CSIP55 | File checksum type | amdSec/digiprovMD/mdRef/@CHECKSUMTYPE; amdSec/rightsMD/mdRef/@CHECKSUMTYPE | The type of checksum used for calculating the checksum of the linked file, mandatory in CS IP | 1..1 |

Full example of the METS <amdSec> element:
```xml
<mets:amdSec>
    <mets:digiprovMD ID="uuid-9124DA4D-3736-4F69-8355-EB79A22E943F" CREATED="2018-04-24T14:37:52.783+01:00" STATUS="Current">
        <mets:mdRef LOCTYPE="URL" xlink:type="simple" xlink:href="metadata/preservation/premis1.xml" MDTYPE="PREMIS:EVENT" MDTYPEVERSION="3.0" MIMETYPE="text/xml" SIZE="1211" CREATED="2018-04-24T14:37:52.783+01:00" CHECKSUM="8aa278038dbad54bbf142e7d72b493e2598a94946ea1304dc82a79c6b4bac3d5" CHECKSUMTYPE="SHA-256" LABEL="premis1.xml"/>
    </mets:digiprovMD>
    <mets:digiprovMD ID="uuid-48C18DD8-2561-4315-AC39-F941CBB138B3" CREATED="2018-04-24T14:47:52.783+01:00" STATUS="Current">
        <mets:mdRef LOCTYPE="URL" xlink:type="simple" xlink:href="metadata/preservation/premis2.xml" MDTYPE="PREMIS:OBJECT" MDTYPEVERSION="3.0" MIMETYPE="text/xml" SIZE="2854" CREATED="2018-04-24T14:37:52.783+01:00" CHECKSUM="d1dfa585dcc9d87268069dc58d5e47956434ec3db4087a75a3885d287f15126f" CHECKSUMTYPE="SHA-256" LABEL="premis2.xml"/>
    </mets:digiprovMD>
</mets:amdSec>
```
