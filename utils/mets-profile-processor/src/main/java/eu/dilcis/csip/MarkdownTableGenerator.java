package eu.dilcis.csip;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.SAXException;

class MarkdownTableGenerator {
	final static String tbleHead1 = "| ID | Name | Element/Attribute | Level | Description and usage | Cardinality |"; //$NON-NLS-1$
	final static String tbleHead2 = "| -- | ---- | ----------------- |-------| --------------------- | ----------- |"; //$NON-NLS-1$
	final static String cellDiv = "|"; //$NON-NLS-1$
	final static String spcdCellDiv = " |"; //$NON-NLS-1$
	final static String space = " "; //$NON-NLS-1$
	final static String anchorOpen = "<a name=\""; //$NON-NLS-1$
	final static String anchorClose = "></a>"; //$NON-NLS-1$
	final static String lineBreak = "<br/>"; //$NON-NLS-1$


	final List<Requirement> requirements = new ArrayList<>();
	final MarkdownTemplater.Section section;

	MarkdownTableGenerator(final MarkdownTemplater.Section section) {
		this.section = section;
	}

	boolean add(Requirement req) {
		return this.requirements.add(req);
	}

	void toTable(OutputHandler outHandler) throws SAXException, IOException {
		if (this.requirements.isEmpty()) return;
		outHandler.emit(tbleHead1);
		outHandler.nl();
		outHandler.emit(tbleHead2);
		outHandler.nl();
		for (Requirement req : this.requirements) {
			tableRow(outHandler, req);
		}
	}

	static void tableRow(OutputHandler outputHandler, final Requirement req) throws IOException {
		outputHandler.emit(cellDiv);
		outputHandler.emit(anchorCell(req.id.prefix + req.id.number));
		outputHandler.emit(cell(req.name));
		outputHandler.emit(cell(req.xPath));
		outputHandler.emit(cell(req.reqLevel));
		outputHandler.emit(cell(concatDescription(req.description)));
		outputHandler.emit(cell(req.cardinality));
		outputHandler.nl();
	}

	static String anchorCell(final String cellVal) {
		StringBuffer buff = new StringBuffer(anchorOpen);
		buff.append(cellVal);
		buff.append(anchorClose);
		buff.append(cellVal);
		return cell(buff.toString());
	}

	static String cell(final String cellVal) {
		StringBuffer buff = new StringBuffer(space);
		buff.append(cellVal);
		buff.append(spcdCellDiv);
		return buff.toString();
	}

	static String concatDescription(List<String> description) {
		if (description.isEmpty()) return space;
		StringBuffer buff = new StringBuffer(description.get(0));
		for (int i = 1; i < description.size(); i++) {
			buff.append(lineBreak);
			buff.append(description.get(i));
		}
		return buff.toString();
	}
}