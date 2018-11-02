package eu.dilcis.csip;

/**
	 * @author  <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
	 *          <a href="https://github.com/carlwilson">carlwilson AT github</a>
	 *
	 * @version 0.1
	 * 
	 * Created 2 Nov 2018:02:13:01
	 */

final class MarkdownTemplater {

	static final String tempStartFinish = "@@DONTDELETE@@"; //$NON-NLS-1$
	static final String tempTableMrk = "REQUIREMENTS_TABLE"; //$NON-NLS-1$
	static final String tempCmmnt = "Template marker for page generation from profile."; //$NON-NLS-1$

	/**
	 * 
	 */
	public MarkdownTemplater() {
		// TODO Auto-generated constructor stub
	}

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
}