package eu.dilcis.csip;

import java.io.File;
import java.io.FileNotFoundException;
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
	final static String fileNotFoundMessage = "Could not find file: %s.";
	final static String fileisDirectoryMessage = "File at %s is a directory NOT a file.";
	final boolean isToFile;
	final boolean isUsage;
	final List<File> toProcess;

	/**
	 * 
	 */
	private ProcessorOptions(final boolean isToFile, final boolean isUsage,
			List<File> toProcess) {
		this.isToFile = isToFile;
		this.isUsage = isUsage;
		this.toProcess = Collections.unmodifiableList(toProcess);
	}

	final static ProcessorOptions fromArgs(final String[] args)
			throws FileNotFoundException {
		List<File> toProcess = new ArrayList<>();
		boolean isToFile = false;
		boolean isUsage = false;
		for (String arg : args) {
			if (arg.equals("-h")) {
				isUsage = true;
			}
			if (arg.equals("-f")) {
				isToFile = true;
				continue;
			}
			File toTest = new File(arg);
			if (toTest.isFile()) {
				toProcess.add(toTest);
			} else {
				String message = toTest.exists() ? fileisDirectoryMessage
						: fileNotFoundMessage;
				throw new FileNotFoundException(
						String.format(message, toTest.getAbsolutePath()));
			}
		}
		return new ProcessorOptions(isToFile, isUsage, toProcess);
	}
}
