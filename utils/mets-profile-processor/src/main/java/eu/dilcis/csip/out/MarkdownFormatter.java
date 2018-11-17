package eu.dilcis.csip.out;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *         <a href="https://github.com/carlwilson">carlwilson AT github</a>
 *
 * @version 0.1
 * 
 *          Created 17 Nov 2018:16:17:30
 */

enum MarkdownFormatter {
	INSTANCE;
	// Markdown Tags
	private final static String empty = ""; //$NON-NLS-1$
	private final static String space = " "; //$NON-NLS-1$
	private final static String cellDiv = "|"; //$NON-NLS-1$
	private final static String cellDivCls = space + cellDiv; // $NON-NLS-1$
	private final static String cellDivOpen = cellDiv + space; // $NON-NLS-1$
	private final static char hyphen = '-';
	private final static String mdBoldMarker = "**"; //$NON-NLS-1$
	private final static String mdConsoleMarker = "`"; //$NON-NLS-1$
	final static String mdInlineMarker = "```"; //$NON-NLS-1$
	private final static String xml = "xml"; //$NON-NLS-1$
	final static String mdInlineXml = mdInlineMarker + xml;

	// HTML Tags
	private final static String anchorOpen = "<a name=\""; //$NON-NLS-1$
	private final static String anchorClose = "\"></a>"; //$NON-NLS-1$
	final static String htmlBr = "<br/>"; //$NON-NLS-1$

	static String makeBold(final String toBold) {
		if (toBold == null || toBold.isEmpty())
			return empty;
		StringBuffer buff = new StringBuffer(mdBoldMarker);
		buff.append(toBold);
		buff.append(mdBoldMarker);
		return buff.toString();
	}

	static String makeConsole(final String toConsole) {
		if (toConsole == null || toConsole.isEmpty())
			return empty;
		StringBuffer buff = new StringBuffer(mdConsoleMarker);
		buff.append(toConsole);
		buff.append(mdConsoleMarker);
		return buff.toString();
	}

	static String anchorCell(final String cellVal) {
		return anchorCell(cellVal, false);
	}

	static String anchorCell(final String cellVal, final boolean isFirst) {
		StringBuffer buff = new StringBuffer(anchorOpen);
		buff.append(cellVal);
		buff.append(anchorClose);
		buff.append(makeBold(cellVal));
		return cell(buff.toString(), isFirst);
	}

	static String cell(final String cellVal) {
		return cell(cellVal, false);
	}

	static String cell(final String cellVal, boolean isFirst) {
		StringBuffer buff = (isFirst) ? new StringBuffer(cellDivOpen) : new StringBuffer(space);
		buff.append(cellVal);
		buff.append(cellDivCls);
		return buff.toString();
	}

	static String makeHeadingLines(final String heading) {
		int len = (heading == null || heading.isEmpty()) ? 1 : heading.length();
		char[] chars = new char[len];
		Arrays.fill(chars, hyphen);
		return new String(chars);
	}

	static String concatDescription(List<String> description) {
		if (description.isEmpty())
			return space;
		StringBuffer buff = new StringBuffer(description.get(0));
		for (int i = 1; i < description.size(); i++) {
			buff.append(htmlBr);
			buff.append(description.get(i));
		}
		return buff.toString();
	}
}

