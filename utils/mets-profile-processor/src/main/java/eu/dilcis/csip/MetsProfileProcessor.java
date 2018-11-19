package eu.dilcis.csip;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import org.xml.sax.SAXException;

import eu.dilcis.csip.profile.MetsProfileXmlHandler;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *         <a href="https://github.com/carlwilson">carlwilson AT github</a>
 *
 * @version 0.1
 *
 * Created 24 Oct 2018:06:37:19
 */

public final class MetsProfileProcessor {

	private MetsProfileProcessor() {
		throw new IllegalStateException();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ProcessorOptions opts = parseOpts(args);
		if (opts.isUsage) {
			usage(System.out);
			System.exit(0);
		}
		try {
			MetsProfileXmlHandler handler = new MetsProfileXmlHandler(opts);
			handler.processProfile();
		} catch (SAXException | IOException excep) {
			excep.printStackTrace();
		}
	}

	private static ProcessorOptions parseOpts(final String[] args) {
		ProcessorOptions opts = null;
		try {
			opts = ProcessorOptions.fromArgs(args);
		} catch (IllegalArgumentException | FileNotFoundException excep) {
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
