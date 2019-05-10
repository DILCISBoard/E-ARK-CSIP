### 5.3.1.	Use of the METS root element (element mets)
The METS document's root element (`<mets>`) describe the container for the information being stored and/or transmitted. The root `<mets>` element of a CSIP conformant METS document uses attributes from the METS specification and additional attributes defined in the CSIP extension schema.

As well as the METS and CSIP extension attributes, the METS document's root `<mets>` element needs to define all of the relevant namespaces and locations for XML schema used in the package. This is done using the @xmlns and @xsi:schemaLocation attributes as described in ["Referencing a Schema in an XML Document"](https://www.w3schools.com/xml/schema_schema.asp). The schema identifiers and locations for a typical CSIP `<mets>` element are shown below:

```xml
<mets xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns="http://www.loc.gov/METS/"
  xmlns:csip="https://DILCIS.eu/XML/METS/CSIPExtensionMETS"
  xmlns:xlink="http://www.w3.org/1999/xlink"
  xsi:schemaLocation="http://www.w3.org/2001/XMLSchema-instance schemas/XMLSchema.xsd http://www.loc.gov/METS/ schemas/mets.xsd http://www.w3.org/1999/xlink schemas/xlink.xsd https://DILCIS.eu/XML/METS/CSIPExtensionMETS schemas/CSIPExtensionMETS.xsd" ... >
```

When implementing and using XML schemas the physical location of any schemas needs to be considered accounting for potential unavailability of any resources required for validation that are hosted externally.

In case XML schemas have been included into the package (i.e. placed into the `schemas` folder) it is recommended to link to the schemas using the relative path of the schema file (i.e. `schemas/mets.xsd`).

The specific requirements for the root element and its attributes are described in the following table .
