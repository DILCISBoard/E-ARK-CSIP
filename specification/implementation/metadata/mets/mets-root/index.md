### 5.3.1.	Use of the METS root element (element mets)
The purpose of the METS root element is to describe the container for the information being stored and/or transmitted, which is held within the seven sections of the METS file. The root element of a METS document has five attributes derived from the official METS specification and one attribute added for the purposes of the CS IP.

In addition to these six attributes the METS root element mets MUST define all relevant namespaces and locations of XML schemas using the `@xmlns` and `@xsi:schemaLocation` attributes. In case XML schemas have been included into the package (i.e. placed into the `schemas` folder) it is recommended to link to the schemas using the relative path of the schema file (i.e. `schemas/mets.xsd`). The specific requirements for the root element and its attributes are described in the following table .

| ID | Name | Element/Attribute | Description and usage | Cardinality |
| -- | ---- | ----------------- | --------------------- | ----------- |
| <a name="CSIP1"></a>CSIP1 | METS root element   | mets | The root level element that is required in all METS documents | 1..1 |
| <a name="CSIP2"></a>CSIP2 | Content ID | mets/@OBJID | Mandatory in this specification. It is recommended that it be the same as the name or ID of the package (the name of the root folder). The OBJID must meet the principle of being unique at least across the repository | 1..1 |
| <a name="CSIP3"></a>CSIP3 |  General content type | mets/@TYPE | Mandatory in this specification. The TYPE attribute must be used for identifying the type of the package (genre), for example ERMS, RDBMS, digitised construction plans. However, there is no fixed vocabulary and as such implementers are welcome to use values most suitable for their needs.| 1..1 |
| <a name="CSIP4"></a>CSIP4 | Content Information Type Specification name | @CONTENTTYPESPECIFICATION | An attribute added by this specification. It describes which content information type specification is used for the content. Values of the attribute are fixed in the following vocabulary:<br/>1. SMURFERMS<br/>2. SMURFSFSB<br/>3. SIARD1<br/>4. SIARD2<br/>5. SIARDDK<br/>6. GeoVectorGML<br/>7. GeoRasterGeotiff<br/>8. MIXED<br/>9. OTHER<br/>NB The vocabulary is extensible as additional content information type specifications are developed. | 1..1 |
| <a name="CSIP5"></a>CSIP5 | Other Content Information Type Specification | @OTHERCONTENTTYPESPECIFICATION | An attribute added by this specification. In case the value "OTHER" has been selected for the @CONTENTTYPESPECIFICATION attribute, this attribute MUST be used to record the name of the content information type specification  | 0..1 |
| <a name="CSIP6"></a>CSIP6 | METS profile | @PROFILE | Mandatory in this specification. The PROFILE attribute has to have as its value the URL of the used profile. When this profile is implemented as is the value is the URL for the official CS IP METS Profile. | 1..1 |

Full example of the METS root element:
```xml
<mets:mets xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:mets="http://www.loc.gov/METS/"
    xmlns:xlink="http://www.w3.org/1999/xlink"
    xmlns:csip="DILCIS"
    OBJID="uuid-4422c185-5407-4918-83b1-7abfa77de182"
    LABEL="Sample CS IP Information Package"
    TYPE="Database"
    csip:CONTENTTYPESPECIFICATION="SIARD2"
    PROFILE="http://www.eark-project.com/METS/IP.xml"
    xsi:schemaLocation="http://www.loc.gov/METS/ http://www.loc.gov/standards/mets/mets.xsd http://www.w3.org/1999/xlink http://www.loc.gov/standards/mets/xlink.xsd">
</mets:mets>
```
