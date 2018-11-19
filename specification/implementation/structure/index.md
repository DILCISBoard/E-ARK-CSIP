---
title: CSIP structure
---
# 4.	CSIP structure
The preferred implementation of the conceptual model described in Principle 3.6 http://earkcsip.dilcis.eu/specification/specification/principles/#principle-36 is a fixed physical (folder) structure which follows exactly the conceptual structure. While the CS IP doesn't prohibited alternative implementations of the conceptual model such implementations aren't recommended.

The main reason for such an implementation decision is that a fixed physical folder structure makes it clear for both human users and tools where to find what. The main benefit of such a clear decision is that many archival tasks (for example file format risk analysis) can be executed directly on the data portion of the package structure, as opposed to first processing potentially large amounts of metadata for the locations of the files. This, in turn, allows for more efficient processing which is valuable in the case of large collections and bulk operations. In short, we believe that a fixed folder structure allows for more efficiency and scalability.

The authors of this specification are well aware that there are multiple data storage solutions which do not support explicit folder structures but use other means for structuring and storing (the content of) AIPs.
However, we would like to note that the purpose of this specification is to support Information Package interoperability. As such we believe that even if a storage solution does not allow implementing the physical folder structure as the native AIP storage structure, it is still possible to implement the physical structure described below for SIPs, DIPs and the import/export of AIPs. While the repository needs to support an extra transformation (i.e. Common Specification IP to internal AIP and vice versa), it still allows the use of tools created by other users of the common specification, easy transfer of AIPs to a new repository systems or storage solutions and to establish cross-repository duplicated storage solutions.

## Contents

- [4.	CS IP structure](#)
  - [4.1 Folder structure of the CS IP](folders/)
  - [4.2 Implementing the structure](implementation/)
