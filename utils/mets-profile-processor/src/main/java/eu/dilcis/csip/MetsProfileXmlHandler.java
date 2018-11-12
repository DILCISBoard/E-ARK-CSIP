package eu.dilcis.csip;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
	static final String ioExcepMess = "IOException generating markdown tables."; //$NON-NLS-1$
	static final String period = "."; //$NON-NLS-1$
	static final String headEle = "head"; //$NON-NLS-1$
	static final String paraEle = "p"; //$NON-NLS-1$
	static final String defTermEle = "dt"; //$NON-NLS-1$
	static final String defDefEle = "dd"; //$NON-NLS-1$
	static final String sectHeadTemplate = "5.3.%s Use of the METS %s (element %s)"; //$NON-NLS-1$

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
	private MarkdownTableGenerator tableGen;
	private Requirement.Builder reqBuilder = new Requirement.Builder();
	private int reqCounter = 0;
	private String currDefTerm = null;
	private final Path metsReqRoot;

	public MetsProfileXmlHandler(final ProcessorOptions opts)
			throws UnsupportedEncodingException, IOException {
		super();
		this.opts = opts;
		Path toReqRoot = Paths.get("..", "specification", "implementation", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				"metadata", "mets");  //$NON-NLS-1$ //$NON-NLS-2$
		this.metsReqRoot = opts.profileFile.getParent().resolve(toReqRoot);
		this.outHandler = OutputHandler.toStdOut();
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
			Attributes attrs) {
		// Get the current ele name
		this.currEleName = qName;
		this.outHandler.voidBuffer();
		if (Requirement.isRequirementEle(this.currEleName)) {
			this.inRequirement = true;
			this.processRequirementAttrs(attrs);
		} else if (MarkdownTemplater.Section.isSection(this.currEleName)) {
			MarkdownTemplater.Section section = MarkdownTemplater.Section
					.fromEleName(this.currEleName);
			this.startSection(section);
		}
	}

	@Override
	public void endElement(String namespaceURI, String sName, // simple name
			String qName  // qualified name
	) throws SAXException {
		this.currEleName = qName;
		if (Requirement.isRequirementEle(this.currEleName)) {
			this.processRequirementEle();
		} else if (this.inRequirement) {
			this.processRequirementChild();
		} else if (MarkdownTemplater.Section.isSection(this.currEleName)) {
			MarkdownTemplater.Section section = MarkdownTemplater.Section
					.fromEleName(this.currEleName);
			try {
				this.tableGen.toTable(OutputHandler
						.toSectionRequirements(this.metsReqRoot, section));
			} catch (IOException excep) {
				throw new SAXException(ioExcepMess, excep);
			}
			this.reqCounter += this.tableGen.requirements.size();
		}
		this.outHandler.voidBuffer();
		this.currEleName = null;
	}

	@Override
	public void endDocument() throws SAXException {
		try {
			this.outHandler.nl();
			this.outHandler.emit("======================================="); //$NON-NLS-1$
			this.outHandler.nl();
			this.outHandler.emit("Total Requirements: " + this.reqCounter); //$NON-NLS-1$
			this.outHandler.nl();
		} catch (IOException excep) {
			throw new SAXException(ioExcepMess, excep);
		}
	}

	private void processRequirementAttrs(Attributes attrs) {
		if (attrs == null)
			return;
		for (int i = 0; i < attrs.getLength(); i++) {
			String aName = attrs.getLocalName(i); // Attr name
			if (Requirement.empty.equals(aName))
				aName = attrs.getQName(i);
			this.reqBuilder.processAttr(aName, attrs.getValue(i));
		}
	}

	private void processRequirementEle() {
		this.inRequirement = false;
		final Requirement req = this.reqBuilder.build();
		if (req.id == eu.dilcis.csip.Requirement.RequirementId.DEFAULT_ID)
			return;
		this.tableGen.add(req);
		this.reqBuilder = new Requirement.Builder();
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
			this.reqBuilder.defPair(this.currDefTerm,
					this.outHandler.getBufferValue());
			break;
		case MetsProfileXmlHandler.paraEle:
			this.reqBuilder.description(this.outHandler.getBufferValue());
			break;
		default:
			break;
		}
	}

	private void startSection(final MarkdownTemplater.Section section) {
		this.tableGen = new MarkdownTableGenerator(section);
	}

	@Override
	public void characters(char buf[], int offset, int len) {
		String toAdd = new String(buf, offset, len);
		this.outHandler.addToBuffer(toAdd);
	}
}
