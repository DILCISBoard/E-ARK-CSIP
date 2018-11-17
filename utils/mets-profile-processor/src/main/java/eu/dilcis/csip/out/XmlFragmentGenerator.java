package eu.dilcis.csip.out;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.xml.sax.Attributes;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *         <a href="https://github.com/carlwilson">carlwilson AT github</a>
 *
 * @version 0.1
 * 
 *          Created 17 Nov 2018:20:59:46
 */

abstract class XmlFragmentGenerator {
	private static final int defaultSpaces = 2;
	private static final int defaultIndent = 0;
	protected final OutputHandler handler;
	private final int indentSpaces;
	private int indent;

	protected XmlFragmentGenerator() throws UnsupportedEncodingException {
		this(defaultSpaces);
	}

	protected XmlFragmentGenerator(final int indentSpaces) throws UnsupportedEncodingException {
		this(OutputHandler.toStdOut(), indentSpaces, defaultIndent);
	}

	protected XmlFragmentGenerator(final OutputHandler handler) {
		this(handler, defaultSpaces, defaultIndent);
	}

	protected XmlFragmentGenerator(final OutputHandler handler, final int indentSpaces) {
		this(handler, indentSpaces, defaultIndent);
	}

	protected XmlFragmentGenerator(final OutputHandler handler, final int indentSpaces, final int indent) {
		this.handler = handler;
		this.indentSpaces = indentSpaces;
		this.indent = indent;
	}

	public void outputEleStart(final String eleName, final Attributes attrs) throws IOException {
		this.handler.nl();
		this.indent();
		this.handler.emit(XmlFormatter.eleStartTag(eleName, attrs));
		this.indent++;
	}

	public void outputEleEnd(final String eleName,
			final String eleVal) throws IOException {
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
