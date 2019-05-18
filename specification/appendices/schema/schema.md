## External Schema

### E-ARK CSIP METS Extension
**Location:**  <a href="http://earkcsip.dilcis.eu/schema/DILCISExtensionMETS.xsd" >http://earkcsip.dilcis.eu/schema/DILCISExtensionMETS.xsd</a> <br/> 
**Context:** XML-schema for the attributes added by CSIP <br/> 
**Note:**   <br/> 
An extension schema with the added attributes for use in this profile. <br/> 
The schema is identified using the namespace prefix csip. <br/> 


### PREMIS
**Location:**  <a href="http://www.loc.gov/standards/premis/" >http://www.loc.gov/standards/premis/</a> <br/> 
**Context:** Used for preservation metadata <br/> 
**Note:**   <br/> 
A rule set for use with this profile is under development. <br/> 

## Controlled Vocabularies

### Content information type specification
<a name="VocabularyContentInformationTypeSpecification"></a>
**Maintained By:** DILCIS Board <br/> 
**Location:**  <a href="http://earkcsip.dilcis.eu/schema/CSIPVocabularyContentInformationType.xml" >http://earkcsip.dilcis.eu/schema/CSIPVocabularyContentInformationType.xml</a> <br/> 
**Context:** Values for `@csip:CONTENTINFORMATIONTYPE` <br/> 
**Description:**   <br/> 
Lists the names of specific E-ARK content information type specifications supported or maintained in this METS profile. <br/> 


### Content Category
<a name="VocabularyContentCategory"></a>
**Maintained By:** DILCIS Board <br/> 
**Location:**  <a href="http://earkcsip.dilcis.eu/schema/CSIPVocabularyContentCategory.xml" >http://earkcsip.dilcis.eu/schema/CSIPVocabularyContentCategory.xml</a> <br/> 
**Context:** Values for `mets/@type` <br/> 
**Description:**   <br/> 
Declares the categorical classification of package content. <br/> 


### OAIS Package type
<a name="VocabularyOAISPackageType"></a>
**Maintained By:** DILCIS Board <br/> 
**Location:**  <a href="http://earkcsip.dilcis.eu/schema/CSIPVocabularyOAISPackageType.xml" >http://earkcsip.dilcis.eu/schema/CSIPVocabularyOAISPackageType.xml</a> <br/> 
**Context:** Values for `@csip:OAISPACKAGETYPE` <br/> 
**Description:**   <br/> 
Describes the OAIS type the package belongs to in the OAIS reference model. <br/> 


### Note type
<a name="VocabularyNoteType"></a>
**Maintained By:** DILCIS Board <br/> 
**Location:**  <a href="http://earkcsip.dilcis.eu/schema/CSIPVocabularyNoteType.xml" >http://earkcsip.dilcis.eu/schema/CSIPVocabularyNoteType.xml</a> <br/> 
**Context:** Values for `@csip:NOTETYPE` <br/> 
**Description:**   <br/> 
Provides values for the type of a note for an agent. <br/> 


### Other agent type
<a name="VocabularyAgentOtherType"></a>
**Maintained By:** DILCIS Board <br/> 
**Location:**  <a href="http://earkcsip.dilcis.eu/schema/CSIPVocabularyAgentOtherType.xml" >http://earkcsip.dilcis.eu/schema/CSIPVocabularyAgentOtherType.xml</a> <br/> 
**Context:** Values for `metsHdr/agent/@OTHERTYPE` <br/> 
**Description:**   <br/> 
Describes the other agent types supported by the profile. <br/> 


### Identifier type
<a name="VocabularyIdentifierType"></a>
**Maintained By:** Library of Congress <br/> 
**Location:**  <a href="http://id.loc.gov/vocabulary/identifiers.html" >http://id.loc.gov/vocabulary/identifiers.html</a> <br/> 
**Context:** Values for `metsHdr/altRecordID/@TYPE` <br/> 
**Description:**   <br/> 
Describes the type of the identifier. <br/> 


### dmdSec status
<a name="VocabularyStatus"></a>
**Maintained By:** DILCIS Board <br/> 
**Location:**  <a href="http://earkcsip.dilcis.eu/schema/CSIPVocabularyStatus.xml" >http://earkcsip.dilcis.eu/schema/CSIPVocabularyStatus.xml</a> <br/> 
**Context:** Values for `dmdSec/@STATUS` <br/> 
**Description:**   <br/> 
Describes the status of the descriptive metadata section (dmdSec) which is supported by the profile. <br/> 


### IANA media types
<a name="VocabularyIANAmediaTypes"></a>
**Maintained By:** IANAs <br/> 
**Location:**  <a href="https://www.iana.org/assignments/media-types/media-types.xhtml" >https://www.iana.org/assignments/media-types/media-types.xhtml</a> <br/> 
**Context:** Values for `@MIMETYPE` <br/> 
**Description:**   <br/> 
Valid values for the mime types of referenced files. <br/> 


### File group names
<a name="VocabularyFileGrpAndStructMapDivisionLabel"></a>
**Maintained By:** DILCIS Board <br/> 
**Location:**  <a href="http://earkcsip.dilcis.eu/schema/CSIPVocabularyFileGrpAndStructMapDivisionLabel.xml" >http://earkcsip.dilcis.eu/schema/CSIPVocabularyFileGrpAndStructMapDivisionLabel.xml</a> <br/> 
**Context:** Values for `fileGrp/@USE` <br/> 
**Description:**   <br/> 
Describes the uses of the file group (fileGrp) that are supported by the profile. <br/> 
Own names should be placed in an own extending vocabulary. <br/> 


### Structural map typing
<a name="VocabularyStructMapType"></a>
**Maintained By:** DILCIS Board <br/> 
**Location:**  <a href="http://earkcsip.dilcis.eu/schema/CSIPVocabularyStructMapType.xml" >http://earkcsip.dilcis.eu/schema/CSIPVocabularyStructMapType.xml</a> <br/> 
**Context:** Values for `structMap/@TYPE <br/> 
**Description:**   <br/> 
Describes the type of the structural map (structMap) that is supported by the profile. <br/> 
Own types should be placed in an own extending vocabulary. <br/> 


### Structural map label
<a name="VocabularyStructMapLabel"></a>
**Maintained By:** DILCIS Board <br/> 
**Location:**  <a href="http://earkcsip.dilcis.eu/schema/CSIPVocabularyStructMapLabel.xml" >http://earkcsip.dilcis.eu/schema/CSIPVocabularyStructMapLabel.xml</a> <br/> 
**Context:** Values for `structMap/@LABEL` <br/> 
**Description:**   <br/> 
Describes the label of the structural map that is supported by the profile. <br/> 
Own labels should be placed in an own extending vocabulary. <br/> 

