The implementation requirements of the CSIP Information Package structure are:

<a name="CSIPSTR1"></a>
**CSIPSTR1**: Any Information Package **MUST** be included within a single physical root folder (known as the “Information Package root folder”). For packages presented in an archive format, [see CSIPSTR3](#CSIPSTR3), the archive **MUST** unpack to a single root folder.

<a name="CSIPSTR2"></a>
**CSIPSTR2**: The Information Package root folder **SHOULD** be named with the ID or name of the Information Package, that is the value of the package METS.xml's root `<mets>` element's `@OBJID` attribute.

<a name="CSIPSTR3"></a>
**CSIPSTR3**: The Information Package root folder **MAY** be compressed (for example by using TAR or ZIP). Which specific compression format to use needs to be stated in the Submission Agreement.

<a name="CSIPSTR4"></a>
**CSIPSTR4**: The Information Package root folder **MUST** include a file named `METS.xml`. This file **MUST** contain metadata that identifies the package, provides a high-level package description, and describes its structure, including pointers to constituent representations.

<a name="CSIPSTR5"></a>
**CSIPSTR5**: The Information Package root folder **SHOULD** include a folder named `metadata`, which SHOULD include metadata relevant to the whole package.

<a name="CSIPSTR6"></a>
**CSIPSTR6**: If preservation metadata are available, they **SHOULD** be included in sub-folder `preservation`.

<a name="CSIPSTR7"></a>
**CSIPSTR7**: If descriptive metadata are available, they **SHOULD** be included in sub-folder `descriptive`.

<a name="CSIPSTR8"></a>
**CSIPSTR8**: If any other metadata are available, they **MAY** be included in separate sub-folders, for example an additional folder named `other`.

<a name="CSIPSTR9"></a>
**CSIPSTR9**: The Information Package folder **SHOULD** include a folder named `representations`.

<a name="CSIPSTR10"></a>
**CSIPSTR10**: The `representations` folder **SHOULD** include a sub-folder for each individual representation (i.e. the “representation folder”). Each representation folder should have a string name that is unique within the package scope. For example the name of the representation and/or its creation date might be good candidates as a representation sub-folder name.

<a name="CSIPSTR11"></a>
**CSIPSTR11**: The representation folder **SHOULD** include a sub-folder named `data` which **MAY** include all data constituting the representation.

<a name="CSIPSTR12"></a>
**CSIPSTR12**: The representation folder **SHOULD** include a metadata file named `METS.xml` which includes information about the identity and structure of the representation and its components. The recommended best practice is to always have a `METS.xml` in the representation folder.

<a name="CSIPSTR13"></a>
**CSIPSTR13**: The representation folder **SHOULD** include a sub-folder named `metadata` which **MAY** include all metadata about the specific representation.

<a name="CSIPSTR14"></a>
**CSIPSTR14**: The Information Package **MAY** be extended with additional sub-folders.

<a name="CSIPSTR15"></a>
**CSIPSTR15**: We recommend including all XML schema documents for any structured metadata within package. These schema documents **SHOULD** be placed in a sub-folder called `schemas` within the Information Package root folder and/or the representation folder.

<a name="CSIPSTR16"></a>
**CSIPSTR16**: We recommend including any supplementary documentation for the package or a specific representation within the package. Supplementary documentation **SHOULD** be placed in a sub-folder called `documentation` within the Information Package root folder and/or the representation folder.
