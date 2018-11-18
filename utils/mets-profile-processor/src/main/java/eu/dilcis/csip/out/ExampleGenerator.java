package eu.dilcis.csip.out;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Class to handle the generation of the examples both insine and outside of
 * Appendices. Extends the XmlFragmentGenerator.
 * 
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *         <a href="https://github.com/carlwilson">carlwilson AT github</a>
 *
 * @version 0.1
 * 
 *          Created 17 Nov 2018:22:04:03
 */

public final class ExampleGenerator extends XmlFragmentGenerator {
	private final static String space = " "; //$NON-NLS-1$
	private final static String exampleHead = "Example:"; //$NON-NLS-1$
	private final static String exampleNumHead = "Example %s:"; //$NON-NLS-1$

	public ExampleGenerator() throws UnsupportedEncodingException {
		super();
	}

	public ExampleGenerator(final OutputHandler handler) {
		super(handler);
	}

	public void startExample(final String label) throws IOException {
		this.strExmpl(exampleHead, label);
	}

	public void startExample(final String label, final String number)
			throws IOException {
		this.strExmpl(String.format(exampleNumHead, number), label);
	}

	private void strExmpl(final String head, final String label)
			throws IOException {
		this.handler.nl();
		this.handler.emit(MarkdownFormatter.makeBold(head) + space + label);
		this.handler.nl();
		this.handler.nl();
		this.handler.emit(MarkdownFormatter.mdInlineXml);
	}

	public void endExample() throws IOException {
		this.handler.nl();
		this.handler.emit(MarkdownFormatter.mdInlineMarker);
		this.handler.nl();
		this.handler.nl();
	}
}
