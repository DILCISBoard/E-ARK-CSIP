package eu.dilcis.csip.profile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import eu.dilcis.csip.ProcessorOptions;
import eu.dilcis.csip.out.MarkdownTableGenerator;
import eu.dilcis.csip.out.OutputHandler;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *         <a href="https://github.com/carlwilson">carlwilson AT github</a>
 *
 * @version 0.1
 * 
 *          Created 24 Oct 2018:01:25:39
 */

public final class MetsProfileXmlHandler extends DefaultHandler {
	private static final SAXParserFactory spf = SAXParserFactory.newInstance();
	private static final String initSaxMess = "Couldn't initialise SAX XML Parser."; //$NON-NLS-1$
	private static final String ioExcepMess = "IOException generating markdown tables."; //$NON-NLS-1$
	private static final String period = "."; //$NON-NLS-1$
	private static final String headEle = "head"; //$NON-NLS-1$
	private static final String exampleEle = "Example"; //$NON-NLS-1$
	private static final String paraEle = "p"; //$NON-NLS-1$
	private static final String defTermEle = "dt"; //$NON-NLS-1$
	private static final String defDefEle = "dd"; //$NON-NLS-1$

	static final String xmlExtension = period + "xml"; //$NON-NLS-1$
	static final String xmlProcInstr = "<?xml version='1.0' encoding='UTF-8'?>"; //$NON-NLS-1$
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
	private XmlCharBuffer charBuff = new XmlCharBuffer();
	private String currEleName;
	private final ProcessorOptions opts;
	private boolean inRequirement = false;
	private boolean inExample = false;
	private MarkdownTableGenerator tableGen;
	private Requirement.Builder reqBuilder = new Requirement.Builder();
	private int reqCounter = 0;
	private String currDefTerm = null;
	private Section currentSect;
	private final Path metsReqRoot;

	private final Map<Section, Set<String>> exampleMap = new HashMap<>();
	private final Map<Section, OutputHandler> exampleHandlers = new HashMap<>();

	public MetsProfileXmlHandler(final ProcessorOptions opts) {
		super();
		this.opts = opts;
		Path toReqRoot = Paths.get("..", "specification", "implementation", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				"metadata", "mets"); //$NON-NLS-1$ //$NON-NLS-2$
		this.metsReqRoot = opts.profileFile.getParent().resolve(toReqRoot);
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
		this.charBuff.voidBuffer();
		if (Requirement.isRequirementEle(this.currEleName)) {
			this.processRequirementAttrs(attrs);
		} else if (Section.isSection(this.currEleName)) {
			this.startSection();
		} else if (exampleEle.equals(this.currEleName)) {
			this.startExample(attrs);
		} else if (this.inExample) {
			try {
				OutputHandler handler = this.getSectionExampleHandler(this.currentSect);
				handler.outputEleStart(this.currEleName, attrs);
			} catch (IOException excep) {
				throw new SAXException(ioExcepMess, excep);
			}
		}
	}

	@Override
	public void endElement(String namespaceURI, String sName, // simple name
			String qName // qualified name
	) throws SAXException {
		this.currEleName = qName;
		if (Requirement.isRequirementEle(this.currEleName)) {
			this.processRequirementEle();
		} else if (this.inRequirement) {
			this.processRequirementChild();
		} else if (exampleEle.equals(this.currEleName)) {
			OutputHandler handler = this.getSectionExampleHandler(this.currentSect);
			try {
				handler.nl();
				handler.emit("```");
				handler.nl();
				handler.nl();
			} catch (IOException excep) {
				throw new SAXException(ioExcepMess, excep);
			}
			this.inExample = false;
		} else if (this.inExample) {
			try {
				OutputHandler handler = this.getSectionExampleHandler(this.currentSect);
				handler.outputEleEnd(this.currEleName, this.charBuff.voidBuffer());
			} catch (IOException excep) {
				throw new SAXException(ioExcepMess, excep);
			}
		} else if (Section.isSection(this.currEleName)) {
			this.currentSect = Section.fromEleName(this.currEleName);
			try {
				this.tableGen.toTable(OutputHandler.toSectionRequirements(this.metsReqRoot, this.currentSect));
			} catch (IOException excep) {
				throw new SAXException(ioExcepMess, excep);
			}
			this.reqCounter += this.tableGen.getRequirmentCount();
		}
		this.charBuff.voidBuffer();
		this.currEleName = null;
	}

	@Override
	public void endDocument() throws SAXException {
		try {
			OutputHandler outHandler = OutputHandler.toStdOut();
			for (Section sect : this.exampleMap.keySet()) {
				System.out.println(sect.sectName);
				for (String ex : this.exampleMap.get(sect)) {
					System.out.println(ex);
				}
			}
			outHandler.nl();
			outHandler.emit("======================================="); //$NON-NLS-1$
			outHandler.nl();
			outHandler.emit("Total Requirements: " + this.reqCounter); //$NON-NLS-1$
			outHandler.nl();
		} catch (IOException excep) {
			throw new SAXException(ioExcepMess, excep);
		}
	}

	private void processRequirementAttrs(Attributes attrs) {
		this.inRequirement = true;
		if (attrs == null)
			return;
		for (int i = 0; i < attrs.getLength(); i++) {
			String aName = attrs.getLocalName(i); // Attr name
			if (Requirement.empty.equals(aName))
				aName = attrs.getQName(i);
			this.reqBuilder.processAttr(aName, attrs.getValue(i));
			if ("EXAMPLES".equals(aName)) {
				this.processExampleAtt(attrs.getValue(i));
			}
		}
	}

	private void processExampleAtt(final String attVal) {
		if (attVal == null || attVal.isEmpty()) {
			return;
		}
		String[] exampleIds = (attVal.contains(" ")) ? attVal.split(" ") : new String[] { attVal };
		for (String exKey : exampleIds) {
			this.exampleMap.get(this.currentSect).add(exKey);
		}
	}

	private void processRequirementEle() {
		this.inRequirement = false;
		final Requirement req = this.reqBuilder.build();
		if (req.id == eu.dilcis.csip.profile.Requirement.RequirementId.DEFAULT_ID)
			return;
		this.tableGen.add(req);
		this.reqBuilder = new Requirement.Builder();
	}

	private void processRequirementChild() {
		switch (this.currEleName) {
		case MetsProfileXmlHandler.headEle:
			this.reqBuilder.name(this.charBuff.getBufferValue());
			break;
		case MetsProfileXmlHandler.defTermEle:
			this.currDefTerm = this.charBuff.getBufferValue();
			break;
		case MetsProfileXmlHandler.defDefEle:
			this.reqBuilder.defPair(this.currDefTerm, this.charBuff.getBufferValue());
			break;
		case MetsProfileXmlHandler.paraEle:
			this.reqBuilder.description(this.charBuff.getBufferValue());
			break;
		default:
			break;
		}
	}

	private void startSection() {
		this.currentSect = Section.fromEleName(this.currEleName);
		this.exampleMap.put(this.currentSect, new HashSet<>());
		this.tableGen = new MarkdownTableGenerator(this.currentSect);
	}

	private void startExample(final Attributes attrs) throws SAXException {
		this.inExample = true;
		final String id = getId(attrs);
		for (Section section : this.exampleMap.keySet()) {
			if (this.exampleMap.get(section).contains(id)) {
				OutputHandler handler = this.getSectionExampleHandler(section);
				try {
					handler.nl();
					handler.emit("**Example:** " + getLabel(attrs));
					handler.nl();
					handler.nl();
					handler.emit("```xml");
				} catch (IOException e) {
					throw new SAXException(e);
				}
				return;
			}
		}
	}

	private static String getId(final Attributes attrs) {
		return getAttValue(attrs, "ID");
	}

	private static String getLabel(final Attributes attrs) {
		return getAttValue(attrs, "LABEL");
	}

	private static String getAttValue(final Attributes attrs, final String attName) {
		if (attrs != null) {
			for (int i = 0; i < attrs.getLength(); i++) {
				String aName = attrs.getLocalName(i); // Attr name
				if ("".equals(aName))
					aName = attrs.getQName(i);
				if (attName.equals(aName))
					return attrs.getValue(i);
			}
		}
		return "";
	}

	private OutputHandler getSectionExampleHandler(Section section) throws SAXException {
		OutputHandler handler = this.exampleHandlers.get(section);
		try {
			if (handler == null) {
				handler = OutputHandler.toSectionExamples(this.metsReqRoot, section);
				this.exampleHandlers.put(section, handler);
			}
		} catch (IOException e) {
			throw new SAXException(String.format("Error opening example file for section %s.", section.sectName), e);
		}
		this.currentSect = section;
		return handler;
	}

	@Override
	public void characters(char buf[], int offset, int len) {
		String toAdd = new String(buf, offset, len);
		this.charBuff.addToBuffer(toAdd);
	}
}
