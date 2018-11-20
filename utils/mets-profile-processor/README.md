E-ARK CSIP METS Profile Processor
=================================

Java SAX based XML processor for the CSIP METS Profile. Performs the following tasks:
- [x] schema aware parsing and validation of the METS profile;
- [ ] generation of metadata requirements tables from Profile requirements;
- [ ] checks for duplicate requirement ID allocation; and
- [ ] reports gaps in the ID sequence (in preparation for deprecation reporting).

Quick Start
-----------

### Build from source
This is a [Java](https://www.java.com/) project and is built using [Maven](https://maven.apache.org/). You'll need a copy of this project sub-directory, from a git clone, `git clone https://github.com/DILCISBoard/E-ARK-CSIP.git` or a [source package download](https://github.com/DILCISBoard/E-ARK-CSIP/archive/master.zip).

From within this project sub-directory, e.g. `mets-profile-processor` issue the Maven command: `mvn clean package` to run tests and build.

### Class overview
It's just a basic SAX processor for the profile with some Markdown output.

#### `eu.dilcis.csip.MetsProfileProcessor`
Main entry point for fat JAR package, sequences parsing user input and running
the SAX handler.

#### `eu.dilcis.csip.ProcessorOptions`
Parses the `String` args array and records the user options in a dedicated
class.

#### `eu.dilcis.csip.profile.MetsProfileXmlHandler`
SAX event driven handler for METS Profile, parses `Requirements` lists from
Profile XML document.

#### `eu.dilcis.csip.OutputHandler`
Buffers XML element text and handles output (for now.....)

ToDo ?
------
- [ ] Stronger data typing for [`eu.dilcis.csip.profile.MetsProfileXmlHandler.Requirement`](./src/main/java/eu/dilcis/csip/profile/MetsProfileXmlHandler.java)
- [ ] Requirement validation, e.g. non-empty fields etc.
- [ ] Group think for other validation activities.
- [ ] Markdown table generation
- [ ] `index.md` file template selection
- [ ] `index.md` file template substitution
- [ ] Generalise vanilla METS Profile handling to base class
- [ ] fix SaxExceptions from OutputHandler class
