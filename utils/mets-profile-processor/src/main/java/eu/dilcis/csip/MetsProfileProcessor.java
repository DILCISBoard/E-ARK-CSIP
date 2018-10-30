package eu.dilcis.csip;

import java.io.IOException;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *         <a href="https://github.com/carlwilson">carlwilson AT github</a>
 *
 * @version 0.1
 *
 *          Created 24 Oct 2018:06:37:19
 */

public final class MetsProfileProcessor {

	/**
	 *
	 */
	public MetsProfileProcessor() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		ProcessorOptions opts = ProcessorOptions.fromArgs(args);
		if (opts.profileFile == null) {
			usage();
			System.exit(1);
		}
		if (opts.isUsage) {
			usage();
			System.exit(0);
		}
		MetsProfileXmlHandler handler = new MetsProfileXmlHandler(opts);
		handler.processProfile();
	}

	private static void usage() {
		System.out.println("usage: mets-profile-proc [options] FILE");
		System.out.println("");
		System.out.println(
				"Checks E-ARK CSIP METS Profile.");
		System.out.println("");
		System.out.println("  -h prints this message.");
		System.out.println("  -o output requirements Markdown as files to current directory, default to STDOUT.");
//		System.out.println("  -d [DIRECTORY] output requirements Markdown as files in [DIRECTORY], default to STDOUT.");
//		System.out.println("  -f [FILE] output requirements Markdown to single [FILE], default to STDOUT.");
		System.out.println("  [FILE] is the METS Profile XML path.");
	}
}
