package eu.dilcis.csip;

import java.io.IOException;

import org.xml.sax.SAXException;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *         <a href="https://github.com/carlwilson">carlwilson AT github</a>
 *
 * @version 0.1
 *
 *          Created 22 Jun 2018:00:27:22
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
	public static void main(String[] args) throws SAXException, IOException {
		ProcessorOptions opts = ProcessorOptions.fromArgs(args);
		if (opts.toProcess.isEmpty()) {
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
		System.out.println("usage: mets-profile-processor [options] FILE");
		System.out.println("");
		System.out.println(
				"Checks E-ARK CSIP METS Profile.");
		System.out.println("");
		System.out.println("  -h prints this message.");
		System.out.println("  -o [DIRECTORY] output requirements Markdown as files in [DIRECTORY], default to STDOUT.");
		System.out.println("  [FILE] is the METS Profile XML path.");
	}
}
