package eu.dilcis.csip.out;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.xml.sax.Attributes;

/**
 * Abstract class to handle the pain of XML element generation, e.g.
 * indentation.
 * 
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *         <a href="https://github.com/carlwilson">carlwilson AT github</a>
 *
 * @version 0.1
 * 
 *          Created 17 Nov 2018:20:59:46
 */

abstract class XmlFragmentGenerator {
	protected static final int defaultSpaces = 2;
	protected static final int defaultIndent = 0;
	protected final OutputHandler handler;
	private final int indentSpaces;
	private int indent;

	/**
	 * Constructs a default {@link XmlFragmentGenerator} instance with
	 * {@link XmlFragmentGenerator.defaultSpaces} spaces and a
	 * {@link XmlFragmentGenerator.defaultIndent} starting indent point. Output
	 * defaults to STDOUT.
	 * 
	 * @throws UnsupportedEncodingException
	 */
	protected XmlFragmentGenerator() throws UnsupportedEncodingException {
		this(defaultSpaces);
	}

	protected XmlFragmentGenerator(final int indentSpaces)
			throws UnsupportedEncodingException {
		this(OutputHandler.toStdOut(), indentSpaces, defaultIndent);
	}

	protected XmlFragmentGenerator(final OutputHandler handler) {
		this(handler, defaultSpaces, defaultIndent);
	}

	protected XmlFragmentGenerator(final OutputHandler handler,
			final int indentSpaces) {
		this(handler, indentSpaces, defaultIndent);
	}

	protected XmlFragmentGenerator(final OutputHandler handler,
			final int indentSpaces, final int indent) {
		this.handler = handler;
		this.indentSpaces = indentSpaces;
		this.indent = indent;
	}

	public void outputEleStart(final String eleName, final Attributes attrs)
			throws IOException {
		this.handler.nl();
		this.indent();
		this.handler.emit(XmlFormatter.eleStartTag(eleName, attrs));
		this.indent++;
	}

	public void outputEleEnd(final String eleName, final String eleVal)
			throws IOException {
		this.indent--;
		if (eleVal == null || eleVal.trim().isEmpty()) {
			this.handler.nl();
			this.indent();
		} else {
			this.handler.emit(eleVal.trim());
		}
		this.handler.emit(XmlFormatter.eleEndTag(eleName));
	}

	private void indent() throws IOException {
		this.handler.emit(XmlFormatter.indent(this.indent, this.indentSpaces));
	}
}
