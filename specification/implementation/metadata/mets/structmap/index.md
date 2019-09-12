### 	Use of the METS structural map (`<structMap>`)
The METS structural map element is the only mandatory element in the METS specification. It provides an overview of the components described in the METS document. It can also link the elements in the structure to associated content files and metadata. In the CSIP the `<structMap>` describes the higher level structure of all the content in the package and may link to representations.

The CSIP requires the inclusion of one mandatory structural map according to the principles described below.
However, implementers are welcome to define additional structural maps for their internal purposes by repeating the `<structMap>` element.
The most crucial requirements for the CSIP mandated structural map are as follows:

- The `<structMap>` element's `@LABEL` attribute is mandatory and must have the value “CSIP”. The `@LABEL` attribute distinguishes the CSIP mandated structural maps from other structural maps. NOTE this means that the "CSIP" `@LABEL` value should be treated as a unique id and not applied to other `<structMap>` elements;
- The package structure is documented using METS division `<div>` elements which can be arranged in nested tree structures. The CSIP organises it's constituent `<div>` elements in a single root structural `<div>` element.
  - This top level structural `<div>` element must use the package identifier as the value of its `@LABEL` attribute.
- The internal structure of the structural map (expressed by `<div>` elements) follows the CSIP high level physical structure described in Section 4, and groups metadata, representations, schemas, documentation and user-defined folders into discrete `<div>` elements;
  - All `<div>` elements must have an `@LABEL` attribute with the value identical to the folder name, e.g. “metadata”;
- When both package and representation METS files are present, the structural map in the Package METS document:
  - References the `<fileGrp>` which describes every file in every folder, with the exception of the content of the representation folders
  - Lists all representations (as separate `<div>` elements)
  - Lists only the appropriate representation METS file using the `<mptr>` element as the content of the representation
- The structural map in the representations METS file uses the structural map in an identical manner, describing all representation structural map with no exceptions

The specific requirements for elements, sub-elements and attributes are listed in the following table. Note that the `<area>`, `<seq>` and `<par>` elements are not discussed below.
