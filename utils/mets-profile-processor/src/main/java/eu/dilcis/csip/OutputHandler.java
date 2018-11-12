package eu.dilcis.csip;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;

import eu.dilcis.csip.MarkdownTemplater.Section;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *         <a href="https://github.com/carlwilson">carlwilson AT github</a>
 *
 * @version 0.1
 * 
 *          Created 25 Oct 2018:09:00:06
 */

public final class OutputHandler {
	private static final String lineSepPropName = "line.separator"; //$NON-NLS-1$
	private static final String utf8 = "UTF8"; //$NON-NLS-1$
	private static final String reqsMd = "requirements.md"; //$NON-NLS-1$
	private StringBuffer textBuffer = null;
	private final Writer out;

	/**
	 * Default constructor, output to STDOUT
	 */
	private OutputHandler() throws UnsupportedEncodingException {
		super();
		this.out = new OutputStreamWriter(System.out, utf8);
	}

	/**
	 * Constructor to output to a file
	 */
	private OutputHandler(final File outFile) throws IOException {
		super();
		File output = new File(outFile.getParentFile(), outFile.getName());
		this.out = new FileWriter(output);
	}

	// ===========================================================
	// Utility Methods ...
	// ===========================================================

	// Display text accumulated in the character buffer
	public void echoText() throws IOException {
		if (this.textBuffer == null)
			return;
		emit(this.textBuffer.toString());
		this.textBuffer = null;
	}

	// Wrap I/O exceptions in SAX exceptions, to
	// suit handler signature requirements
	public void emit(String s) throws IOException {
		this.out.write(s);
		this.out.flush();
	}

	// Start a new line
	public void nl() throws IOException {
		String lineEnd = System.getProperty(lineSepPropName);
		this.out.write(lineEnd);
		this.out.flush();
	}

	/**
	 * Get the value of the text buffer
	 */
	public String getBufferValue() {
		return (this.textBuffer == null) ? null : this.textBuffer.toString();
	}

	/**
	 * Get the value of the text buffer and erase the buffer contents
	 */
	public String voidBuffer() {
		String retVal = this.getBufferValue();
		this.textBuffer = null;
		return retVal;
	}

	/**
	 * Add text to the buffer / start a new bufer if necessary
	 */
	public void addToBuffer(final String toAdd) {
		if (this.textBuffer == null) {
			this.textBuffer = new StringBuffer(toAdd);
		} else {
			this.textBuffer.append(toAdd);
		}
	}

	static OutputHandler toStdOut() throws UnsupportedEncodingException {
		return new OutputHandler();
	}

	static OutputHandler toSectionRequirements(Path metsReqRoot, Section sect)
			throws IOException {
		return new OutputHandler(
				metsReqRoot.resolve(Paths.get(sect.sectName, reqsMd)).toFile());
	}
}
