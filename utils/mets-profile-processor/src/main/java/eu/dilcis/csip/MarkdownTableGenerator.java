package eu.dilcis.csip;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.SAXException;

class MarkdownTableGenerator {
	static enum Section {
		ROOT("metsRootElement", "1", "root element", "mets"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		HEADER("metsHdr", "2", "header", "metsHdr"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		DMD_SEC("dmdSec", "3", "descriptive metadata", "dmdSec"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		AMD_SEC("amdSec", "4", "administrative metadata", "amdSec"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		FILE_SEC("fileSec", "5", "file section", "fileSec"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		STRUCT_MAP("structMap", "6", "structural map", "structMap"),  //$NON-NLS-1$ //$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$
		STRUCT_LINK("structLink", "7", "structural link", "structLink"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

		final String eleName;
		final String sectName;
		final String sectSubHeadNum;
		final String metsEleName;

		private Section(final String eleName, final String sectSubHeadNum,
				final String sectName, final String metsEleName) {
			this.eleName = eleName;
			this.sectName = sectName;
			this.sectSubHeadNum = sectSubHeadNum;
			this.metsEleName = metsEleName;
		}

		public String getFullHeader() {
			return String.format(MetsProfileXmlHandler.sectHeadTemplate, this.sectSubHeadNum,
					this.sectName, this.metsEleName);
		}

		public String getDirName() {
			return this.metsEleName;
		}

		public static boolean isSection(final String eleName) {
			for (Section sect : Section.values()) {
				if (sect.eleName.equals(eleName)) {
					return true;
				}
			}
			return false;
		}

		public static Section fromEleName(final String eleName) {
			for (Section sect : Section.values()) {
				if (sect.eleName.equals(eleName)) {
					return sect;
				}
			}
			return null;
		}
	}

	final static String tbleHead1 = "| ID | Name | Element/Attribute | Level | Description and usage | Cardinality |"; //$NON-NLS-1$
	final static String tbleHead2 = "| -- | ---- | ----------------- |-------| --------------------- | ----------- |"; //$NON-NLS-1$
	final static String cellDiv = "|"; //$NON-NLS-1$
	final static String spcdCellDiv = " |"; //$NON-NLS-1$
	final static String space = " "; //$NON-NLS-1$
	final static String anchorOpen = "<a name=\""; //$NON-NLS-1$
	final static String anchorClose = "></a>"; //$NON-NLS-1$
	final static String lineBreak = "<br/>"; //$NON-NLS-1$


	final List<Requirement> requirements = new ArrayList<>();
	final MarkdownTableGenerator.Section section;

	MarkdownTableGenerator(final MarkdownTableGenerator.Section section) {
		this.section = section;
	}

	boolean add(Requirement req) {
		return this.requirements.add(req);
	}

	void toTable(OutputHandler outHandler) throws SAXException {
		if (this.requirements.isEmpty()) return;
		outHandler.emit(tbleHead1);
		outHandler.nl();
		outHandler.emit(tbleHead2);
		outHandler.nl();
		for (Requirement req : this.requirements) {
			tableRow(outHandler, req);
		}
	}

	static void tableRow(OutputHandler outputHandler, final Requirement req) throws SAXException {
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