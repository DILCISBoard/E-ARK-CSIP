- [Handling large packages](#handling-large-packages)
	    - [The structure for IP, their representations and their segments](#the-structure-for-ip-their-representations-and-their-segments)
	    - [Using METS to refer from parent IP to child IP](#using-mets-to-refer-from-parent-ip-to-child-ips)
	    - [Using METS to refer from child IP to parent IP](#using-mets-to-refer-from-child-ip-to-parent-ip)
	    - [An example for the Northwind database](#an-example-for-the-northwind-database)
	    - [Illustration of references between METS files in a segmented IP](#illustration-of-references-between-mets-files-in-a-segmented-ip)
	  -
## Handling large packages
By default a Common Specification IP should be contained in a single folder or archive file. However, the amount of data and metadata within a single IP can easily grow to several GB or even TB and become difficult to manage and inefficient to process e.g. due to insufficient storage capacity.

The Common Specification can, in principle, be extended in different ways to support the segmentation of large packages into more manageable parts. This Section describes such an extension which exploits the Common Specification “representation METS” concept and extends it to cover physical segmentation.

Please be aware that this is a “recommended approach” and, at this point in time, is not a part of the Common Specification. It is also not expected that all tools will support such a mechanism.

### The structure for IP, their representations and their segments
E-ARK Common Specification Information packages may comprise multiple representations of the same intellectual content. The segmentation approach described here is based on the following considerations:

- The majority of the size of an IP consists of the content (data) which, according to the Common Specification, resides in the representations folder of the IP. As such any segmentation should take place within the representations layer of the Common Specification;
- According to the Common Specification each representation is essentially a Common Specification IP itself, consisting of a METS metadata file, data, metadata, and any additional components;
- A segment of an IP must also adhere to the Common Specification format, i.e. it shall be possible to validate each individual segment as a Common Specification IP;
- Each IP shall consist of a parent segment (including at least the package METS file) and any number of child segments;
- It shall be possible to add new physical child segments (as an example a new representation) to the whole IP without necessitating the update of other child segments.

### Using METS to refer from parent IP to child IP(s)
The method used to refer from parent to child is based on the ID of the IP of the child. One reason for using ID and not URL or other more direct references to a location of the referenced METS file is the flexibility it gives to move the segmented IPs across storage locations. This flexibility is often needed for segmented IPs that can be very large.

The value of the xlink:href attribute in the <mptr> element in the METS file of the parent IP is used.

This vis to be set to the value of the OBJID attribute of the <mets> element in the METS file of the child IP. According to the Common Specification, the OBJID attribute must have the value of the ID of the IP. This is therefore sufficient for having the parent know the ID of the child, but the parent does not know the exact child location.

### Using METS to refer from child IP to parent IP
The optional reference from child to the parent is based on the ID of the IP of the parent.

The value of the xlink:href attribute in <mptr> element in the METS file of the child IP is used.

This value is to be set to the value of the OBJID attribute of the <mets> element in the METS file of the parent IP. According to the Common Specification, the OBJID attribute must have the value of the ID of the
IP.

This is therefore sufficient for having the child know the ID of the parent, but the child does not know the exact parent location.

### An example for the Northwind database
Here follows a partial example, where the value of the xlink:href attribute in the `<mptr>` element (inside the `<div>` element inside the `<structMap>` element) is `ID.AVID.RA.18005.rep0.seg0` after the urn NID part (`urn:<NID>:<NSS>`).

The value `ID.AVID.RA.18005.rep0.seg0` must now match the value of the OBJID attribute for the `<mets>` element in the child IP package METS file.
(Note that in order to save space in this example the CS mandatory ID attribute for the `<div>` elements have been left out.)
Parent METS file

```xml
<!-- this top package level METS.xml IP only refers to the package level METS files in the representations using the <mptr> element -->
<div LABEL="representations">
<!-- the value of the attribute LABEL is the ID of the representation -->
   <div LABEL="representations/ID.AVID.RA.18005.rep0" ORDER="0" >
<!-- we use the attribute LABEL value 'child IP' in the 'div' element for representations in accordance with the AIP spec.3.3.1.9 -->
      <div LABEL="child IP" TYPE="representation child">
<!-- each package level METS file in the representations refer to its own METS files in the segments and in the representations folder using
the <mptr> element -->
<!-- this is a METS reference to another METS file, and this file is in another segment -->
        <mptr xlink:href="urn:sa.dk:ID.AVID.RA.18005.rep0.seg0" xlink:title="package level METS file for representation 0" xlink:type="simple"
LOCTYPE="URN"/>
      </div>
   </div>
<!-- the value of the attribute LABEL is the ID of the representation -->
   <div LABEL="representations/ID.AVID.RA.18005.rep1" ORDER="1">
      <div LABEL="child IP" TYPE="representation child">
<!-- this is an indirect METS reference to another METS file, and this file is in another segment -->
         <mptr xlink:href="urn:sa.dk:ID.AVID.RA.18005.rep1.seg0" xlink:title="package level METS file for representation 1" xlink:type="simple"
LOCTYPE="URN"/>
      </div>
   </div>
</div>
```

Child METS file

```xml
<mets xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://www.loc.gov/METS/"
xmlns:xlink="http://www.w3.org/1999/xlink"
xsi:schemaLocation="http://www.loc.gov/METS/ schemas/mets.xsd"
PROFILE="http://www.ra.ee/METS/v01/IP.xml" TYPE="Database segment child" OBJID="ID.AVID.RA.18005.rep0.seg0" LABEL="package
level METS file for a representation segment">
..
..
..
   <div LABEL="parent IP" TYPE="Godfather IP"> <!-- working title - maybe master IP is more appropriate -->
<!-- this is an indirect METS reference to another METS file. However, the referenced file is in another segment -->
      <mptr xlink:href="urn:sa.dk:ID.AVID.RA.18005.godfather" xlink:title="package level METS file for godfather IP" xlink:type="simple"
LOCTYPE="URN"/>
   </div>
```

### Illustration of references between METS files in a segmented IP
We need to segment an IP at the data folder in the representations level, but according to the Common Specification this can only be done at the IP level. Therefore this IP has been segmented at the top IP level, and not at the representations level.

![CSIP Example](figs/mets_file_ref.svg "Illustration of references between files.")

Please note the following about the example:

- The Master IP MUST NOT contain representations
- A representation MAY be segmented
- The IDs are not just unique but have implicit value for example purposes only
- In representation 0 the limits on folder size and amount of files requires three segments (0, 1 and 2)
- In representation 1 these limits have been increased and we only need two segments. Further the .bin files have been migrated to .tif.
