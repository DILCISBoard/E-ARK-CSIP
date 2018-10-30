package eu.dilcis.csip;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *         <a href="https://github.com/carlwilson">carlwilson AT github</a>
 *
 * @version 0.1
 * 
 *          Created 12 Jul 2018:00:08:51
 */

public final class ProcessorOptions {
	final static String dirNotWritable = "Output directory: %s is not writeable.";
	final static String dirNotFoundMess = "Could not find output directory: %s.";
	final static String dirIsFileMess = "Output path %s must be a directory.";
	final static String fileNotFoundMess = "Could not find Profile file: %s.";
	final static String fileisDirMess = "Profile path %s must be a file.";
	final Path outDir;
	final boolean isUsage;
	final List<File> toProcess;

	/**
	 * 
	 */
	private ProcessorOptions(final boolean isToCurrentDir, final boolean isUsage, List<File> toProcess) {
		this.outDir = (isToCurrentDir) ? new File(".").toPath() : null;
		if (this.outDir != null && !this.outDir.toFile().canWrite()) {
			throw new AccessControlException(String.format(dirNotWritable, this.outDir.toAbsolutePath().toString()));
		}
		this.isUsage = isUsage;
		this.toProcess = Collections.unmodifiableList(toProcess);
	}

	boolean isToDir() {
		return this.outDir != null;
	}

	/**
	 * Process the main program String parameter array and return a
	 * ProcessorOptions instance.
	 * 
	 * @param args
	 *            the String args parameter array passed to main
	 * @return a {@link ProcessorOptions} instance initialised using the passed
	 *         arguments
	 * @throws FileNotFoundException
	 */
	final static ProcessorOptions fromArgs(final String[] args) throws FileNotFoundException {
		List<File> toProcess = new ArrayList<>();
		// Path outDir = null; // Hold the output directory path, if any
		boolean isToCurrentDir = false; // Flag when output directory requested
		boolean isUsage = false; // Flag help requested
		// Process args in a loop
		for (String arg : args) {
			// Help
			if (arg.equals("-h")) {
				isUsage = true;
				continue;
			}
			// Output dir
			if (arg.equals("-o")) {
				isToCurrentDir = true;
				continue;
			}
			// It's a file arg of some kind
			File toTest = new File(arg);
			// Are we processing an output dir
			// if (isToFile && outDir == null) {
			// if (!toTest.isDirectory()) {
			// // Output directory isn't a directory
			// String message = toTest.exists() ? dirIsFileMess :
			// dirNotFoundMess;
			// throw new FileNotFoundException(String.format(message,
			// toTest.getAbsolutePath()));
			// }
			// outDir = toTest.toPath();
			// // Or treat as a profile file
			// } else if (toTest.isFile()) {
			if (toTest.isFile()) {
				toProcess.add(toTest);
			} else {
				// METS Profile file isn't a file
				String message = toTest.exists() ? fileisDirMess : fileNotFoundMess;
				throw new FileNotFoundException(String.format(message, toTest.getAbsolutePath()));
			}
		}
		// Return a shiny new ProcessorOptions instance
		return new ProcessorOptions(isToCurrentDir, isUsage, toProcess);
	}
}
