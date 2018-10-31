E-ARK CSIP METS Profile Processor
=================================

Java SAX based XML processor for the CSIP METS Profile. Performs the following tasks:
- [ ] schema aware parsing and validation of the METS profile;
- [ ] generation of metadata requirements tables from Profile requirements;
- [ ] checks for duplicate requirement ID allocation; and
- [ ] reports gaps in the ID sequence (in preparation for deprecation reporting).

Quick Start
-----------

### Build from source
This is a [Java](https://www.java.com/) project and is built using [Maven](https://maven.apache.org/). You'll need a copy of this project sub-directory, from a git clone, `git clone https://github.com/DILCISBoard/E-ARK-CSIP.git` or a [source package download](https://github.com/DILCISBoard/E-ARK-CSIP/archive/master.zip).

From within this project sub-directory, e.g. `mets-profile-processor` issue the Maven command: `mvn clean package` to run tests and build.
