package eu.dilcis.csip;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *         <a href="https://github.com/carlwilson">carlwilson AT github</a>
 *
 * @version 0.1
 * 
 *          Created 24 Oct 2018:01:25:39
 */

public final class MetsProfileXmlHandler extends DefaultHandler {
	static final SAXParserFactory spf = SAXParserFactory.newInstance();
	static final String initSaxMess = "Couldn't initialise SAX XML Parser."; //$NON-NLS-1$
	static final String period = "."; //$NON-NLS-1$
	static final String lt = "<"; //$NON-NLS-1$
	static final String gt = ">"; //$NON-NLS-1$
	static final String empty = ""; //$NON-NLS-1$
	static final String atValOpen = "=\""; //$NON-NLS-1$
	static final String atValClosed = "\""; //$NON-NLS-1$
	static final String requirementEle = "requirement"; //$NON-NLS-1$
	static final String descriptionEle = "description"; //$NON-NLS-1$
	static final String headEle = "head"; //$NON-NLS-1$
	static final String paraEle = "p"; //$NON-NLS-1$
	static final String defListEle = "dl"; //$NON-NLS-1$
	static final String defTermEle = "dt"; //$NON-NLS-1$
	static final String defDefEle = "dd"; //$NON-NLS-1$
	static final String xPathTerm = "METS XPath"; //$NON-NLS-1$
	static final String cardTerm = "Cardinality"; //$NON-NLS-1$
	static final String sectHeadTemplate = "5.3.%s Use of the METS %s (element %s)";

	static final String xmlExtension = period + "xml"; //$NON-NLS-1$
	static final String xmlProcInstr = "<?xml version='1.0' encoding='UTF-8'?>";  //$NON-NLS-1$
	static {
		spf.setNamespaceAware(true);
	}
	static final SAXParser saxParser;
	static {
		try {
			saxParser = spf.newSAXParser();
		} catch (ParserConfigurationException | SAXException excep) {
			throw new IllegalStateException(initSaxMess, excep);
		}
	}
	private OutputHandler outHandler;
	private String currEleName;
	private final ProcessorOptions opts;
	private boolean inRequirement = false;
	private List<Requirement> requirements = new ArrayList<>();
	private Requirement.Builder reqBuilder = new Requirement.Builder();
	private String currDefTerm = null;

	public MetsProfileXmlHandler(final ProcessorOptions opts)
			throws UnsupportedEncodingException, IOException {
		super();
		this.opts = opts;
		this.outHandler = opts.isToDir()
				? new OutputHandler(opts.outDir.toFile())
				: new OutputHandler();
	}
	// ===========================================================
	// SAX DocumentHandler methods
	// ===========================================================

	public void processProfile() throws SAXException, IOException {
		saxParser.parse(this.opts.profileFile.toFile(), this);
	}

	@Override
	public void startElement(String namespaceURI, String sName, // simple name
			String qName, // qualified name
			Attributes attrs) throws SAXException {
		// Get the current ele name
		this.currEleName = qName;
		this.outHandler.voidBuffer();
		if (isRequirementEle(this.currEleName)) {
			this.inRequirement = true;
			this.processRequirementAttrs(attrs);
		} else if (Section.isSection(this.currEleName)) {
			Section section = Section.fromEleName(this.currEleName);
			this.processSection(section);
		}
	}

	@Override
	public void endElement(String namespaceURI, String sName, // simple name
			String qName  // qualified name
	) throws SAXException {
		this.currEleName = qName;
		if (isRequirementEle(this.currEleName)) {
			this.processRequirementEle();
		} else if (this.inRequirement) {
			this.processRequirementChild();
		}
		this.outHandler.voidBuffer();
		this.currEleName = null;
	}

	@Override
	public void endDocument() throws SAXException {
		this.outHandler.emit("Total Requirements: " + this.requirements.size());
		this.outHandler.nl();
	}

	private void processRequirementAttrs(Attributes attrs) {
		if (attrs == null)
			return;
		for (int i = 0; i < attrs.getLength(); i++) {
			String aName = attrs.getLocalName(i); // Attr name
			if (empty.equals(aName))
				aName = attrs.getQName(i);
			this.reqBuilder.processAttr(aName, attrs.getValue(i));
		}
	}

	private void processRequirementEle() throws SAXException {
		this.inRequirement = false;
		final Requirement req = this.reqBuilder.build();
		if (req.id == RequirementId.DEFAULT)
			return;
		this.requirements.add(req);
		this.outHandler.emit(req.toString());
		this.reqBuilder = new Requirement.Builder();
		this.outHandler.nl();
	}

	private void processRequirementChild() {
		switch (this.currEleName) {
		case MetsProfileXmlHandler.headEle:
			this.reqBuilder.name(this.outHandler.getBufferValue());
			break;
		case MetsProfileXmlHandler.defTermEle:
			this.currDefTerm = this.outHandler.getBufferValue();
			break;
		case MetsProfileXmlHandler.defDefEle:
			this.reqBuilder.defPair(this.currDefTerm, this.outHandler.getBufferValue());
			break;
		case MetsProfileXmlHandler.paraEle:
			this.reqBuilder.description(this.outHandler.getBufferValue());
			break;
		default:
			break;
		}
	}

	private void processSection(final Section section) throws SAXException {
		this.outHandler.emit(section.getFullHeader());
		this.outHandler.nl();
	}

	private static boolean isRequirementEle(final String eleName) {
		return requirementEle.equals(eleName);
	}

	@Override
	public void characters(char buf[], int offset, int len) {
		String toAdd = new String(buf, offset, len);
		this.outHandler.addToBuffer(toAdd);
	}

	static class RequirementId implements Comparable<RequirementId> {
		final static String defPrefix = "PREF";
		final static int defNumber = -1;
		public final static RequirementId DEFAULT = new RequirementId();
		final String prefix;
		final int number;

		private RequirementId() {
			this(defPrefix, defNumber);
		}

		private RequirementId(final String prefix, final int number) {
			super();
			this.prefix = prefix;
			this.number = number;
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "RequirementId [prefix=" + this.prefix + ", number="
					+ this.number + "]";
		}

		@Override
		public int compareTo(RequirementId other) {
			if (other.prefix.equals(this.prefix)) {
				return (this.number < other.number) ? -1
						: (this.number == other.number) ? 0 : -1;
			}
			return this.prefix.compareTo(other.prefix);
		}

		/**
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + this.number;
			result = prime * result
					+ ((this.prefix == null) ? 0 : this.prefix.hashCode());
			return result;
		}

		/**
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof RequirementId)) {
				return false;
			}
			RequirementId other = (RequirementId) obj;
			if (this.number != other.number) {
				return false;
			}
			if (this.prefix == null) {
				if (other.prefix != null) {
					return false;
				}
			} else if (!this.prefix.equals(other.prefix)) {
				return false;
			}
			return true;
		}

		static RequirementId fromIdString(final String idString) {
			StringBuffer prefixBuff = new StringBuffer();
			StringBuffer numBuff = new StringBuffer();
			for (int i = 0; i < idString.length(); i++) {
				char c = idString.charAt(i);
				if (Character.isDigit(c)) {
					numBuff.append(c);
				} else {
					prefixBuff.append(c);
				}
			}
			return new RequirementId(prefixBuff.toString(),
					Integer.parseInt(numBuff.toString()));
		}
	}

	static class Requirement {
		public final static Requirement DEFAULT = new Requirement();
		final RequirementId id;
		final String name;
		final String reqLevel;
		final String relMat;
		final List<String> description;
		final List<String> examples;
		final String xPath;
		final String cardinality;

		private Requirement() {
			this(RequirementId.DEFAULT, empty, empty, empty,
					Collections.emptyList(), Collections.emptyList(), empty, empty);
		}

		private Requirement(final RequirementId id, final String name,
				final String reqLevel, final String relMat,
				final List<String> description, final List<String> examples,
				final String xPath, final String cardinality) {
			super();
			this.id = id;
			this.name = name;
			this.reqLevel = reqLevel;
			this.relMat = relMat;
			this.description = description;
			this.examples = examples;
			this.xPath = xPath;
			this.cardinality = cardinality;
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Requirement [id=" + this.id + ", name=" + this.name
					+ ", relMat=" + this.relMat + ", reqLevel=" + this.reqLevel
					+ ", description=" + this.description + ", examples="
					+ this.examples + ", xPath=" + this.xPath + ", cardinality="
					+ this.cardinality + "]";
		}

		/**
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((this.cardinality == null) ? 0
					: this.cardinality.hashCode());
			result = prime * result + ((this.description == null) ? 0
					: this.description.hashCode());
			result = prime * result
					+ ((this.examples == null) ? 0 : this.examples.hashCode());
			result = prime * result
					+ ((this.id == null) ? 0 : this.id.hashCode());
			result = prime * result
					+ ((this.name == null) ? 0 : this.name.hashCode());
			result = prime * result
					+ ((this.relMat == null) ? 0 : this.relMat.hashCode());
			result = prime * result
					+ ((this.reqLevel == null) ? 0 : this.reqLevel.hashCode());
			result = prime * result
					+ ((this.xPath == null) ? 0 : this.xPath.hashCode());
			return result;
		}

		/**
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof Requirement)) {
				return false;
			}
			Requirement other = (Requirement) obj;
			if (this.cardinality == null) {
				if (other.cardinality != null) {
					return false;
				}
			} else if (!this.cardinality.equals(other.cardinality)) {
				return false;
			}
			if (this.description == null) {
				if (other.description != null) {
					return false;
				}
			} else if (!this.description.equals(other.description)) {
				return false;
			}
			if (this.examples == null) {
				if (other.examples != null) {
					return false;
				}
			} else if (!this.examples.equals(other.examples)) {
				return false;
			}
			if (this.id == null) {
				if (other.id != null) {
					return false;
				}
			} else if (!this.id.equals(other.id)) {
				return false;
			}
			if (this.name == null) {
				if (other.name != null) {
					return false;
				}
			} else if (!this.name.equals(other.name)) {
				return false;
			}
			if (this.relMat == null) {
				if (other.relMat != null) {
					return false;
				}
			} else if (!this.relMat.equals(other.relMat)) {
				return false;
			}
			if (this.reqLevel == null) {
				if (other.reqLevel != null) {
					return false;
				}
			} else if (!this.reqLevel.equals(other.reqLevel)) {
				return false;
			}
			if (this.xPath == null) {
				if (other.xPath != null) {
					return false;
				}
			} else if (!this.xPath.equals(other.xPath)) {
				return false;
			}
			return true;
		}

		static class Builder {
			private RequirementId id;
			private String name;
			private String reqLevel;
			private String relMat;
			private List<String> description;
			private List<String> examples;
			private String xPath;
			private String cardinality;

			public Builder() {
				this(Requirement.DEFAULT);
			}

			public Builder(Builder builder) {
				this(builder.build());
			}

			public Builder(Requirement req) {
				super();
				this.id = req.id;
				this.name = req.name;
				this.reqLevel = req.reqLevel;
				this.relMat = req.relMat;
				this.description = new ArrayList<>(req.description);
				this.examples = new ArrayList<>(req.examples);
				this.xPath = req.xPath;
				this.cardinality = req.cardinality;
			}

			public Builder processAttr(final String attName,
					final String attValue) {
				switch (attName) {
				case "ID":
					this.id(RequirementId.fromIdString(attValue));
					break;

				case "REQLEVEL":
					this.reqLevel(attValue);
					break;

				case "RELATEDMAT":
					this.relMat(attValue);
					break;

				case "EXAMPLES":
					for (String example : attValue.split(" ")) {
						this.example(example);
					}
					break;

				default:
					break;
				}
				return this;
			}

			/**
			 * @param id
			 *            the id to set
			 */
			public Builder id(RequirementId iD) {
				this.id = iD;
				return this;
			}

			/**
			 * @param name
			 *            the name to set
			 */
			public Builder name(String nm) {
				this.name = nm;
				return this;
			}

			/**
			 * @param reqLevel
			 *            the reqLevel to set
			 */
			public Builder relMat(String rlMt) {
				this.relMat = rlMt;
				return this;
			}

			/**
			 * @param reqLevel
			 *            the reqLevel to set
			 */
			public Builder reqLevel(String rqLvl) {
				this.reqLevel = rqLvl;
				return this;
			}

			/**
			 * @param description
			 *            the description to set
			 */
			public Builder description(String dscrptn) {
				this.description.add(dscrptn);
				return this;
			}

			/**
			 * @param description
			 *            the description to set
			 */
			public Builder descriptions(List<String> dscrptns) {
				this.description = new ArrayList<>(dscrptns);
				return this;
			}

			/**
			 * @param examples
			 *            the examples to set
			 */
			public Builder examples(List<String> xmpls) {
				this.examples = new ArrayList<>(xmpls);
				return this;
			}

			/**
			 * @param example
			 *            the example to add
			 */
			public Builder example(String xmpl) {
				this.examples.add(xmpl);
				return this;
			}

			/**
			 * @param xPath
			 *            the xPath to set
			 */
			public Builder defPair(String term, final String def) {
				switch (term) {
				case MetsProfileXmlHandler.xPathTerm:
					return this.xPath(def);
				case MetsProfileXmlHandler.cardTerm:
					return this.cardinality(def);
				default:
					break;
				}
				return this;
			}

			/**
			 * @param xPath
			 *            the xPath to set
			 */
			public Builder xPath(String xPth) {
				this.xPath = xPth;
				return this;
			}

			/**
			 * @param cardinality
			 *            the cardinality to set
			 */
			public Builder cardinality(String crdnlty) {
				this.cardinality = crdnlty;
				return this;
			}

			public Requirement build() {
				return new Requirement(this.id, this.name, this.reqLevel,
						this.relMat, this.description, this.examples,
						this.xPath, this.cardinality);
			}
		}
	}

	static enum Section {
		ROOT("metsRootElement", "1", "root element", "mets"), HEADER("metsHdr",
				"2", "header", "metsHdr"), DMD_SEC("dmdSec", "3",
						"descriptive metadata", "dmdSec"), AMD_SEC("amdSec",
								"4", "administrative metadata",
								"amdSec"), FILE_SEC("fileSec", "5",
										"file section",
										"fileSec"), STRUCT_MAP("structMap", "6",
												"structural map",
												"structMap"), STRUCT_LINK(
														"structLink", "7",
														"structural link",
														"structLink");

		final String eleName;
		final String sectName;
		final String sectSubHeadNum;
		final String metsEleName;

		private Section(final String eleName, final String sectSubHeadNum,
				final String sectName, final String metsEleName) {
			this.eleName = eleName;
			this.sectName = sectName;
			this.sectSubHeadNum = sectSubHeadNum;
			this.metsEleName = metsEleName;
		}

		public String getFullHeader() {
			return String.format(sectHeadTemplate, this.sectSubHeadNum,
					this.sectName, this.metsEleName);
		}

		public String getDirName() {
			return this.metsEleName;
		}

		public static boolean isSection(final String eleName) {
			for (Section sect : Section.values()) {
				if (sect.eleName.equals(eleName)) {
					return true;
				}
			}
			return false;
		}

		public static Section fromEleName(final String eleName) {
			for (Section sect : Section.values()) {
				if (sect.eleName.equals(eleName)) {
					return sect;
				}
			}
			return null;
		}
	}
}
