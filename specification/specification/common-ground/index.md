# Need for establishing common ground

**The vision:** *All digital preservation systems receive, store and provide access to information, regardless of its size, type or format, according to a set of agreed principles which allow institutions to identify, verify and validate the information in a uniform way.*

**The goal:** *Interoperability between data sources, archives and reuse environments is improved to a point where digital preservation tools can be reused across borders and institutions. This opens up new possibilities for collaboration and reduces the need for development resources for any single institution.*

The amount of digital information being created, held and exchanged is continuously growing. This information is created with the help of numerous software tools and systems, comes in a variety of technical formats, and covers most aspects of our daily lives. Regardless of the formats and systems in question, it is necessary to consider whether the information needs to be retained and managed for longer periods of time. The reasons for this might be, for example:

- to meet legal and regulatory obligations
- to provide for efficient reuse
- to satisfy historical, cultural, scientific and business interest.

Currently, most tools and systems used to create information are not built for coping with long-term
requirements for keeping information safe and accessible. Instead, implementations separate the short-term and long-term management of information into different systems, for example, business and records
systems on one hand and archival systems on the other ([Figure 5](#fig5)).

<a name="fig5"></a>
![OAIS Entities](figs/fig_5_oais_ent.svg "Information flow between live and archival systems")

**Figure 5:** Information flow between live and archival systems

The implication for data owners and system managers is that information which has to be kept for
extended time periods need to be exchanged between a set of different locations, including archival
systems:

- as effectively as possible,
- without endangering the authenticity and integrity of the information,
- and without limiting the possibilities for discovering and reusing the information.

As such, what is needed to make the long-term availability of crucial information possible under
(usually limited) resources is a set of principles which allow exchanging information in a common way
across the systems participating in archival workflows and processes (i.e. create a set of interoperability
specifications). For archival information packages, the following interoperability scenarios have been identified
([Figure 6](#fig6)):

- Export of data and metadata from source systems and transfer to SIP creation tools (or directly, as
a SIP, into preservation systems);
- Transfer of SIPs from SIP creation tools to preservation systems;
- Exchange of preservation systems where all AIPs need to be transferred (ingested) into a new
technological platform;
- Distributed storage and synchronisation of AIPs between multiple (technologically different)
preservation systems;
- Exchange of DIPs between preservation systems and access platforms or portals;
- Exchange of DIPs between various access platforms of portals.

<a name="fig6"></a>
![OAIS Entities](figs/fig_6_arch_wrkflw.svg "Archival workflow and tool ecosystem")

**Figure 6:** Archival workflow and tool ecosystem

As of 2014 (the start of the development of this specification), the state of interoperability in digital preservation was rather poor. While national and institutional practical implementation-level specifications existed to serve the need for data and metadata packaging and exchange, these were generally not interoperable with each other. On the contrary, available and widely used international specifications (most notably METS and PREMIS) lack the necessary implementation-level detail, needed to serve as an authoritative source for practical interoperability.

This situation has a remarkable effect on the cost of digital preservation. Namely, the tools developed in individual institutions are not reusable across institutional and state borders and therefore need to be redeveloped at each location. Globally, this raises the cost of digital preservation to a level which makes it unaffordable for smaller institutions and, at the same time, does often not allow for developing tools which would be sufficiently mature, user-friendly and not prone to errors. Furthermore, the multitude of national or institutional specifications does not allow internationally active source system providers (e.g. Oracle, Microsoft) to build a single native archiving functionality into their products, meaning that there is a need for bespoke development (and therefore added cost) for each installation of these source systems across all sectors and countries.

To overcome these limitations this document proposes a universal common specification, which can be implemented across borders, describing how data and metadata should be structured and packaged when transferred to archival systems, ingested and preserved in these, and re-used. Such a specification will allow data owners to build standardised interfaces for the export of their data regardless of the archives in question; and digital archives to build standardised interfaces for data ingest and access, regardless of the data providers and users in question.

Further, the aim of the common specification is to be sufficiently detailed and technical to allow for extended collaboration in regard to software development and pooling. Ideally the tools which implement the common specification for data export, transfer, ingest, preservation and reuse are exchangeable between institutions and administrations with minimal effort. This leads to a significant decrease in resources needed from any single institution and opens up an extended market for commercial software providers.
