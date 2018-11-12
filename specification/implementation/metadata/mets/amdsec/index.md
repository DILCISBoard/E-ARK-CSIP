---
title: Use of the METS administrative metadata section
---
### 5.3.4.	Use of the METS administrative metadata section (element amdSec)
The purpose of the METS administrative data section is to embed or refer to files containing administrative metadata about the IP content objects. The CS IP (and METS) categorises preservation metadata as administrative metadata, specifically Digital Provenance metadata, hence all preservation metadata should be referenced from a digiprovMD element within the amdSec.

The CS IP allows both the embedding of metadata within the METS.xml file and keeping metadata in external files within the IP. Where preservation metadata is stored in external files (external to the METS file) it should be referenced using the mdRef element. Embedded metadata is wrapped using the `mdWrap` element. Note that for scalability concerns the CS IP recommends the use of mdRef over mdWrap.

The METS amdSec element must include references to all relevant metadata either embedded or in external files located in the folder “metadata/preservation”. This means also that the root level METS.xml
file must refer only to the root level preservation metadata and the representation METS.xml file must refer only to the representation level preservation metadata.

Decision regarding placement of PREMIS in this section is following the guide lines available from PREMIS EC <http://www.loc.gov/standards/premis/guidelines2017-premismets.pdf>.

The specific requirements for the amdSec element, its sub-elements and attributes are presented in the
following table.

{% comment %} PAGE TEMPLATING : DO NOT DELETE THIS OR THE FOLLOWING LINE {% endcomment %}
{% include_relative requirements.md %}

Full example of the METS <amdSec> element:

```xml
<mets:amdSec>
    <mets:digiprovMD ID="uuid-9124DA4D-3736-4F69-8355-EB79A22E943F" CREATED="2018-04-24T14:37:52.783+01:00" STATUS="Current">
        <mets:mdRef LOCTYPE="URL" xlink:type="simple" xlink:href="metadata/preservation/premis1.xml" MDTYPE="PREMIS:EVENT" MDTYPEVERSION="3.0" MIMETYPE="text/xml" SIZE="1211" CREATED="2018-04-24T14:37:52.783+01:00" CHECKSUM="8aa278038dbad54bbf142e7d72b493e2598a94946ea1304dc82a79c6b4bac3d5" CHECKSUMTYPE="SHA-256" LABEL="premis1.xml"/>
    </mets:digiprovMD>
    <mets:digiprovMD ID="uuid-48C18DD8-2561-4315-AC39-F941CBB138B3" CREATED="2018-04-24T14:47:52.783+01:00" STATUS="Current">
        <mets:mdRef LOCTYPE="URL" xlink:type="simple" xlink:href="metadata/preservation/premis2.xml" MDTYPE="PREMIS:OBJECT" MDTYPEVERSION="3.0" MIMETYPE="text/xml" SIZE="2854" CREATED="2018-04-24T14:37:52.783+01:00" CHECKSUM="d1dfa585dcc9d87268069dc58d5e47956434ec3db4087a75a3885d287f15126f" CHECKSUMTYPE="SHA-256" LABEL="premis2.xml"/>
    </mets:digiprovMD>
</mets:amdSec>
```
