---
title: Use of the METS structural map
---
### 5.3.6.	Use of the METS structural map (element structMap)
The METS structural map section is the only element mandatory in the METS specification and it is intended
to provide an overview of ALL components of a CS IP Information Package. It also links the elements of that
structure to associated content files and metadata. It is a mandatory and ultimate means to define the full
structure of the package – including metadata, representations, schemas, documentation and user added
components and folders. In other words, tools compatible with the CS IP will count on the information
available within the structMap element as the primary means of identifying all components of the package.
As such it is the most crucial component for the validation of any CS IP Information Package and must
always be present.
The CS IP requires the inclusion of one structural map according to the principles described below.
However, implementers are welcome to define additional structural maps for their internal purposes by
repeating the structMap element.
The most crucial requirements for the CS IP mandated structural map are as follows:

- The structMap element has a mandatory attribute @LABEL which has the fixed value of “CS IP StructMap”. The @LABEL attribute is used to distinguish the Common
Specification mandated structural map occurrence from any other, user-defined, structural maps.
As such we can also derive the requirement, that any user-defined structural maps must not use
the LABEL value of “CS IP StructMap”;
- The internal structure of the structural map (expressed by hierarchical div elements) follows the CS
IP physical structure as described in Section 4, therefore grouping together metadata,
representations, schemas, documentation and user-defined folders;
  - All div elements must use the attribute LABEL with the value being the name of the folder
(as an example “metadata”)
- In  case both root and representation METS files exist, the structural map in the root METS file
  - Lists all files in all folders with the exception of the content of the representation folders
  - Lists all representations (as separate div elements)
  - Lists only the appropriate representation METS file using the mptr element as the content of the representation
- The structural map in a representation METS file lists all files within the representation with no exceptions

The specific requirements for elements, sub-elements and attributes are listed in the following table. Note that the area, seq and par elements are not discussed below.

{% comment %} PAGE TEMPLATING : DO NOT DELETE THIS OR THE FOLLOWING LINE {% endcomment %}
{% include_relative requirements.md %}

Full example of the Common Specification structMap element (root METS file):

{% comment %} PAGE TEMPLATING : DO NOT DELETE THIS OR THE FOLLOWING LINE {% endcomment %}
{% include_relative examples.md %}
