---
title: Use of the METS root element
---
### 5.3.1.	Use of the METS root element (element mets)
The purpose of the METS root element is to describe the container for the information being stored and/or transmitted, which is held within the seven sections of the METS file. The root element of a METS document has five attributes derived from the official METS specification and one attribute added for the purposes of the CS IP.

In addition to these six attributes the METS root element mets MUST define all relevant namespaces and locations of XML schemas using the `@xmlns` and `@xsi:schemaLocation` attributes. In case XML schemas have been included into the package (i.e. placed into the `schemas` folder) it is recommended to link to the schemas using the relative path of the schema file (i.e. `schemas/mets.xsd`). The specific requirements for the root element and its attributes are described in the following table .

{% comment %} PAGE TEMPLATING : DO NOT DELETE THIS OR THE FOLLOWING LINE {% endcomment %}
{% include_relative requirements.md %}

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
