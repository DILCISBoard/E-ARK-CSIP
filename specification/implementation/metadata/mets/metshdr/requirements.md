| ID | Name & Location | Description & usage | Cardinality & Level |
| -- | --------------- | ------------------- | ------------------- |
| <a name="CSIP7"></a>**CSIP7** | **Package creation date** <br/> `metsHdr/@CREATEDATE` | @CREATEDATE describes the date of creation of the package. | **1..1** <br/> MUST |
| <a name="CSIP8"></a>**CSIP8** | **Package last modification date** <br/> `metsHdr/@LASTMODDATE` | @LASTMODDATE is mandatory if the package has been modified. | **0..1** <br/> SHOULD |
| <a name="CSIP9"></a>**CSIP9** | **OAIS Package type information** <br/> `metsHdr/@csip:OAISPACKAGETYPE` | @csip:OAISPACKAGETYPE is an attribute added by the CSIP for describing the type of the IP. <br/> **See also:** <a href="#VocabularyOAISPackageType" >OAIS Package type</a> | **1..1** <br/> MUST |
| <a name="CSIP10"></a>**CSIP10** | **Agent** <br/> `metsHdr/agent` | One mandatory agent is used to describe the software used for creating the package. Other uses of agents are described in the own implementations extending profile. | **1..n** <br/> MUST |
| <a name="CSIP11"></a>**CSIP11** | **Agent role** <br/> `metsHdr/agent/@ROLE` | The role of the mandatory agent is “CREATOR”. | **1..1** <br/> MUST |
| <a name="CSIP12"></a>**CSIP12** | **Agent type** <br/> `metsHdr/agent/@TYPE` | The type of the mandatory agent is “OTHER”. | **1..1** <br/> MUST |
| <a name="CSIP13"></a>**CSIP13** | **Agent other type** <br/> `metsHdr/agent/@OTHERTYPE` | The other type of the mandatory agent is “SOFTWARE”. <br/> **See also:** <a href="#VocabularyAgentOtherType" >Other agent type</a> | **1..1** <br/> MUST |
| <a name="CSIP14"></a>**CSIP14** | **Agent name** <br/> `metsHdr/agent/name` | The name of the mandatory agent is the name of the software tool which was used to create the IP. | **1..1** <br/> MUST |
| <a name="CSIP15"></a>**CSIP15** | **Agent additional information** <br/> `metsHdr/agent/note` | The mandatory agent has a note providing the version information for the tool which was used to create the IP. | **1..1** <br/> MUST |
| <a name="CSIP16"></a>**CSIP16** | **Classification of the agent additional information** <br/> `metsHdr/agent/note/@csip:NOTETYPE` | The mandatory agent note is typed with the fixed value of "SOFTWARE VERSION". <br/> **See also:** <a href="#VocabularyNoteType" >Note type</a> | **1..1** <br/> MUST |
