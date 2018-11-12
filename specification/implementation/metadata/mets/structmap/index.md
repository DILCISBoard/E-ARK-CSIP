---
title: Use of the METS structural map
---
### 5.3.6.	Use of the METS structural map (element structMap)
The METS structural map section is the only element mandatory in the METS specification and it is intended
to provide an overview of ALL components of a CS IP Information Package. It also links the elements of that
structure to associated content files and metadata. It is a mandatory and ultimate means to define the full
structure of the package – including metadata, representations, schemas, documentation and user added
components and folders. In other words, tools compatible with the CS IP will count on the information
available within the structMap element as the primary means of identifying all components of the package.
As such it is the most crucial component for the validation of any CS IP Information Package and must
always be present.
The CS IP requires the inclusion of one structural map according to the principles described below.
However, implementers are welcome to define additional structural maps for their internal purposes by
repeating the structMap element.
The most crucial requirements for the CS IP mandated structural map are as follows:

- The structMap element has a mandatory attribute @LABEL which has the fixed value of “CS IP StructMap”. The @LABEL attribute is used to distinguish the Common
Specification mandated structural map occurrence from any other, user-defined, structural maps.
As such we can also derive the requirement, that any user-defined structural maps must not use
the LABEL value of “CS IP StructMap”;
- The internal structure of the structural map (expressed by hierarchical div elements) follows the CS
IP physical structure as described in Section 4, therefore grouping together metadata,
representations, schemas, documentation and user-defined folders;
  - All div elements must use the attribute LABEL with the value being the name of the folder
(as an example “metadata”)
- In  case both root and representation METS files exist, the structural map in the root METS file
  - Lists all files in all folders with the exception of the content of the representation folders
  - Lists all representations (as separate div elements)
  - Lists only the appropriate representation METS file using the mptr element as the content of the representation
- The structural map in a representation METS file lists all files within the representation with no exceptions

The specific requirements for elements, sub-elements and attributes are listed in the following table. Note that the area, seq and par elements are not discussed below.

| ID | Name | Element/Attribute | Description and usage | Cardinality |
| -- | ---- | ----------------- | --------------------- | ----------- |
| <a name="CSIP80"></a>CSIP80 | Structural map | structMap | Each METS file needs to include exactly one structMap element used exactly as described in this table. Institutions can add their own additional custom structural maps as separate structMap sections. | 1..n |
| <a name="CSIP81"></a>CSIP81 | Type of structural map | structMap/@TYPE | Mandatory in this specification. The value must be “physical” | 1..1 |
| <a name="CSIP82"></a>CSIP82 | Structural map name | structMap/@LABEL | Mandatory in this specification. The value must be “Common Specification structural map” | 1..1 |
| <a name="CSIP83"></a>CSIP83 | Structural divisions | structMap/div | Each folder (and sub-folder) within the package must be represented by an occurrence of the `<div>` element. Please note that sub-folders must be represented as nested div elements.<br/>Example:<br/> `<structMap TYPE="physical" LABEL="CS IP StructMap">`<br/>`  <div LABEL="Package123">`<br/>`     <div LABEL="metadata">` | 0..n |
| <a name="CSIP84"></a>CSIP84 | Structural division ID | structMap/div/@ID | Mandatory, identifier must be unique within the package | 1..1 |
| <a name="CSIP85"></a>CSIP85 | Structural division name | structMap/div/@LABEL | Mandatory, value must be the name of the folder (“metadata”, “descriptive”, “schemas”, “representations”, etc). The LABEL value of the first div element in the package is the ID of the package | 1..1 |
| <a name="CSIP86"></a>CSIP86 | Reference to descriptive metadata | structMap/div/@DMDID | ID attribute values identifying the dmdSec, elements in the METS document that contain or link to descriptive metadata pertaining to the structural division represented by the current div element | 0..1 |
| <a name="CSIP87"></a>CSIP87 | Reference to administrative metadata | structMap/div/@ADMID | No specific requirements | 0..1 |
| <a name="CSIP88"></a>CSIP88 | File pointer | structMap/div/fptr | If the folder which is described by the div element includes computer files these must be referenced by using the fptr element.<br/>The only exception is the description of representations (see below for the use of mptr). | 0..n |
| <a name="CSIP89"></a>CSIP89 | ID of content | structMap/div/fptr/@FILEID | Mandatory, must be the ID used in the appropriate file or mdRef element | 1..1 |
| <a name="CSIP90"></a>CSIP90 | METS pointer | structMap/div/div/mptr | In the case of describing representations within the package (i.e. representations/representation1) the content of the representations must not be described. Instead the `<div>` of the specific representation should include one and only one occurrence of the `<mptr>` element, pointing to the appropriate representation METS file.<br/> The references to representation METS files must be made using the XLink href attribute and the file protocol using the relative location of the file.<br/>Example: `xlink:href="representation/representation1/mets.xml"`<br/>The XLink type attribute is used with the fixed value “simple”.<br/>Example: `xlink:type="simple"`<br/>The LOCTYPE attribute is used with the fixed value ”URL” | 0..n |

Full example of the Common Specification structMap element (root METS file):
```xml
<mets:structMap ID="uuid-1465D250-0A24-4714-9555-5C1211722FB7" TYPE="physical" LABEL="CS IP StructMap">
    <mets:div ID="uuid-638362BC-65D9-4DA7-9457-5156B3965A17" LABEL="uuid-4422c185-5407-4918-83b1-7abfa77de182">
        <mets:div ID="uuid-A4E1C5B6-CD9B-43EF-8F0C-3FD3AB688F80" LABEL="metadata">
            <mets:div ID="uuid-D0C71206-1087-4F38-9E27-7A077898F663" LABEL="descriptive">
                <mets:fptr FILEID="uuid-906F4F12-BA52-4779-AE2C-178F9206111F"/>
            </mets:div>
            <mets:div ID="uuid-A0A8A04A-B7F4-4C30-B084-A119A1C24666" LABEL="preservation">
                <mets:fptr FILEID="uuid-9124DA4D-3736-4F69-8355-EB79A22E943F"/>
                <mets:fptr FILEID="uuid-48C18DD8-2561-4315-AC39-F941CBB138B3"/>
            </mets:div>
        </mets:div>
        <mets:div ID="uuid-35CB3341-D731-4AC3-9622-DB8901CD6735" LABEL="representations">
            <mets:div ID="uuid-F0C49415-9597-42D3-9DA2-40ECFC6CDCD8" LABEL="Submission">
                <mets:mptr LOCTYPE="URL" xlink:type="simple" xlink:href="representations/Submission/METS.xml"/>
            </mets:div>
            <mets:div ID="uuid-861D5F8D-85F2-4419-BF9E-2C916746E36E" LABEL="Ingest">
                <mets:mptr LOCTYPE="URL" xlink:type="simple" xlink:href="representations/Ingest/METS.xml"/>
            </mets:div>
        </mets:div>
        <mets:div ID="uuid-26757DC2-4C0F-4431-85B5-5943D1AB5CA2" LABEL="schemas">
            <mets:fptr FILEID="uuid-A1B7B0DA-E129-48EF-B431-E553F2977FD6"/>
        </mets:div>
    </mets:div>
</mets:structMap>
```
