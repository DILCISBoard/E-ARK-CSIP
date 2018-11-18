package eu.dilcis.csip.profile;

public enum Section {
	ROOT("metsRootElement", "1", "mets-root", "mets"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	HEADER("metsHdr", "2", "metshdr", "metsHdr"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	DMD_SEC("dmdSec", "3", "dmdsec", "dmdSec"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	AMD_SEC("amdSec", "4", "amdsec", "amdSec"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	FILE_SEC("fileSec", "5", "filesec", "fileSec"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	STRUCT_MAP("structMap", "6", "structmap", "structMap");  //$NON-NLS-1$ //$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$
	// STRUCT_LINK("structLink", "7", "structural link", "structLink"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

	public final String eleName;
	public final String sectName;
	public final String sectSubHeadNum;
	public final String metsEleName;

	private Section(final String eleName, final String sectSubHeadNum,
			final String sectName, final String metsEleName) {
		this.eleName = eleName;
		this.sectName = sectName;
		this.sectSubHeadNum = sectSubHeadNum;
		this.metsEleName = metsEleName;
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