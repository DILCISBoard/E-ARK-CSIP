package eu.dilcis.csip;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.security.AccessControlException;

/**
 * Parse and validate the command line arg array to a sensible options object.
 * 
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *         <a href="https://github.com/carlwilson">carlwilson AT github</a>
 *
 * @version 0.1
 * 
 * Created 23 Oct 2018:20:08:51
 */

public final class ProcessorOptions {
	final static String dirNotWritable = "Output directory: %s is not writeable."; //$NON-NLS-1$
	// private final static String dirNotFoundMess = "Could not find output directory: %s."; //$NON-NLS-1$
	// private final static String dirIsFileMess = "Output path %s must be a directory."; //$NON-NLS-1$
	final static String fileNotFoundMess = "Could not find Profile file: %s."; //$NON-NLS-1$
	final static String noFileArgMess = "No [FILE] argument passed to processor."; //$NON-NLS-1$
	final static String fileisDirMess = "Profile path %s must be a file not a directory."; //$NON-NLS-1$
	final static String usageOpt = "-h"; //$NON-NLS-1$
	final static String outputOpt = "-o"; //$NON-NLS-1$
	final static String period = "."; //$NON-NLS-1$
	public final Path outDir;
	public final boolean isUsage;
	public final Path profileFile;

	/**
	 * 
	 */
	private ProcessorOptions(final boolean isToCurrentDir,
			final boolean isUsage, final Path profileFile) {
		super();
		this.outDir = (isToCurrentDir) ? new File(period).toPath() : null;
		if (this.outDir != null && !this.outDir.toFile().canWrite()) {
			throw new AccessControlException(String.format(dirNotWritable,
					this.outDir.toAbsolutePath().toString()));
		}
		this.isUsage = isUsage;
		this.profileFile = profileFile;
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
	final static ProcessorOptions fromArgs(final String[] args)
			throws FileNotFoundException {
		// Path outDir = null; // Hold the output directory path, if any
		boolean isToCurrentDir = false; // Flag when output directory requested
		boolean isUsage = false; // Flag help requested
		Path profileFile = null;
		// Process args in a loop
		for (String arg : args) {
			// Help
			if (arg.equals(usageOpt)) {
				isUsage = true;
				continue;
			}
			// Output dir
			if (arg.equals(outputOpt)) {
				isToCurrentDir = true;
				continue;
			}
			// It's a file arg for the profile file
			File toTest = new File(arg);
			if (toTest.isFile()) {
				profileFile = toTest.toPath();
			} else {
				// METS Profile file isn't a file
				// If it exists it's a directory
				if (toTest.exists()) {
					throw new IllegalArgumentException(String.format(fileisDirMess, toTest.getAbsolutePath()));
				}
				throw new FileNotFoundException(
						String.format(fileNotFoundMess, toTest.getAbsolutePath()));
			}
		}
		// No profile file arg and no usage request is a bail
		if (!isUsage && profileFile == null) {
			throw new IllegalArgumentException(noFileArgMess);
		}
		// Return a shiny new ProcessorOptions instance
		return new ProcessorOptions(isToCurrentDir, isUsage, profileFile);
	}
}
