package eu.dilcis.csip.out;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.xml.sax.Attributes;

import eu.dilcis.csip.Section;

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
	private static final String examplesMd = "examples.md"; //$NON-NLS-1$
	private final Writer out;
	private int indent = 0;

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

	// Wrap I/O exceptions in SAX exceptions, to
	// suit handler signature requirements
	public void emit(String s) throws IOException {
		if (s == null)
			return;
		this.out.write(s);
		this.out.flush();
	}

	// Start a new line
	public void nl() throws IOException {
		String lineEnd = System.getProperty(lineSepPropName);
		this.out.write(lineEnd);
		this.out.flush();
	}

	public void outputEleStart(final String eleName, final Attributes attrs) throws IOException {
		this.nl();
		this.indent();
		this.emit(eleStartTag(eleName, attrs));
		this.indent++;
	}

	public void outputEleEnd(final String eleName, final String eleVal) throws IOException {
		this.indent--;
		if (eleVal == null || eleVal.trim().isEmpty()) {
			this.nl();
			this.indent();
		} else {
			this.emit(eleVal.trim());
		}
		this.emit(eleEndTag(eleName));
	}

	private void indent() throws IOException {
		if (this.indent == 0)
			return;
		char[] chars = new char[this.indent * 2];
		Arrays.fill(chars, ' ');
		this.emit(new String(chars));
	}

	private static String eleStartTag(final String eleName, final Attributes attrs) {
		StringBuffer retVal = makeEleTag(eleName, false);
		if (attrs != null) {
			for (int i = 0; i < attrs.getLength(); i++) {
				String aName = attrs.getLocalName(i); // Attr name
				if ("".equals(aName))
					aName = attrs.getQName(i);
				retVal.append(" ");
				retVal.append(aName);
				retVal.append("=\"");
				retVal.append(attrs.getValue(i));
				retVal.append("\"");
			}
		}
		retVal.append(">");
		return retVal.toString();
	}

	private static String eleEndTag(final String eleName) {
		StringBuffer retVal = makeEleTag(eleName, true);
		retVal.append(">");
		return retVal.toString();
	}

	private static StringBuffer makeEleTag(final String eleName, final boolean isEnd) {
		StringBuffer retVal = (isEnd) ? new StringBuffer("</") : new StringBuffer("<");
		retVal.append(eleName);
		return retVal;
	}

	public static OutputHandler toStdOut() throws UnsupportedEncodingException {
		return new OutputHandler();
	}

	public static OutputHandler toSectionRequirements(Path metsReqRoot, Section sect) throws IOException {
		return new OutputHandler(metsReqRoot.resolve(Paths.get(sect.sectName, reqsMd)).toFile());
	}

	public static OutputHandler toSectionExamples(Path metsReqRoot, Section sect) throws IOException {
		return new OutputHandler(metsReqRoot.resolve(Paths.get(sect.sectName, examplesMd)).toFile());
	}
}
