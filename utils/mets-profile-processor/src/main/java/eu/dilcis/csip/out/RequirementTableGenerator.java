package eu.dilcis.csip.out;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import eu.dilcis.csip.profile.Requirement;

public class RequirementTableGenerator {
	final static String[] tableHeadings = { "ID", "Name & Location", //$NON-NLS-1$ //$NON-NLS-2$
			"Description & usage", "Cardinality & Level" };  //$NON-NLS-1$ //$NON-NLS-2$

	final List<Requirement> requirements = new ArrayList<>();

	private RequirementTableGenerator() {
		super();
	}

	public int getRequirmentCount() {
		return this.requirements.size();
	}

	public boolean add(Requirement req) {
		return this.requirements.add(req);
	}

	public void toTable(final OutputHandler outHandler) throws IOException {
		this.toTable(outHandler, true);
	}

	public void toTable(final OutputHandler outHandler, boolean addHeader)
			throws IOException {
		if (this.requirements.isEmpty())
			return;
		if (addHeader)
			tableHeading(outHandler);
		for (Requirement req : this.requirements) {
			tableRow(outHandler, req);
		}
	}

	public static RequirementTableGenerator instance() {
		return new RequirementTableGenerator();
	}

	static void tableHeading(final OutputHandler outHandler)
			throws IOException {
		boolean isFirst = true;
		StringBuffer headingLines = new StringBuffer();
		for (String heading : tableHeadings) {
			outHandler.emit(MarkdownFormatter.cell(heading, isFirst));
			headingLines.append(MarkdownFormatter.cell(
					MarkdownFormatter.makeHeadingLines(heading), isFirst));
			isFirst = false;
		}
		outHandler.nl();
		outHandler.emit(headingLines.toString());
		outHandler.nl();
	}

	static void tableRow(OutputHandler outputHandler, final Requirement req)
			throws IOException {
		outputHandler.emit(MarkdownFormatter
				.anchorCell(req.id.prefix + req.id.number, true));
		outputHandler.emit(MarkdownFormatter.cell(nameString(req)));
		StringBuffer desc = new StringBuffer(
				MarkdownFormatter.concatDescription(req.description));
		desc = relatedMatter(desc, req.relatedMatter());
		outputHandler.emit(MarkdownFormatter.cell(desc.toString()));
		outputHandler.emit(MarkdownFormatter.cell(cardString(req)));
		outputHandler.nl();
	}

	static String cardString(final Requirement req) {
		return boldHeadPair(req.cardinality, req.reqLevel);
	}

	static String nameString(final Requirement req) {
		return boldHeadPair(req.name, MarkdownFormatter.makeConsole(req.xPath));
	}

	static String boldHeadPair(final String head, final String secondLine) {
		StringBuffer buff = new StringBuffer(MarkdownFormatter.makeBold(head));
		buff.append(MarkdownFormatter.htmlBr);
		buff.append(secondLine);
		return buff.toString();
	}

	static StringBuffer relatedMatter(StringBuffer buff, String[] ids) {
		if (ids == null || ids.length == 0)
			return buff;
		StringBuffer relMattBuff = new StringBuffer();
		relMattBuff.append(MarkdownFormatter.htmlBr);
		relMattBuff.append(MarkdownFormatter.makeBold("See also:")); //$NON-NLS-1$
		String prefix = relMattBuff.toString();
		relMattBuff = new StringBuffer();
		for (String id : ids) {
			String vocabName =  SchemaAppendixGenerator.getVocabName(id);
			if (vocabName != null) {
				buff.append(prefix);
				buff.append(MarkdownFormatter.href(relMattHref(id), vocabName));
				prefix = ", ";
			}
		}
		return buff;
	}
	
	private static String relMattHref(final String id) {
		return "#" + id;
	}
}
