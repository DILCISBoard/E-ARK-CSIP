---
title: Use of the METS descriptive metadata section
---
### 5.3.3 Use of the METS descriptive metadata section (element dmdSec)

The purpose of the METS descriptive data section is to embed or refer to files containing descriptive metadata.

The CS IP as such does not make any assumptions on the use of specific descriptive metadata schemas. As such, implementers are welcome to use descriptive metadata following any standards inside a CS IP package.

Specific elements for which the exact use is fixed within this specification are highlighted in the following table.

| ID | Name | Element/Attribute | Description and usage | Cardinality |
| -- | ---- | ----------------- | --------------------- | ----------- |
| <a name="CSIP20"></a>CSIP20 | Descriptive metadata section | dmdSec | Must be used if descriptive metadata about the package content is available.<br/> NOTE: According to official METS documentation each metadata section must describe one and only one set of metadata. As such, if implementers want to include multiple occurrences of descriptive metadata into the package this must be done by repeating the whole dmdSec element for each individual metadata. | 0..n |
| <a name="CSIP21"></a>CSIP21 | dmdSec ID | dmdSec/@ID | Mandatory, identifier must be unique within the package | 1..1 |
| <a name="CSIP22"></a>CSIP22 | Reference to administrative metadata | dmdSec/@ADMID | In case administrative (provenance) metadata is available and described within METS about changes to the descriptive metadata, this element must reference the appropriate ID of the administrative metadata section. | 0..1 |
| <a name="CSIP23"></a>CSIP23 | Date created | dmdSec/@CREATED | Required by this specification. Creation date of the metadata in this section, needed to track changes to metadata files. | 1..1 |
| <a name="CSIP24"></a>CSIP24 | Metadata status | dmdSec/@STATUS | Status of the metadata. Recommended for use to indicate currency of package. If used it is recommended to use one of the two values “SUPERSEDED” or “CURRENT”. | 0..1 |

According to the METS specification metadata files themselves must either be referenced from the dmdSec using the mdRef element or wrapped into the dmdSec using the mdWrap element. This specification requires the storage of metadata as distinct files within the information package (i.e. the use of the mdRef element). In the case of physically separated metadata files it is easier for long-term repositories to check the integrity of metadata, extract it if necessary – ultimately have it easier to manage, update and reuse any metadata within the information package.

**Use of mdRef**

| ID | Name | Element/Attribute | Description and usage | Cardinality |
| -- | ---- | ----------------- | --------------------- | ----------- |
| <a name="CSIP25"></a>CSIP25 | External metadata link | dmdSec/mdRef | Reference to the descriptive metadata file stored in the “metadata” folder of the IP.<br/>In each occurrence of the dmdSec exactly one occurrence of the mdRef element must be present. | 0..1 |

**Location group**

The following group of elements describes the actual location of the references metadata file

| ID | Name | Element/Attribute | Description and usage | Cardinality |
| -- | ---- | ----------------- | --------------------- | ----------- |
| <a name="CSIP26"></a>CSIP26 | Locator type | dmdSec/mdRef/@LOCTYPE | Specifies the locator type used in the @xlink:href attribute which points to the file. The @LOCTYPE attribute is mandatory in the CS IP | 1..1 |
| <a name="CSIP27"></a>CSIP27 | Locator type | dmdSec/mdRef/@LOCTYPE | Within the CS IP the value of @LOCTYPE attribute must be "URL". | 1..1 |
| <a name="CSIP28"></a>CSIP28 | XLink type | dmdSec/mdRef/@xlink:type | The type of the link. The @xlink:type attribute is mandatory in the CS IP | 1..1 |
| <a name="CSIP29"></a>CSIP29 | XLink type | dmdSec/mdRef/@xlink:type | The CS IP requires the use of @xlink:type attribute with the fixed value “simple” | 1..1 |
| <a name="CSIP30"></a>CSIP30 | XLink location | dmdSec/mdRef/@xlink:href | The actual location of the resource.<br/>This specification requires the recording of an URL type filepath within this attribute.<br/>Further, the filepath must be decoded consistently throughout all mdRef elements within the information package, and follow the requirements for referencing as described in Section 5.2. | 1..1 |

**Metadata group**

The following group of elements describes the type and version of metadata being referenced.

| ID | Name | Element/Attribute | Description and usage | Cardinality |
| -- | ---- | ----------------- | --------------------- | ----------- |
| <a name="CSIP31"></a>CSIP31 | Type of metadata | dmdSec/mdRef/@MDTYPE | Specifies the type of metadata in the linked file. Values should be taken from the METS list provided. | 1..1 |

**File core group**

The following elements describe the core characteristics of the referenced metadata file.

| ID | Name | Element/Attribute | Description and usage | Cardinality |
| -- | ---- | ----------------- | --------------------- | ----------- |
| <a name="CSIP32"></a>CSIP32 | File mime type | dmdSec/mdRef/@MIMETYPE | The IANA media type for the external file, mandatory in CS IP | 1..1 |
| <a name="CSIP33"></a>CSIP33 | File size | dmdSec/mdRef/@SIZE | Size of linked file in bytes, mandatory in CS IP | 1..1 |
| <a name="CSIP34"></a>CSIP34 | File creation date | dmdSec/mdRef/@CREATED | Date the linked file was created, mandatory in CS IP | 1..1 |
| <a name="CSIP35"></a>CSIP35 | File checksum | dmdSec/mdRef/@CHECKSUM | The checksum of the linked file, mandatory in CS IP | 1..1 |
| <a name="CSIP36"></a>CSIP36 | File checksum type | dmdSec/mdRef/@CHECKSUMTYPE | The type of checksum used for calculating the checksum of the linked file, mandatory in CS IP | 1..1 |

Example of the METS `<dmdSec>` element:
```xml
<mets:dmdSec ID="uuid-906F4F12-BA52-4779-AE2C-178F9206111F" CREATED="2018-04-24T14:37:49.609+01:00">
    <mets:mdRef LOCTYPE="URL" MDTYPE="EAD" xlink:type="simple"  xlink:href="metadata/descriptive/ead2002.xml" mimetype="application/xml" SIZE="903" CREATED="2018-04-24T14:37:49.609+01:00" CHECKSUM="F24263BF09994749F335E1664DCE0086DB6DCA323FDB6996938BCD28EA9E8153" CHECKSUMTYPE="SHA-256"/>
</mets:dmdSec>
```
