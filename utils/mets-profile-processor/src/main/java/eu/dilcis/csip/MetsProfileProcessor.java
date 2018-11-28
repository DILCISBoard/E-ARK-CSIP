package eu.dilcis.csip;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import org.xml.sax.SAXException;

import eu.dilcis.csip.profile.MetsProfileXmlHandler;

/**
 * Main class and start of CLI programming. Passes args to ProcessorOptions for
 * parsing and then invokes the SAXParser to process the XML file.
 * 
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *         <a href="https://github.com/carlwilson">carlwilson AT github</a>
 *
 * @version 0.1
 *
 *          Created 24 Oct 2018:06:37:19
 */

public final class MetsProfileProcessor {

	private MetsProfileProcessor() {
		throw new IllegalStateException();
	}

	/**
	 * Main method, controls top level flow from command line.
	 * @param args command line arg array
	 */
	public static void main(String[] args) throws SAXException, IOException {
		// Parse the command line options
		ProcessorOptions opts = parseOpts(args);
		if (opts.isUsage) {
			// If help flag print usage message and quit
			usage(System.out);
			System.exit(0);
		}
		try {
			// Create new SAX Parser handler initialised with opts and process
			MetsProfileXmlHandler handler = new MetsProfileXmlHandler(opts);
			handler.processProfile();
		} catch (SAXException | IOException excep) {
			// Basic for now, print the stack trace and trhow it
			excep.printStackTrace();
			throw excep;
		}
	}

	private static ProcessorOptions parseOpts(final String[] args) {
		ProcessorOptions opts = null;
		try {
			// Create new opts object from args
			opts = ProcessorOptions.fromArgs(args);
		} catch (IllegalArgumentException | FileNotFoundException excep) {
			// Something rotten in Denmark with passed parameters
			System.err.println(excep.getMessage());
			System.err.println();
			usage(System.err);
			System.exit(1);
		}
		return opts;
	}

	private static void usage(final PrintStream out) {
		out.println("usage: mets-profile-proc [options] FILE"); //$NON-NLS-1$
		out.println(""); //$NON-NLS-1$
		out.println("Checks E-ARK CSIP METS Profile."); //$NON-NLS-1$
		out.println("  -h prints this message."); //$NON-NLS-1$
		out.println(
				"  -o output requirements Markdown as files to current directory, default to STDOUT."); //$NON-NLS-1$
		// out.println(" -d [DIRECTORY] output requirements Markdown as files in
		// [DIRECTORY], default to STDOUT.");
		// out.println(" -f [FILE] output requirements Markdown to single
		// [FILE], default to STDOUT.");
		out.println("  [FILE] is the METS Profile XML path."); //$NON-NLS-1$
	}
}
