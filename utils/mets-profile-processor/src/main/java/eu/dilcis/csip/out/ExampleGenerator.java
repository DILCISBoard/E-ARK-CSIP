package eu.dilcis.csip.out;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author  <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *          <a href="https://github.com/carlwilson">carlwilson AT github</a>
 *
 * @version 0.1
 * 
 * Created 17 Nov 2018:22:04:03
 */

public final class ExampleGenerator extends XmlFragmentGenerator {
	private final String exampleHead = "**Example:** "; //$NON-NLS-1$

	public ExampleGenerator() throws UnsupportedEncodingException {
		super();
	}

	public ExampleGenerator(final OutputHandler handler) {
		super(handler);
	}

	public void startExample(final String label) throws IOException {
		this.handler.nl();
		this.handler.emit(this.exampleHead + label);
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
