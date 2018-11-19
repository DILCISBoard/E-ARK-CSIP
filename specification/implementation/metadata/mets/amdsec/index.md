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

{% comment %} PAGE TEMPLATING : DO NOT DELETE THIS OR THE FOLLOWING LINE {% endcomment %}
{% include_relative examples.md %}
