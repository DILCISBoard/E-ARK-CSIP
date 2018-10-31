/**
 * 
 */
package eu.dilcis.csip;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *         <a href="https://github.com/carlwilson">carlwilson AT github</a>
 *
 * @version 0.1
 *
 *          Created 23 Oct 2018:18:42:37
 */
@SuppressWarnings("static-method")
public class ProcessorOptionsTests {
	static final String usageOpt = "-h"; //$NON-NLS-1$
	static final String outputOpt = "-o"; //$NON-NLS-1$

	/**
	 * Test method for
	 * {@link eu.dilcis.csip.ProcessorOptions#fromArgs(java.lang.String[])}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void testIsUsage() throws FileNotFoundException {
		final String[] args = { usageOpt };
		ProcessorOptions opts = ProcessorOptions.fromArgs(args);
		assertTrue(opts.isUsage);
		assertFalse(opts.isToDir());
	}

	/**
	 * Test method for
	 * {@link eu.dilcis.csip.ProcessorOptions#fromArgs(java.lang.String[])}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void testIsToDirAndUsage() throws FileNotFoundException {
		final String[] args = { outputOpt, usageOpt };
		ProcessorOptions opts = ProcessorOptions.fromArgs(args);
		assertTrue(opts.isToDir());
		assertTrue(opts.isUsage);
	}

	/**
	 * Test method for
	 * {@link eu.dilcis.csip.ProcessorOptions#fromArgs(java.lang.String[])}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void testIsUsageAndToDir() throws FileNotFoundException {
		final String[] args = { usageOpt, outputOpt };
		ProcessorOptions opts = ProcessorOptions.fromArgs(args);
		assertTrue(opts.isToDir());
		assertTrue(opts.isUsage);
	}

	/**
	 * Test method for
	 * {@link eu.dilcis.csip.ProcessorOptions#fromArgs(java.lang.String[])}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testIsToDirNoFileArg() throws FileNotFoundException {
		final String[] args = { outputOpt };
		ProcessorOptions.fromArgs(args);
	}

	/**
	 * Test method for
	 * {@link eu.dilcis.csip.ProcessorOptions#fromArgs(java.lang.String[])}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNoFileArg() throws FileNotFoundException {
		final String[] args = {};
		ProcessorOptions.fromArgs(args);
	}

	/**
	 * Test method for
	 * {@link eu.dilcis.csip.ProcessorOptions#fromArgs(java.lang.String[])}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNoFileArgMess() throws FileNotFoundException {
		final String[] args = {};
		try {
			ProcessorOptions.fromArgs(args);
		} catch (IllegalArgumentException excep) {
			assertEquals(ProcessorOptions.noFileArgMess, excep.getMessage());
			throw excep;
		}
	}

	/**
	 * Test method for
	 * {@link eu.dilcis.csip.ProcessorOptions#fromArgs(java.lang.String[])}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test(expected = FileNotFoundException.class)
	public void testBadFileArg() throws FileNotFoundException {
		final String[] args = { "£%&*(" }; //$NON-NLS-1$
		ProcessorOptions.fromArgs(args);
	}

	/**
	 * Test method for
	 * {@link eu.dilcis.csip.ProcessorOptions#fromArgs(java.lang.String[])}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test(expected = FileNotFoundException.class)
	public void testBadFileArgMess() throws FileNotFoundException {
		final String unlikelyFileName = "couldNotBeFoundButQUitelong.with.extensions."; //$NON-NLS-1$
		final String[] args = { unlikelyFileName };
		try {
			ProcessorOptions.fromArgs(args);
		} catch (FileNotFoundException excep) {
			final String expected = String.format(
					ProcessorOptions.fileNotFoundMess,
					new File(unlikelyFileName).toPath().toAbsolutePath());
			assertEquals(expected, excep.getMessage());
			throw excep;
		}
	}

	/**
	 * Test method for
	 * {@link eu.dilcis.csip.ProcessorOptions#fromArgs(java.lang.String[])}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFileArgIsDir() throws FileNotFoundException {
		String tempDir = System.getProperty("java.io.tmpdir"); //$NON-NLS-1$
		final String[] args = { tempDir };
		ProcessorOptions.fromArgs(args);
	}
}
