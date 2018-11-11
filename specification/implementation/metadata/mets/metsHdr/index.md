---
title: Use of the METS header
---
### 5.3.2.	Use of the METS header (element metsHdr)
The purpose of the METS header section is to describe the METS document itself, for example information
about the creator of the IP. The requirements for the metsHdr element, its sub-elements and attributes are presented in the following
table.

| ID | Name | Element/Attribute | Description and usage | Cardinality |
| -- | ---- | ----------------- | --------------------- | ----------- |
| <a name="CSIP7"></a>CSIP7 | METS Header | metsHdr | Element for describing the package itself. Mandatory within the CS IP | 1..1 |
| <a name="CSIP8"></a>CSIP8 | Administrative Metadata ID | metsHdr/@ADMID | Optional, referring to the appropriate administrative metadata section, if used for metadata about the package as a whole. | 0..1 |
| <a name="CSIP9"></a>CSIP9 | Package creation date | metsHdr/@CREATEDATE | Mandatory, the date of creation of the package | 1..1 |
| <a name="CSIP10"></a>CSIP10 | Package last modification date | metsHdr/@LASTMODDATE | Mandatory if relevant (in case the package has been modified) | 0..1 |
| <a name="CSIP11"></a>CSIP11 | OAIS Information Package Type | metsHdr/@csip:OAISPACKAGETYPE | An attribute added by the CS IP for describing the type of the IP. The vocabulary to be used contains values:<br/>- SIP<br/>- AIP<br/>- DIP<br/>- AIU<br/>- AIC<br/>The vocabulary is managed by the DILCIS Board and will be updated when required. | 1..1 |
| <a name="CSIP12"></a>CSIP12 | Agent | metsHdr/agent | The metsHdr must include at least one agent describing the software which has been used to create the package (ROLE=”CREATOR” TYPE=”OTHER” OTHERTYPE=”SOFTWARE”). The use of this element for describing additional agents is optional and nor restricted or recommended by this specification. | 1..n |
| <a name="CSIP13"></a>CSIP13 | Agent role | metsHdr/agent/@ROLE | The role of the agent. The CS IP requires describing at least one agent with the agent/@ROLE value “CREATOR”.<br/>For other (optional) occurrences of agent this attribute shall use a value from the fixed list provided by METS. | 1..1 |
| <a name="CSIP14"></a>CSIP14 | Other agent role | metsHdr/agent/@OTHERROLE | A textual description of the role of the agent in case the value of agent/@ROLE is “OTHER”. | 0..1 |
| <a name="CSIP15"></a>CSIP15 | Agent type | metsHdr/agent/@TYPE | The CS IP requires that at least one instance of the agent element includes the agent/@TYPE attribute with the value “OTHER”. In other occurrences of the agent element the attribute is optional. If used, values defined in official METS documentation shall be followed (“individual”, “organisation”, “other"). | 0..1 |
| <a name="CSIP16"></a>CSIP16 | Other agent type | metsHdr/agent/@OTHERTYPE | The CS IP requires that at least one instance of the agent element includes the agent/@OTHERTYPE attribute with the value “SOFTWARE”.<br/>In other occurrences this attribute shall only be used in case the value of agent/@TYPE is “OTHER”. | 0..1 |
| <a name="CSIP17"></a>CSIP17 | Agent name | metsHdr/agent/name | The name of the agent. If the value of the @OTHERTYPE attribute is “SOFTWARE” this element must provide the name of the software tool which was used to create the IP. | 1..1 |
| <a name="CSIP18"></a>CSIP18 | Note about agent | metsHdr/agent/note | Additional information about the agent. If the value of the @OTHERTYPE attribute is “SOFTWARE” this element MUST provide the version information for the tool which was used to create the IP. | 0..1 |
| <a name="CSIP19"></a>CSIP19 | Type of the note | metsHdr/agent/note/@csip:NOTETYPE | Type attribute for the note element. If the value of the @OTHERTYPE attribute is “SOFTWARE” this attribute must be used with the fixed value "SOFTWARE VERSION" | 0..1 |

Full example of the METS header:
```xml
<mets:metsHdr CREATEDATE="2018-04-24T14:37:49.602+01:00" LASTMODDATE="2018-04-24T14:37:49.602+01:00" RECORDSTATUS="NEW" csip:OAISPACKAGETYPE="SIP">
    <mets:agent ROLE="CREATOR" TYPE="OTHER" OTHERTYPE="SOFTWARE">
        <mets:name>RODA-in</mets:name>
        <mets:note csip:TYPE="SOFTWARE VERSION">2.1.0-beta.7</mets:note>
    </mets:agent>
</mets:metsHdr>
```
