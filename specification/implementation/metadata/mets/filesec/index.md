### 5.3.5.	Use of the METS file section (element fileSec)
Use of the METS fileSec element is highly recommended by the CS IP (although not mandatory). It should describe all components of the IP which have not been already included in the amdSec and dmdSec elements. For all files the location and checksum need to be available. Therefore the main purpose of the
METS file section is to serve as a “table of contents” or “manifest” and allow validating the integrity of the files included into the package.
The main requirement of the CS IP is that the file section of both the root and representation METS files includes at least one file group (element fileGrp). This so-called “Common Specification file group” should
follow the requirements below:
- The file group should be defined by a single fileGrp element
  - It is mandatory to use the `@USE` attribute with a fixed value of either “Root” (for the root METS file) or “Representation [representation ID]” (for the representation METS file if available)
  - Example: `<fileGrp USE=“Root”>`
- Each of the structural components (i.e. documentation, schemas, data) should be described by its own nested fileGrp element
  - The value of the `@USE` attribute of the nested fileGrp element should reflect the name of the folder (i.e. USE=“documentation”; USE=“data”; USE=“schemas”);
- In case representations include their own METS files, the components (including data files) of a representation should be described only in the representation METS. The root METS file should still include a fileGrp for each representation but only reference the METS.xml file of the representation.

The specific requirements for elements, sub-elements and attributes are listed in the following table. Note that use of the stream and transformFile elements are not discussed below. Implementers wishing to use either of these METS elements should follow the requirements in the METS documentation.

| ID | Name | Element/Attribute | Description and usage | Cardinality |
| -- | ---- | ----------------- | --------------------- | ----------- |
| <a name="CSIP56"></a>CSIP56 | File section | fileSec | Recommended to include one fileSec element in each METS file | 0..1 |
| <a name="CSIP57"></a>CSIP57 | File group | fileSec/fileGrp | This specification requires that one specific occurrence of the fileGrp element is included as described above. Implementers are welcome to define and add additional file groups necessary for internal purposes. The main fileGrp element includes additional nested fileGrp elements, one for each folder of the package (except metadata described in amdSec and dmdSec). | 1..n |
| <a name="CSIP58"></a>CSIP58 | File group version date | fileSec/fileGrp/@VERSDATE | Version date of the file grouping | 0..1 |
| <a name="CSIP59"></a>CSIP59 | Reference to administrative metadata | fileSec/fileGrp/@ADMID | In case administrative metadata is available about the file group, this element must reference the appropriate ID of the administrative metadata section. | 0..1 |
| <a name="CSIP60"></a>CSIP60 | File group intended use | fileSec/fileGrp/@USE | Required in CS IP with one occurrence bearing the values “Root” (for the root fileGrp element and the names of appropriate folders for nested fileGrp occurrences. | 1..1 |
| <a name="CSIP61"></a>CSIP61 | Files | fileSec/fileGrp/file | The CS IP requires that fileGrp must contain at least one file element pointing to described content files | 1..n |
| <a name="CSIP62"></a>CSIP62 | File element ID | fileSec/fileGrp/file/@ID | Mandatory, must be unique across the package | 1..1 |
| <a name="CSIP63"></a>CSIP63 | Mime type of referenced file | fileSec/fileGrp/file/@MIMETYPE | The IANA mime type for the wrapped or linked file. Required by the Common Specification. | 1..1 |
| <a name="CSIP64"></a>CSIP64 | File sequencing | fileSec/fileGrp/file/@SEQ | Used to describe the sequence of files listed within the fileGrp element | 0..1 |
| <a name="CSIP65"></a>CSIP65 | File size | fileSec/fileGrp/file/@SIZE | Size of the linked or embedded file in bytes. Required by the Common Specification | 1..1 |
| <a name="CSIP66"></a>CSIP66 | Date file created | fileSec/fileGrp/file/@CREATED | Date the embedded/linked file was created. Required by the Common Specification | 1..1 |
| <a name="CSIP67"></a>CSIP67 | File checksum | fileSec/fileGrp/file/@CHECKSUM | The checksum of the embedded/linked file. Required by the Common Specification | 1..1 |
| <a name="CSIP68"></a>CSIP68 | File checksum type | fileSec/fileGrp/file/@CHECKSUMTYPE | The type of checksum used for the embedded/linked file. Required by the Common Specification | 1..1 |
| <a name="CSIP69"></a>CSIP69 | File owner ID | fileSec/fileGrp/file/@OWNERID | Unique ID of the file assigned by its owner | 0..1 |
| <a name="CSIP70"></a>CSIP70 | Reference to administrative metadata | fileSec/fileGrp/file/@ADMID | In case administrative metadata is available about the file, this element must referencethe appropriate ID of the administrative metadata section. | 0..1 |
| <a name="CSIP71"></a>CSIP71 | Reference to descriptive metadata | fileSec/fileGrp/file/@DMDID | Value for the ID attribute of the dmdSec containing metadata describing the content files listed in this file element. | 0..1 |
| <a name="CSIP72"></a>CSIP72 | File intended use | fileSec/fileGrp/file/@USE | Statement about intended use of the files | 0..1 |
| <a name="CSIP73"></a>CSIP73 | File location | fileSec/fileGrp/file/FLocat | The location of each file within the information package must be given by the `<FLocat>` element using the same rules as for referencing metadata files.| 1..1 |
| <a name="CSIP74"></a>CSIP74 | File locator | fileSec/fileGrp/file/FLocat/@LOCTYPE | Mandatory locator pointing to the external file. | 1..1 |
| <a name="CSIP75"></a>CSIP75 | Locator type | fileSec/fileGrp/file/FLocat/@LOCTYPE | Within the CS IP the value of @LOCTYPE attribute must be "URL". | 1..1 |
| <a name="CSIP76"></a>CSIP76 | XLink type | fileSec/fileGrp/file/FLocat/@xlink:type | The type of the link. The @xlink:type attribute is mandatory in the CS IP | 1..1 |
| <a name="CSIP77"></a>CSIP77 | XLink type | fileSec/fileGrp/file/FLocat/@xlink:type | The CS IP requires the use of @xlink:type attribute with the fixed value “simple” | 1..1 |
| <a name="CSIP78"></a>CSIP78 | XLink location | dmdSec/mdRef/@xlink:href | The actual location of the resource.<br/>This specification recommends the recording of an URL type filepath within this attribute.<br/>Further, the filepath must be decoded consistently throughout all mdRef elements within the information package, and follow the requirements for referencing as described in Section 5.2. | 1..1 |
| <a name="CSIP79"></a>CSIP79 | File intended use | fileSec/fileGrp/file/FLocat/@USE | Statement about intended use of the linked file | 0..1 |

Example of the fileSec element:
```xml
<mets:fileSec>
    <mets:fileGrp ID="uuid-5811D494-6045-4741-924C-A1CFA340C276" USE="Root">
        <mets:fileGrp ID="uuid-075D87BB-FAFA-4718-A7E7-FFF5FE2FF210" USE="metadata">
            <mets:fileGrp ID="uuid-6290B550-E965-41CC-9DAF-BA0AA777C320" USE="descriptive"/>
            <mets:fileGrp ID="uuid-727ADA39-BDA7-4B34-9B83-85C15860EA22" USE="preservation"/>
        </mets:fileGrp>
        <mets:fileGrp ID="uuid-EB965AD6-198A-40D6-B361-8D179E9909A8" USE="representations">
            <mets:fileGrp ID="uuid-0AF57CAC-667B-4993-9555-99E24BA1093E" USE="Submission">
                <mets:fileGrp ID="uuid-3C1A884C-11AA-4B1-A230-496F3569610D" USE="Data">
                    <mets:file ID="uuid-0C0049CA-6DE0-4A6D-8699-7975E4046A81" MIMETYPE="application/vnd.openxmlformats-officedocument.wordprocessingml.document" SIZE="2554366" CREATED="2012-08-15T12:08:15.432+01:00" CHECKSUM="91B7A2C0A1614AA8F3DAF11DB4A1C981F14BAA25E6A0336F715B7C513E7A1557" CHECKSUMTYPE="SHA-256">
                        <mets:FLocat LOCTYPE="URL" xlink:type="simple" xlink:href="representations/Submission/File.docx"/>
                    </mets:file>
                </mets:fileGrp>
            </mets:fileGrp>
            <mets:fileGrp ID="uuid-7C328264-2BD7-45CD-AF37-4611DC537371" ADMID="uuid-9124DA4D-3736-4F69-8355-EB79A22E943F" USE="Ingest">
                <mets:fileGrp ID="uuid-BF73CA45-CC70-4704-ACFA-BCDFD35F9A20" USE="Data">
                    <mets:file ID="uuid-EE23344D-4F64-40C1-8E18-75839EF661FC" MIMETYPE="application/pdf" SIZE="1338744" CREATED="2018-04-24T14:37:49.617+01:00" CHECKSUM="7176A627870CFA3854468EC43C5A56F9BD8B30B50A983B8162BF56298A707667" CHECKSUMTYPE="SHA-256" ADMID="uuid-48C18DD8-2561-4315-AC39-F941CBB138B3">
                        <mets:FLocat LOCTYPE="URL" xlink:type="simple" xlink:href="representations/Ingest/File.pdf"/>
                    </mets:file>
                </mets:fileGrp>
            </mets:fileGrp>
        </mets:fileGrp>
        <mets:fileGrp ID="uuid-4ACDC6F3-8A36-4A00-A85F-84A56415E86F" USE="schemas">
            <mets:file ID="uuid-A1B7B0DA-E129-48EF-B431-E553F2977FD6" MIMETYPE="text/xsd" SIZE="123917" CREATED="2018-04-24T14:37:49.617+01:00" CHECKSUM="0BF9E16ADE296EF277C7B8E5D249D300F1E1EB59F2DCBD89644B676D66F72DCC" CHECKSUMTYPE="SHA-256">
                <mets:FLocat LOCTYPE="URL" xlink:type="simple" xlink:href="schemas/ead2002.xsd"/>
            </mets:file>
        </mets:fileGrp>
    </mets:fileGrp>
</mets:fileSec>

```
