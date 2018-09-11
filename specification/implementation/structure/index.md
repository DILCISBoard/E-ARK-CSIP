# 4.	CS IP structure
The preferred implementation of the conceptual model described in Requirement 3.6 is a fixed physical (folder) structure which follows exactly the conceptual structure. While the CS IP doesn't prohibited alternative implementations of the conceptual model such implementations aren't recommended.

The main reason for such an implementation decision is that a fixed physical folder structure makes it clear for both human users and tools where to find what. The main benefit of such a clear decision is that many archival tasks (for example file format risk analysis) can be executed directly on the data portion of the package structure, as opposed to first processing potentially large amounts of metadata for the locations of the files. This, in turn, allows for more efficient processing which is valuable in the case of large collections and bulk operations. In short, we believe that a fixed folder structure allows for more efficiency and scalability.

The authors of this specification are well aware that there are multiple data storage solutions which do not support explicit folder structures but use other means for structuring and storing (the content of) AIPs.
However, we would like to note that the purpose of this specification is to support Information Package interoperability. As such we believe that even if a storage solution does not allow implementing the physical folder structure as the native AIP storage structure, it is still possible to implement the physical structure described below for SIPs, DIPs and the import/export of AIPs. While the repository needs to support an extra transformation (i.e. Common Specification IP to internal AIP and vice versa), it allows still
to use the tools created by other users of the common specification, transfer AIPs more easily to new repository systems or storage solutions, and establish cross-repository duplicated storage solutions.

## 4.1.	Folder structure of the CS IP
The CS IP folder structure is presented in Figure 8 below. The structure follows directly the principles of the conceptual data model by dividing the components of the package into stand-alone folders for representations, metadata, and other components.

<a name="fig8"></a>
![IP Folder Structure](fig_8_cs_ip_struct.png "CS IP Information Package folder structure.")

**Figure 8:** CS IP Information Package folder structure

The implementation requirements of the CS IP Information Package structure are:

**CSIP1:** Each CS IP Information Package MUST be included in a single physical folder (i.e. the “Information Package folder”). In other words: on the highest structural level a Common Specification IP MUST consist of one and only one folder;

**CSIP2:** The Information Package folder SHOULD be named with the ID or name of the Information Package;

**CSIP3:** The Information Package folder CAN be compressed (for example by using TAR or ZIP);

**CSIP4:** The Information Package folder MUST include a metadata file named `METS.xml`, which includes information about the identity and structure of the package and its components ;

**CSIP5:** The Information Package folder MUST include a folder named `metadata`, which MUST include at least all metadata relevant for the whole package

**CSIP6:** If preservation metadata are available, they SHOULD be included in sub-folder `preservation`;

**CSIP7:** If descriptive metadata are available, they SHOULD be included in sub-folder `descriptive`;

**CSIP8:** If any other metadata are available, they CAN be included in separate sub-folders, for example an additional folder named `other`.

**CSIP9:** The Information Package folder MUST include a folder named `representations`;

**CSIP10:** The `representations` folder MUST include a sub-folder for each individual representation (i.e. the “representation folder”) named with a string uniquely identifying the representation within the scope of the package (for example the name of the representation and/or its creation date could be good examples for an representation sub-folder) ;

**CSIP11:** The representation folder MUST include a sub-folder named `data` which includes all data constituting the representation ;

**CSIP12:** The representation folder CAN include a metadata file named `METS.xml` which includes information about the identity and structure of the representation;

**CSIP13:** The representation folder CAN include a sub-folder named `metadata` which CAN include all metadata about the specific representation

**CSIP14:** The Information Package folder and representation folder CAN be extended with additional sub-folders:

**CSIP15:** We recommend including XML Schemas for all metadata in XML format into the package. These schemas SHOULD be placed into the sub-folder called `schemas` within the Information Package folder;

**CSIP16:** We recommend including all additional (binary) documentation about the whole package or a specific representation into the package. Such documentation SHOULD be placed into the sub-folder called `documentation` within the Information Package folder and/or the representation folder;

**CSIP17:** Implementers CAN add any other folders either into the Information Package folder or the representation folder.

## 4.2.	Implementing the structure
The requirements presented in Section 4.1 leave room for quite a few decisions during implementation. For the sake of clarity we provide here examples for two extremes – the simplest and the full use of the structure.

In the simplest case the structure can be implemented following mostly just the MUST requirements. An example of this is visible on Figure 9.

<a name="fig9"></a>
![CS IP Example](fig_9_cs_ip_simple.png "Example of a simple use of the CS IP structure.")

**Figure 9:** Example of a simple use of the CS IP structure

The main point to highlight with such a simple use is that the representations have been kept as simple as possible. All metadata about both the package and the representations (in this example METS, EAD and
PREMIS metadata) are located in the Information Package folder and none of these components are available within the representation folders.

Such a simple implementation is reasonable in scenarios where the amount of data and metadata is limited. However, in the case of large Information Packages (for example, a package including three representations and 1,000,000 files in one representation) the size of both the `METS.xml` file and preservation metadata can grow too large to manage efficiently. Especially in such large data scenarios it might prove necessary to implement all the capabilities of the structure presented in the previous Section.

An example of the full implementation is delivered in Figure 10. The main difference between the simple and full use of the structure is that each representation does essentially repeat the simple structure. Especially structural and preservation metadata in METS and PREMIS formats is available in both the Information Package folder (for package level descriptions) and within representation folders (for representation level descriptions). As such the full structure allows for easier management of single representations and brings further benefits like more straight-forward metadata versioning. It is worth to note that, in order to avoid confusion, it is recommended to have a common approach towards adding metadata into representations or not. In other words, we recommend having all representation-relevant metadata either in the root metadata folder or the representation metadata folder, but not to have a mixed approach (i.e. some representation metadata in the root metadata folder and some within the representation). Further, we do not recommend the duplication of any metadata or the content of optional folders (schemas, documentation, etc.) between the Information Package folder and representation folders.

<a name="fig10"></a>
![CS IP Example](fig_10_cs_ip_full.png "Example of the full use of the CS IP structure.")

**Figure 10:** Example of the full use of the CS IP structure
 
