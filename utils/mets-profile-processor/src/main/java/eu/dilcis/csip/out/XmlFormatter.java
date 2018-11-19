package eu.dilcis.csip.out;

import java.util.Arrays;

import org.xml.sax.Attributes;

/**
 * @author  <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *          <a href="https://github.com/carlwilson">carlwilson AT github</a>
 *
 * @version 0.1
 * 
 * Created 17 Nov 2018:20:59:05
 */

enum XmlFormatter {
	INSTANCE;
	private static final String empty = ""; //$NON-NLS-1$
	private static final char spaceChar = ' ';
	private static final String space = new String(new char[] {spaceChar});
	private static final String attOpen = "=\""; //$NON-NLS-1$
	private static final String attClose = "\""; //$NON-NLS-1$
	private static final String eleStartOpen = "<"; //$NON-NLS-1$
	private static final String eleFinishOpen = "</"; //$NON-NLS-1$
	private static final String eleClose = ">"; //$NON-NLS-1$

	static String indent(final int indentCount, final int indentSpaces) {
		int indentSize = indentCount * indentSpaces;
		if (indentSize < 1)
			return empty;
		char[] chars = new char[indentSize];
		Arrays.fill(chars, ' ');
		return new String(chars);
	}

	static String eleStartTag(final String eleName, final Attributes attrs) {
		StringBuffer retVal = makeEleTag(eleName, false);
		if (attrs != null) {
			for (int i = 0; i < attrs.getLength(); i++) {
				String aName = attrs.getLocalName(i); // Attr name
				if (empty.equals(aName))
					aName = attrs.getQName(i);
				retVal.append(space);
				retVal.append(aName);
				retVal.append(attOpen);
				retVal.append(attrs.getValue(i));
				retVal.append(attClose);
			}
		}
		retVal.append(eleClose);
		return retVal.toString();
	}

	static String eleEndTag(final String eleName) {
		StringBuffer retVal = makeEleTag(eleName, true);
		retVal.append(eleClose);
		return retVal.toString();
	}

	private static StringBuffer makeEleTag(final String eleName, final boolean isEnd) {
		StringBuffer retVal = (isEnd) ? new StringBuffer(eleFinishOpen) : new StringBuffer(eleStartOpen);
		retVal.append(eleName);
		return retVal;
	}
}
