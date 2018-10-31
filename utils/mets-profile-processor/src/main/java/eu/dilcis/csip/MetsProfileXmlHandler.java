package eu.dilcis.csip;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
	public void startDocument() throws SAXException {
		// Output the XML processing instruction
		this.outHandler.emit(xmlProcInstr);
		this.outHandler.nl();
	}

	@Override
	public void endDocument() {
	}

	@Override
	public void startElement(String namespaceURI, String sName, // simple name
			String qName, // qualified name
			Attributes attrs) {
		// Throw the text to output
		this.outHandler.voidBuffer();
		// Get the current ele name
		this.currEleName = deriveEleName(sName, qName);
	}

	@Override
	public void endElement(String namespaceURI, String sName, // simple name
			String qName  // qualified name
	) {
		this.currEleName = deriveEleName(sName, qName);
		this.outHandler.voidBuffer();

		this.currEleName = null;
	}

	private static String deriveEleName(final String sName,
			final String qName) {
		return (empty.equals(sName)) ? qName : sName; // element name
	}

	@Override
	public void characters(char buf[], int offset, int len) {
		String toAdd = new String(buf, offset, len);
		this.outHandler.addToBuffer(toAdd);
	}

	static class RequirementId implements Comparable<RequirementId> {
		final String prefix;
		final int number;

		private RequirementId(final String prefix, final int number) {
			this.prefix = prefix;
			this.number = number;
		}

		@Override
		public int compareTo(RequirementId other) {
			if (other.prefix.equals(this.prefix)) {
				return (this.number < other.number) ? -1
						: (this.number == other.number) ? 0 : -1;
			}
			return this.prefix.compareTo(other.prefix);
		}

		static RequirementId fromIdString(final String idString) {
			return new RequirementId("DEF", 0);
		}
	}

	static class Requirement {
		final RequirementId id;
		final String name;
		final String reqLevel;
		final String description;
		final List<String> examples;
		final String xPath;
		final String cardinality;

		private Requirement(final RequirementId id, final String name,
				final String reqLevel, final String description,
				final List<String> examples, final String xPath,
				final String cardinality) {
			super();
			this.id = id;
			this.name = name;
			this.reqLevel = reqLevel;
			this.description = description;
			this.examples = examples;
			this.xPath = xPath;
			this.cardinality = cardinality;
		}

		static class Builder {
			private RequirementId id;
			private String name = "name";
			private String reqLevel = "DEFAULT";
			private String description = "Description";
			private List<String> examples = new ArrayList<>();
			private String xPath = "xpath";
			private String cardinality = "cardinality";

			public Builder() {
				super();
			}

			public Builder(Builder builder) {
				this(builder.build());
			}

			public Builder(Requirement req) {
				super();
				this.id = req.id;
				this.name = req.name;
				this.reqLevel = req.reqLevel;
				this.description = req.description;
				this.examples = req.examples;
				this.xPath = req.xPath;
				this.cardinality = req.cardinality;
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
			public Builder reqLevel(String rqLvl) {
				this.reqLevel = rqLvl;
				return this;
			}

			/**
			 * @param description
			 *            the description to set
			 */
			public Builder description(String dscrptn) {
				this.description = dscrptn;
				return this;
			}

			/**
			 * @param examples
			 *            the examples to set
			 */
			public Builder examples(List<String> xmpls) {
				this.examples = xmpls;
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
						this.description, this.examples, this.xPath,
						this.cardinality);
			}
		}
	}
}
