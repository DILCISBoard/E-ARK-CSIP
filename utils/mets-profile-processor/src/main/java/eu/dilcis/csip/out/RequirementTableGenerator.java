package eu.dilcis.csip.out;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import eu.dilcis.csip.profile.Requirement;

public class RequirementTableGenerator {
	final static String[] tableHeadings = { "ID", "Name & Location", //$NON-NLS-1$ //$NON-NLS-2$
			"Description & usage", "Cardinality & Level" };  //$NON-NLS-1$ //$NON-NLS-2$

	final List<Requirement> requirements = new ArrayList<>();

	public RequirementTableGenerator() {
		super();
	}

	public int getRequirmentCount() {
		return this.requirements.size();
	}

	public boolean add(Requirement req) {
		return this.requirements.add(req);
	}

	public void toTable(final OutputHandler outHandler) throws IOException {
		if (this.requirements.isEmpty())
			return;
		tableHeading(outHandler);
		for (Requirement req : this.requirements) {
			tableRow(outHandler, req);
		}
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
		outputHandler.emit(
				MarkdownFormatter.cell(MarkdownFormatter.nameString(req)));
		outputHandler.emit(MarkdownFormatter
				.cell(MarkdownFormatter.concatDescription(req.description)));
		outputHandler.emit(
				MarkdownFormatter.cell(MarkdownFormatter.cardString(req)));
		outputHandler.nl();
	}
}
