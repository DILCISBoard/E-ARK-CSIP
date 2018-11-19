package eu.dilcis.csip.out;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;

import eu.dilcis.csip.profile.Section;

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
	private static final String lineEnd = System.getProperty(lineSepPropName);
	private static final String utf8 = "UTF8"; //$NON-NLS-1$
	private static final String mdExt = ".md"; //$NON-NLS-1$
	private static final String reqsMd = "requirements" + mdExt; //$NON-NLS-1$
	private static final String examplesMd = "examples" + mdExt; //$NON-NLS-1$
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
		this.out.write(lineEnd);
		this.out.flush();
	}

	public static OutputHandler toStdOut() throws UnsupportedEncodingException {
		return new OutputHandler();
	}

	public static OutputHandler toSectionRequirements(Path projRoot,
			Section sect) throws IOException {
		return new OutputHandler(getSectionPath(projRoot, sect.sectName)
				.resolve(Paths.get(reqsMd)).toFile());
	}

	public static OutputHandler toSectionExamples(Path projRoot, Section sect)
			throws IOException {
		return new OutputHandler(getSectionPath(projRoot, sect.sectName)
				.resolve(Paths.get(examplesMd)).toFile());
	}

	public static OutputHandler toAppendix(Path metsReqRoot,
			final String appndxName) throws IOException {
		return new OutputHandler(metsReqRoot.resolve(Paths.get("specification",
				"appendices", appndxName, appndxName + mdExt)).toFile());
	}

	private static Path getSectionPath(final Path projRoot,
			final String sectName) {
		Path toReqRoot = Paths.get("specification", "implementation", //$NON-NLS-1$ //$NON-NLS-2$
				"metadata", "mets", sectName); //$NON-NLS-1$ //$NON-NLS-2$
		return projRoot.resolve(toReqRoot);
	}

}
