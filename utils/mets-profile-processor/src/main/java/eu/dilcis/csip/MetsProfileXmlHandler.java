package eu.dilcis.csip;

import java.io.File;
import java.io.IOException;

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
 *          Created 22 Jun 2018:01:25:39
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

	public MetsProfileXmlHandler(final ProcessorOptions opts) {
		this.opts = opts;
	}
	// ===========================================================
	// SAX DocumentHandler methods
	// ===========================================================

	public void processProfile() throws IOException, SAXException {
		for (File dirToParse : this.opts.toProcess) {
			for (File child : dirToParse.listFiles()) {
				if (child.isFile() && child.getName().toLowerCase()
						.endsWith(xmlExtension)) {
					if (this.opts.isToDir()) {
						this.outHandler = new OutputHandler(child);
					} else {
						this.outHandler = new OutputHandler();
					}
					saxParser.parse(child, this);
				}
			}
		}

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
			Attributes attrs) throws SAXException {
		// Throw the text to output
		this.outHandler.voidBuffer();
		// Get the current ele name
		this.currEleName = deriveEleName(sName, qName);
		outputEleStart(this.outHandler, this.currEleName, attrs);
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

	private static void outputEleStart(final OutputHandler handler,
			final String eleName, final Attributes attrs) throws SAXException {
		handler.emit(lt + eleName);
		if (attrs != null) {
			for (int i = 0; i < attrs.getLength(); i++) {
				String aName = attrs.getLocalName(i); // Attr name
				if (empty.equals(aName))
					aName = attrs.getQName(i);
				handler.emit(period);
				handler.emit(
						aName + atValOpen + attrs.getValue(i) + atValClosed);
			}
		}
		handler.emit(gt);
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
				return (this.number < other.number) ? -1 : (this.number == other.number) ? 0 : -1;
			}
			return this.prefix.compareTo(other.prefix);
		}
		
		static RequirementId fromIdString(final String idString) {
			return new RequirementId("DEF", 0);
		}
	}
}
