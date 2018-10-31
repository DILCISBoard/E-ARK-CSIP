## 4.1.	Folder structure of the CS IP
The CS IP folder structure is presented in Figure 8 below. The structure follows directly the principles of the conceptual data model by dividing the components of the package into stand-alone folders for representations, metadata, and other components.

<a name="fig8"></a>
![IP Folder Structure](fig_8_cs_ip_struct.png "CS IP Information Package folder structure.")

**Figure 8:** CS IP Information Package folder structure

The implementation requirements of the CS IP Information Package structure are:

<a name="CSIPSTR1"></a>
CSIPSTR1: Each CS IP Information Package MUST be included in a single physical folder (i.e. the “Information Package folder”). In other words: on the highest structural level a Common Specification IP MUST consist of one and only one folder;

<a name="CSIPSTR2"></a>
CSIPSTR2: The Information Package folder SHOULD be named with the ID or name of the Information Package;

<a name="CSIPSTR3"></a>
CSIPSTR3: The Information Package folder CAN be compressed (for example by using TAR or ZIP);

<a name="CSIPSTR4"></a>
CSIPSTR4: The Information Package folder MUST include a metadata file named `METS.xml`, which includes information about the identity and structure of the package and its components ;

<a name="CSIPSTR5"></a>
CSIPSTR5: The Information Package folder MUST include a folder named `metadata`, which MUST include at least all metadata relevant for the whole package

<a name="CSIPSTR6"></a>
CSIPSTR6: If preservation metadata are available, they SHOULD be included in sub-folder `preservation`;

<a name="CSIPSTR7"></a>
CSIPSTR7: If descriptive metadata are available, they SHOULD be included in sub-folder `descriptive`;

<a name="CSIPSTR8"></a>
CSIPSTR8: If any other metadata are available, they CAN be included in separate sub-folders, for example an additional folder named `other`.

<a name="CSIPSTR9"></a>
CSIPSTR9: The Information Package folder MUST include a folder named `representations`;

<a name="CSIPSTR10"></a>
CSIPSTR10: The `representations` folder MUST include a sub-folder for each individual representation (i.e. the “representation folder”) named with a string uniquely identifying the representation within the scope of the package (for example the name of the representation and/or its creation date could be good examples for an representation sub-folder) ;

<a name="CSIPSTR11"></a>
CSIPSTR11: The representation folder MUST include a sub-folder named `data` which includes all data constituting the representation ;

<a name="CSIPSTR12"></a>
CSIPSTR12: The representation folder CAN include a metadata file named `METS.xml` which includes information about the identity and structure of the representation;

<a name="CSIPSTR13"></a>
CSIPSTR13: The representation folder CAN include a sub-folder named `metadata` which CAN include all metadata about the specific representation

<a name="CSIPSTR14"></a>
CSIPSTR14: The Information Package folder and representation folder CAN be extended with additional sub-folders:

<a name="CSIPSTR15"></a>
CSIPSTR15: We recommend including XML Schemas for all metadata in XML format into the package. These schemas SHOULD be placed into the sub-folder called `schemas` within the Information Package folder;

<a name="CSIPSTR16"></a>
CSIPSTR16: We recommend including all additional (binary) documentation about the whole package or a specific representation into the package. Such documentation SHOULD be placed into the sub-folder called `documentation` within the Information Package folder and/or the representation folder;

<a name="CSIPSTR17"></a>
CSIPSTR17: Implementers CAN add any other folders either into the Information Package folder or the representation folder.
