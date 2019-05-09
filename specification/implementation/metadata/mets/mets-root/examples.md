
**Example:** METS root element example with content not in the value list @TYPE being transferred

```xml
<mets:mets OBJID="uuid-4422c185-5407-4918-83b1-7abfa77de182" LABEL="Sample CSIP Information Package" TYPE="OTHER" OTHERTYPE="Patterns" PROFILE="https://earkcsip.dilcis.eu/profile/CSIP.xml" schemaLocation="http://www.loc.gov/METS/ http://www.loc.gov/standards/mets/mets.xsd http://www.w3.org/1999/xlink http://www.loc.gov/standards/mets/xlink.xsd https://dilcis.eu/XML/METS/CSIPExtensionMETS https://dilcis.eu/XML/METS/CSIPExtensionMETS/DILCISExtensionMETS.xsd">
</mets:mets>
```


**Example:** METS root element example with transferred content of a type from the fixed value list and the representation content is not part of the value list, but is instead a value decided upon in the submission agreement.

```xml
<mets:mets OBJID="uuid-4422c185-5407-4918-83b1-7abfa77de182" LABEL="Sample CSIP Information Package" TYPE="Datasets" CONTENTINFORMATIONTYPE="OTHER" OTHERCONTENTINFORMATIONTYPE="FGS Personal, version 1" PROFILE="https://earkcsip.dilcis.eu/profile/CSIP.xml" schemaLocation="http://www.loc.gov/METS/ http://www.loc.gov/standards/mets/mets.xsd http://www.w3.org/1999/xlink http://www.loc.gov/standards/mets/xlink.xsd https://dilcis.eu/XML/METS/CSIPExtensionMETS https://dilcis.eu/XML/METS/CSIPExtensionMETS/DILCISExtensionMETS.xsd">
</mets:mets>
```

