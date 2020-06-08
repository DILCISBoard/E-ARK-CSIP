# Principles for interoperable Information Packages
At the heart of any standardisation activity has to be a clear understanding of the needs and aims which have to be addressed. This is also the goal of this section, which presents a series of high-level principles to guide the technical details delivered in Part II of this specification.

Most of the principles are driven by the aim of interoperability, which is that Information Packages shall be easy to exchange, identify, validate and (re)use with a wide variety of software tools and systems.

Another crucial factor to take into account is long-term sustainability. Practical technical and semantic interoperability is possible only when a certain set of technologies have been agreed upon and implemented. However, any technology will become outdated sooner or later, and previously agreed-upon approaches have to be updated to accommodate new, better and more efficient technologies and standards. Because of this, the developers of this Common Specification for Information Packages have reused, as much as possible, existing powerful, standardised and well-established best practices for the technical implementation of an Information Package (Part II of this document). This does not mean that the technical implementation details will not need to be changed in future, only that the need will arise later rather than sooner. To achieve long-term sustainability of the Common Specification for Information Packages, we present below a set of generic principles which must be followed when updating any specific implementation details at any point in time.

The principles present a conceptual view of an Information Package, including an overall IP data model, and use of data and metadata. An implementation of this conceptual view is presented later, in Part II of this document.

Each principle has a sequential number and a short description. The description always includes a MoSCoW (MUST/MUST NOT, SHOULD/SHOULD NOT, COULD, WOULD) prioritisation statement. The short description of each principle is followed by a rationale which describes the reason and background for the principle.

## General principles

### Principle 1.1
*It **MUST** be possible to include any data or metadata in an Information Package regardless of its type or format.*

This is one of the most crucial principles of the CSIP. To be truly “common”, technical implementations of the CSIP MUST NOT introduce limitations or restrictions which are only applicable to certain data or metadata types. If an Information Package implementation fails to meet this principle, it is not possible to use it across different sectors and tools, thereby limiting practical interoperability.

### Principle 1.2:
*The Information Package **MUST NOT** restrict the means, methods or tools for exchanging it.*

Tools and methods for transferring Information Packages between locations are constantly evolving. It is also possible that different methods are preferred for packages of varying sizes. To ensure that a CSIP Information Package is truly interoperable across different platforms, it MUST NOT introduce limitations or restrictions which would be impossible to be met by specific information exchange tools or channels.

As such, the CSIP does also not define the principle to use a particular transfer package or envelope. The scope of the CSIP is limited to the structure and requirements for data and metadata within the package. Different implementers are welcome to choose their own methods on top of the CSIP.

### Principle 1.3
*The package format **MUST NOT** define the scope of data and metadata which constitutes an Information Package.*

One of the fundamental principles of the CSIP is that it MUST allow each individual repository to define the (intellectual) scope of an Information Package and its relations to real-life entities. As such, any implementation of the CSIP MUST be equally usable for packaging, for example, the entire content of an ERMS as a single IP; or for extracting each record and its metadata from the ERMS individually and packaging each as a separate IP.

Out of the previous, we can also derive that a CSIP specification MUST NOT define that, for example, a SIP should conform to exactly one AIP. Instead, the CSIP MUST allow for the inclusion of “anything that the implementer wants to define as a SIP, AIP or DIP” and allow for “any relationships (1-1; 1-n; n-1; n-m) between SIPs, AIPs and DIPs".

### Principle 1.4:
*The Information Package **SHOULD** be scalable.*

One of the practical concerns for Information Packages is their size. Many digital repositories have problems with data objects and metadata of increasing sizes, making it especially difficult to carry out tasks related to data or metadata validation, and identification and modification. For example, Information Packages that include relational databases or born-digital 3D movies can easily reach TB sizes.

Consequently, any current or future implementation of the CSIP is required to provide for appropriate scalability mechanisms (for example, mechanisms for splitting large-scale data or metadata).

### Principle 1.5:
*The Information Package **MUST** be machine-readable*

To support the goal of automating ingest, preservation and access workflows each of the implementations of the CSIP must be machine-actionable. This means that decisions about the use of metadata syntax and semantics as well as the physical structure must be expressed explicitly and in a clear way. This, in turn, allows the specification to be implemented in the same way across different tools and environments.

### Principle 1.6:
*The Information Package **SHOULD** be human-readable*

In long-term preservation, it is necessary to take into account that “forgotten” Information Packages might be found long after details about the implementation are gone and no tools to access the package are available. For these scenarios, it is crucial to ensure that the structure and metadata of the Information Package are understandable with minimal effort by using simple tools like text editors and file viewers.

In practice, this means that any implementation of the CSIP should ensure that folder and file naming conventions allow for the human identification of package components and that the semantics of the package is explicit.

### Principle 1.7:
*The Information Package **MUST NOT** prescribe the use of a specific preservation method*

Different preservation institutions and different types of data need to use different methods for long-term preservation; migration and emulation are typical examples. A CSIP implementation MUST NOT prescribe the use of a specific preservation method. Instead, it should allow users to document and/or add arbitrary data or metadata as necessary for preservation purposes.

## Identification of the Information Package

### Principle 2.1:
*The Information Package OAIS type (SIP, AIP or DIP) **MUST** be clearly indicated.*

One of the first tasks in analysing any Information Package is to identify its current status in the overall archival process. Therefore, any Information Package must explicitly and uniformly identify itself as a SIP, AIP or DIP.

### Principle 2.2:
*Any Information Package **MUST** clearly identify the Content Information Type(s) of its data and metadata.*

As stated in Principle 1.1, any Information Package MUST be able to include any kind of data and metadata. At the same time, we have introduced in earlier Sections the concept of Content Information Types which allow users to achieve more detailed control and fine-grained interoperability. As such, any CSIP Information Package MUST include a statement about which Content Information Type Specification(s) has been followed within the Information Package, or on the contrary, indicate clearly that no specific Content Information Type Specification has been followed.

The practical implication of principles 1.1, 2.1 and 2.2 is that, once these have been followed in implementations, it is possible to develop modular identification and validation tools and workflows. While generic components can carry out high-level tasks regardless of the Content Information Type, it is possible to detect automatically which additional content-aware modules need to be executed.

### Principle 2.3:
*Any Information Package **MUST** have an identifier which is unique and persistent within the repository.*

In order to manage a digital repository and provide access services, each Information Package stored in the repository MUST be identified uniquely at least within the repository. At the same time, a CSIP implementation MUST NOT limit the choice of the exact identification mechanism, as long as the mechanism is implemented consistently throughout the repository.

### Principle 2.4:
*Any Information Package **SHOULD** have an identifier which is globally unique and persistent.*

In addition to the previous principle, it is recommended that the identification mechanism used at the repository provides for global uniqueness and persistence of Information Package IDs. The application of globally unique and persistent identifiers allows repositories to participate more easily in cross-institutional information exchange and reuse scenarios (for example participation in national or international portals, or cross-repository duplication of AIP preservation). However, the CSIP MUST NOT limit the choice of the exact identification mechanism.

### Principle 2.5:
*All components of an Information Package **MUST** have an identifier which is unique and persistent within the repository.*

As stated above, an Information Package MUST be flexible enough to allow for the inclusion of any data or metadata depending on the needs of the repository and its users. As well, an Information Package might include additional support documentation like metadata schemas, user guidelines, contextual documentation etc. Regardless of which and how many components constitute a full Information Package, all components MUST have a unique and persistent identifier which allows for the appropriate linking of data, metadata and all other components. This, in turn, is one of the most crucial aspects for achieving an interoperable way of maintaining package integrity.

It is also worth mentioning that in any implementation, it is only necessary to achieve identifier uniqueness and persistence within an individual Information Package. If this is the case, repository-wide uniqueness is easily achieved when combining the package ID (unique according to principle 2.3) and the component ID.

The components of an Information Package are explained in more detail in the following section.

## Structure of the Information Package

### Principle 3.1:
*The Information Package **MUST** ensure that data and metadata are logically separated from one another.*

At the highest level, each Information Package can be logically subdivided into data and metadata. This logical separation minimises the effort required to identify or validate content/metadata and simplifies long-term preservation actions. For example, ingest tools may implement separate, efficient metadata identification and validation tasks as opposed to content format identification and normalisation tasks. Over the lifetime of a package, this separation also facilitates long-term preservation tasks, such as partial metadata or data updates that do not put the integrity of the package at risk. Regardless of the physical structure of a package, the Information Package **MUST** provide logical separation of data and metadata in the package's manifest.

### Principle 3.2:
*The Information Package **SHOULD** ensure that data and metadata are physically separated from one another.*

In addition to the logical separation of components, it is beneficial to have data and metadata physically separated (i.e. formatted as individual computer files or clearly separated bitstreams). This allows digital preservation tools and systems to update respective data or metadata portions of an Information Package without endangering the integrity of the whole package.

### Principle 3.3:
*The structure of the Information Package **SHOULD** allow for the separation of different types of metadata*

In addition to the previous principle, it is recommended to explicitly divide metadata into more specific components. While the definitions of metadata types vary a lot between implementations, it is recommended that metadata is divided both logically and physically at least into descriptive and preservation metadata.

### Principle 3.4:
*The structure of the Information Package **MUST** allow for the creation of data and metadata in multiple representations.*

The concept of representations is one of the fundamental building blocks in digital preservation. As technologies evolve and become obsolete, data and metadata are constantly updated to ensure long-term accessibility, therefore creating new versions or representations of the data and metadata.

Expressing representations within the logical and physical structure of an Information Package helps institutions to explicitly understand the various states of the information throughout its lifecycle, therefore improving also the ease of long-term management and reuse of the information.

### Principle 3.5:
*The structure of the Information Package **MUST** explicitly define the possibilities for adding additional components into the Information Package.*

In addition to data and metadata, institutions might need to include additional components in an Information Package. For example, implementers might decide that XML Schemas about metadata structures and additional binary documentation about the original IT environment have to be added to the package.

If this is the case, the CSIP Information Package MUST NOT limit which components can constitute an Information Package and MUST offer clearly defined extension points for the inclusion of these additional components into the Information Package. At the same time, these extension points MUST be defined in a way which does not interfere with other components (i.e. the extension points MUST be clearly separated from other components of an Information Package).

### Principle 3.6:
*The Information Package **SHOULD** follow a common conceptual structure regardless of its technical implementation.*

Based on principles 3.1 – 3.5, a common structure for any CSIP Information Package is presented ([Figure 7](#fig7)).

<a name="fig7"></a>
![Conceptual Structure](figs/fig_7_cs_con_struct.svg "Conceptual structure of the Common Specification")

**Figure 7:** Conceptual structure of the Common Specification

Following Principle 3.1 the package MUST include a high-level structural component for metadata which includes at least relevant metadata for the whole package. In addition, the representations MUST internally separate between data and metadata (though note that the CSIP does not mandate that both data and metadata must be available in all representations).

Following Principle 3.2, it is strongly recommended that this logical structure is manifested as a physical folder structure.

Following Principle 3.3, it is strongly recommended that any package metadata is subdivided into separate metadata sub-types.

Following Principle 3.4, the structure separates the representations of data and metadata explicitly into a separate structural component.

Following Principle 3.5, repositories and their users have the possibility to add any additional components (as an example for schemas and binary support documentation) either as extensions to the whole Information Package or into a specific representation.

This common structure **SHOULD** be followed throughout all implementations of the CSIP.


The conceptual structure presented above can be implemented in various ways – for example, the components might be defined by accompanying metadata or explicitly through a physical structure. However, it is not reasonable to have multiple implementations available at once as this would lead to unnecessary complexity in developing interoperable tools for creating, processing and managing Information Packages. At the time being for CSIP the highly recommended implementation is to use a fixed physical folder structure in combination with a manifest in the form of a METS-document (see Section 4 and Section 5) as the implementation of this specification.

At the same time, it is clear that any given technical implementation will become obsolete in time, for example, as new transfer methods and storage solutions emerge. As such this specification does not prohibit the take-up of any emerging logical or physical technical solutions.

## Information Package Metadata

### Principle 4.1:
*Metadata in the Information Package **MUST** conform to a standard.*

To exchange, validate, process and reuse Information Packages in an interoperable and automated way, it is necessary to standardise how crucial metadata are presented in the package. “Crucial metadata” is defined in this specification as the core information about how the package content has been created and managed (administrative and preservation metadata), explicit descriptions about of the structure of the package (structural metadata) and the technical details of the data themselves (technical metadata).

To ensure that these metadata are understood and implemented in a common and interoperable way in any Information Package, the use of established and widely used metadata standards is highly recommended. In the current implementation, a large proportion of such metadata is covered by the widely used METS and PREMIS standards (see Section 5).

### Principle 4.2:
*Metadata in the Information Package **MUST** allow for unambiguous use.*

Many metadata standards support multiple options for describing specific details of an Information Package. However, such interpretation possibilities can also lead to different implementations and ultimately to the loss of interoperability.

To overcome this risk, the CSIP requires that, while developing a specific implementation, the chosen metadata standard MUST be reviewed regarding potential ambiguity. If needed, the selected metadata standard MUST be further refined to meet the needs of interoperability and automation.

### Principle 4.3:
*The Information Package **MUST NOT** restrict the addition of supplementary metadata.*

Previous principles state the importance of controlled metadata for interoperability purposes. At the same time, the opposite applies for other types of metadata, most prominently for resource discovery (also called descriptive) or Content Information Type specific technical and structural metadata. So as not to limit the widespread adoption of the CSIP, it has to be possible for any implementer to add any metadata next to the mandatory metadata components needed for package-level automation and interoperability.

In case organisations need to prescribe further details about descriptive or Content Information Type specific metadata for a deeper level of interoperability, it is possible to use the mechanism of Content Information Type Specifications described above.

To summarise the requirements above from a more technical perspective, the CSIP foresees a modular approach towards Information Package metadata:

- All Information Packages share a common core of metadata which allows for the common development of high-level package creation, validation, identification and reuse tools;

- The rest of the metadata in the Information Package might follow additional agreements which have been made to develop specific tools such as, for example, tools to manage archival descriptions in EAD, or for specific Content Information Types like relational databases in the SIARD2 format.
