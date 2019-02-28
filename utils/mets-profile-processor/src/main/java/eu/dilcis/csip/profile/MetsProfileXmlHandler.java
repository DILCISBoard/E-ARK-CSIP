package eu.dilcis.csip.profile;

import java.io.IOException;
import java.nio.file.Path;
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
import eu.dilcis.csip.out.ExampleGenerator;
import eu.dilcis.csip.out.OutputHandler;
import eu.dilcis.csip.out.RequirementTableGenerator;
import eu.dilcis.csip.out.SchemaAppendixGenerator;
import eu.dilcis.csip.out.XmlCharBuffer;

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
	private static final String sectIoMess = "Error opening example file for section %s."; //$NON-NLS-1$
	private static final String empty = ""; //$NON-NLS-1$
	private static final String space = " "; //$NON-NLS-1$
	private static final String period = "."; //$NON-NLS-1$
	private static final String headEle = "head"; //$NON-NLS-1$
	private static final String appendixEle = "Appendix"; //$NON-NLS-1$
	private static final String contextEle = "context"; //$NON-NLS-1$
	private static final String exampleEle = "Example"; //$NON-NLS-1$
	private static final String extSchemaEle = "external_schema"; //$NON-NLS-1$
	private static final String maintEle = "maintenance_agency"; //$NON-NLS-1$
	private static final String nameEle = "name"; //$NON-NLS-1$
	private static final String uriEle = "URI"; //$NON-NLS-1$
	private static final String urlEle = "URL"; //$NON-NLS-1$
	private static final String vocabEle = "vocabulary"; //$NON-NLS-1$
	private static final String exampleAtt = "EXAMPLES"; //$NON-NLS-1$
	private static final String idAtt = "ID"; //$NON-NLS-1$
	private static final String labelAtt = "LABEL"; //$NON-NLS-1$
	private static final String numberAtt = "NUMBER"; //$NON-NLS-1$
	private static final String anchorEle = "a"; //$NON-NLS-1$
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
	private boolean inAppendix = false;
	private boolean inExtSchema = false;
	private boolean inVocab = false;
	private RequirementTableGenerator tableGen;
	private Requirement.Builder reqBuilder = new Requirement.Builder();
	private int reqCounter = 0;
	private String currDefTerm = null;
	private Section currentSect;
	private final Path projRoot;

	private final Map<Section, Set<String>> exampleMap = new HashMap<>();
	private final Map<Section, ExampleGenerator> exampleHandlers = new HashMap<>();
	private ExampleGenerator appendixGenerator = null;
	private RequirementTableGenerator reqsAppndxGen;

	private final SchemaAppendixGenerator schemaGen = new SchemaAppendixGenerator();
	private ExternalSchema.Builder schemaBuilder;
	private ControlledVocabulary.Builder vocabBuilder;

	public MetsProfileXmlHandler(final ProcessorOptions opts) {
		super();
		this.opts = opts;
		this.projRoot = opts.profileFile.getParent().getParent();
	}
	// ===========================================================
	// SAX DocumentHandler methods
	// ===========================================================

	public void processProfile() throws SAXException, IOException {
		this.reqsAppndxGen = RequirementTableGenerator.instance();
		saxParser.parse(this.opts.profileFile.toFile(), this);
	}

	@Override
	public void startElement(String namespaceURI, String sName, // simple name
			String qName, // qualified name
			Attributes attrs) throws SAXException {
		// Get the current ele name
		this.currEleName = qName;
		if (Requirement.isRequirementEle(this.currEleName)) {
			this.processRequirementAttrs(attrs);
		} else if (this.inRequirement) {
			this.processRequirementChildStart();
		} else if (Section.isSection(this.currEleName)) {
			this.startSection();
		} else if (exampleEle.equals(this.currEleName)) {
			this.startExample(attrs);
		} else if (this.inExample) {
			this.fragStart(this.getSectionExampleHandler(this.currentSect),
					attrs);
		} else if (appendixEle.equals(this.currEleName)) {
			this.startAppendix(attrs);
		} else if (this.inAppendix) {
			this.fragStart(this.appendixGenerator, attrs);
		} else if (extSchemaEle.equals(this.currEleName)) {
			this.inExtSchema = true;
			this.schemaBuilder = new ExternalSchema.Builder();
		} else if (vocabEle.equals(this.currEleName)) {
			this.inVocab = true;
			this.vocabBuilder = new ControlledVocabulary.Builder().id(getId(attrs));
		}
		this.charBuff.voidBuffer();
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
			endExample();
		} else if (this.inExample) {
			fragEnd(this.getSectionExampleHandler(this.currentSect));
		} else if (Section.isSection(this.currEleName)) {
			endSection();
		} else if (appendixEle.equals(this.currEleName)) {
			this.endAppendix();
		} else if (this.inAppendix) {
			fragEnd(this.appendixGenerator);
		} else if (extSchemaEle.equals(this.currEleName)) {
			this.inExtSchema = false;
			this.schemaGen.add(this.schemaBuilder.build());
		} else if (this.inExtSchema) {
			this.processSchemaEle();
		} else if (vocabEle.equals(this.currEleName)) {
			this.inVocab = false;
			this.schemaGen.add(this.vocabBuilder.build());
		} else if (this.inVocab) {
			this.processVocabEle();
		}
		this.charBuff.voidBuffer();
		this.currEleName = null;
	}

	@Override
	public void endDocument() throws SAXException {
		try {
			this.reqsAppndxGen.toTable(OutputHandler.toAppendix(this.projRoot, "requirements"));
			this.schemaGen.generateAppendix(this.projRoot);
			
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
			if (exampleAtt.equals(aName)) {
				this.processExampleAtt(attrs.getValue(i));
			}
		}
	}

	private void processExampleAtt(final String attVal) {
		if (attVal == null || attVal.isEmpty()) {
			return;
		}
		String[] exampleIds = (attVal.contains(space)) ? attVal.split(space)
				: new String[] { attVal };
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
		this.reqsAppndxGen.add(req);
		this.reqBuilder = new Requirement.Builder();
	}

	private void processRequirementChildStart() {
		switch (this.currEleName) {
		case anchorEle:
			this.reqBuilder.description(this.charBuff.getBufferValue());
			break;
		default:
			break;
		}
	}

	private void processRequirementChild() {
		switch (this.currEleName) {
		case headEle:
			this.reqBuilder.name(this.charBuff.getBufferValue());
			break;
		case defTermEle:
			this.currDefTerm = this.charBuff.getBufferValue();
			break;
		case defDefEle:
			this.reqBuilder.defPair(this.currDefTerm,
					this.charBuff.getBufferValue());
			break;
		case paraEle:
			this.reqBuilder.description(this.charBuff.getBufferValue());
			break;
		case anchorEle:
			this.reqBuilder.description(this.charBuff.getBufferValue());
			break;
		default:
			break;
		}
	}

	private void processSchemaEle() {
		switch (this.currEleName) {
		case nameEle:
			this.schemaBuilder.name(this.charBuff.getBufferValue());
			break;
		case urlEle:
			this.schemaBuilder.url(this.charBuff.getBufferValue());
			break;
		case contextEle:
			this.schemaBuilder.context(this.charBuff.getBufferValue());
			break;
		case paraEle:
			this.schemaBuilder.note(this.charBuff.getBufferValue());
			break;
		default:
			break;
		}
	}

	private void processVocabEle() {
		switch (this.currEleName) {
		case nameEle:
			this.vocabBuilder.name(this.charBuff.getBufferValue());
			break;
		case maintEle:
			this.vocabBuilder.maintenanceAgency(this.charBuff.getBufferValue());
			break;
		case uriEle:
			this.vocabBuilder.uri(this.charBuff.getBufferValue());
			break;
		case contextEle:
			this.vocabBuilder.context(this.charBuff.getBufferValue());
			break;
		case paraEle:
			this.vocabBuilder.description(this.charBuff.getBufferValue());
			break;
		default:
			break;
		}
	}

	private void startSection() {
		this.currentSect = Section.fromEleName(this.currEleName);
		this.exampleMap.put(this.currentSect, new HashSet<>());
		this.tableGen = RequirementTableGenerator.instance();
	}

	private void endSection() throws SAXException {
		this.currentSect = Section.fromEleName(this.currEleName);
		try {
			this.tableGen.toTable(OutputHandler
					.toSectionRequirements(this.projRoot, this.currentSect));
		} catch (IOException excep) {
			throw new SAXException(ioExcepMess, excep);
		}
		this.reqCounter += this.tableGen.getRequirmentCount();
	}

	private void startExample(final Attributes attrs) throws SAXException {
		this.inExample = true;
		final String id = getId(attrs);
		for (Section section : this.exampleMap.keySet()) {
			if (this.exampleMap.get(section).contains(id)) {
				ExampleGenerator gene = this.getSectionExampleHandler(section);
				try {
					gene.startExample(getLabel(attrs));
				} catch (IOException excep) {
					throw new SAXException(ioExcepMess, excep);
				}
				return;
			}
		}
	}

	private void endExample() throws SAXException {
		ExampleGenerator gene = this.getSectionExampleHandler(this.currentSect);
		try {
			gene.endExample();
		} catch (IOException excep) {
			throw new SAXException(ioExcepMess, excep);
		}
		this.inExample = false;
	}

	private void startAppendix(final Attributes attrs) throws SAXException {
		this.inAppendix = true;
		try {
			if (this.appendixGenerator == null)
				this.appendixGenerator = new ExampleGenerator(OutputHandler
						.toAppendix(this.projRoot, exampleAtt.toLowerCase()));
			this.appendixGenerator.startExample(getLabel(attrs),
					getNumber(attrs));
		} catch (IOException excep) {
			throw new SAXException(ioExcepMess, excep);
		}
	}

	private void endAppendix() throws SAXException {
		try {
			this.appendixGenerator.endExample();
		} catch (IOException excep) {
			throw new SAXException(ioExcepMess, excep);
		}
		this.inAppendix = false;
	}

	private static String getId(final Attributes attrs) {
		return getAttValue(attrs, idAtt);
	}

	private static String getLabel(final Attributes attrs) {
		return getAttValue(attrs, labelAtt);
	}

	private static String getNumber(final Attributes attrs) {
		return getAttValue(attrs, numberAtt);
	}

	private static String getAttValue(final Attributes attrs,
			final String attName) {
		if (attrs != null) {
			for (int i = 0; i < attrs.getLength(); i++) {
				String aName = attrs.getLocalName(i); // Attr name
				if (empty.equals(aName))
					aName = attrs.getQName(i);
				if (attName.equals(aName))
					return attrs.getValue(i);
			}
		}
		return empty;
	}

	private void fragStart(ExampleGenerator generator, final Attributes attrs)
			throws SAXException {
		try {
			generator.outputEleStart(this.currEleName, attrs);
		} catch (IOException excep) {
			throw new SAXException(ioExcepMess, excep);
		}
	}

	private void fragEnd(ExampleGenerator generator) throws SAXException {
		try {
			generator.outputEleEnd(this.currEleName,
					this.charBuff.voidBuffer());
		} catch (IOException excep) {
			throw new SAXException(ioExcepMess, excep);
		}
	}

	private ExampleGenerator getSectionExampleHandler(Section section)
			throws SAXException {
		ExampleGenerator gene = this.exampleHandlers.get(section);
		try {
			if (gene == null) {
				OutputHandler handler = OutputHandler
						.toSectionExamples(this.projRoot, section);
				gene = new ExampleGenerator(handler);
				this.exampleHandlers.put(section, gene);
			}
		} catch (IOException e) {
			throw new SAXException(String.format(sectIoMess, section.sectName),
					e);
		}
		this.currentSect = section;
		return gene;
	}

	@Override
	public void characters(char buf[], int offset, int len) {
		String toAdd = new String(buf, offset, len);
		this.charBuff.addToBuffer(toAdd);
	}
}
