/**
 * 
 */
package eu.dilcis.csip;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * @author cfw
 *
 */
public class ProcessorOptionsTests {

	/**
	 * Test method for {@link eu.dilcis.csip.ProcessorOptions#isToDir()}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void testIsToDir() throws FileNotFoundException {
		final String[] args = { "-o" };
		ProcessorOptions opts = ProcessorOptions.fromArgs(args);
		assertTrue(opts.isToDir());
		assertFalse(opts.isUsage);
	}

	/**
	 * Test method for {@link eu.dilcis.csip.ProcessorOptions#isToDir()}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void testIsUsage() throws FileNotFoundException {
		final String[] args = { "-h" };
		ProcessorOptions opts = ProcessorOptions.fromArgs(args);
		assertTrue(opts.isUsage);
		assertFalse(opts.isToDir());
	}

	/**
	 * Test method for {@link eu.dilcis.csip.ProcessorOptions#isToDir()}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void testIsToDirAndUsage() throws FileNotFoundException {
		final String[] args = { "-o", "-h" };
		ProcessorOptions opts = ProcessorOptions.fromArgs(args);
		assertTrue(opts.isToDir());
		assertTrue(opts.isUsage);
	}

	/**
	 * Test method for {@link eu.dilcis.csip.ProcessorOptions#isToDir()}.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void testIsUsageAndToDir() throws FileNotFoundException {
		final String[] args = { "-h", "-o" };
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
	public void testFromArgs() throws FileNotFoundException {
		final String[] args = {};
		ProcessorOptions opts = ProcessorOptions.fromArgs(args);
		assertFalse(opts.isToDir());
		assertFalse(opts.isUsage);
	}

}
