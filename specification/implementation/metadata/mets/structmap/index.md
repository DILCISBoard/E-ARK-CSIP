### 5.3.6.	Use of the METS structural map (element structMap)
The METS structural map section is the only element mandatory in the METS specification and it is intended
to provide an overview of components described in the METS document. It can also link the elements of that
structure to associated content files and metadata. In CSIP the structMap describes the higher level structure of all the content in the root and may link to representations.

The CSIP requires the inclusion of one mandatory structural map according to the principles described below.
However, implementers are welcome to define additional structural maps for their internal purposes by
repeating the structMap element.
The most crucial requirements for the CSIP mandated structural map are as follows:

- The structMap element has a mandatory attribute @LABEL which has the fixed value of “CSIP structMap”. The @LABEL attribute is used to distinguish the Common Specification mandated structural map occurrence from any other, user-defined, structural maps. As such we can also derive the requirement, that any user-defined structural maps must not use the @LABEL value of “CSIP structMap”;
- The structure is expressed with division elements (div). The div element can be nested in a tree structure and the implementation used in CSIP gathers the div elements described below in one main structural division element. 
  - The main structural division use the attribute LABEL with the value being the string value of the content identification element of the package
- The internal structure of the structural map (expressed by div elements) follows the CSIP high level physical structure as described in Section 4, therefore grouping together metadata, representations, schemas, documentation and user-defined folders into their own div elements;
  - All div elements must use the attribute LABEL with the value being the name of the folder (as an example “metadata”)
- In  case both root and representation METS files exist, the structural map in the root METS file
  - Reference the fileGrp which describes all files in all folders with the exception of the content of the representation folders
  - Lists all representations (as separate div elements)
  - Lists only the appropriate representation METS file using the mptr element as the content of the representation
- The structural map in the representations METS file use the structural map the same way with no exceptions

The specific requirements for elements, sub-elements and attributes are listed in the following table. Note that the area, seq and par elements are not discussed below.
