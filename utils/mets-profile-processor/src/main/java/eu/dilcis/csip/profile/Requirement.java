package eu.dilcis.csip.profile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Requirement {
	public final static Requirement DEFAULT = new Requirement();
	public final Requirement.RequirementId id;
	public final String name;
	public final String reqLevel;
	final String relMat;
	public final List<String> description;
	final List<String> examples;
	public final String xPath;
	public final String cardinality;
	static final String cardTerm = "Cardinality"; //$NON-NLS-1$
	static final String xPathTerm = "METS XPath"; //$NON-NLS-1$
	static final String requirementEle = "requirement"; //$NON-NLS-1$
	static final String empty = ""; //$NON-NLS-1$

	private Requirement() {
		this(Requirement.RequirementId.DEFAULT_ID, Requirement.empty,
				Requirement.empty, Requirement.empty, Collections.emptyList(),
				Collections.emptyList(), Requirement.empty, Requirement.empty);
	}

	Requirement(final Requirement.RequirementId id, final String name,
			final String reqLevel, final String relMat,
			final List<String> description, final List<String> examples,
			final String xPath, final String cardinality) {
		super();
		this.id = id;
		this.name = name;
		this.reqLevel = reqLevel;
		this.relMat = relMat.trim();
		this.description = description;
		this.examples = examples;
		this.xPath = xPath;
		this.cardinality = cardinality;
	}

	public String[] relatedMatter() {
		if (this.relMat == null || this.relMat.isEmpty())
			return new String[] {};
		return (this.relMat.contains(" ")) ? this.relMat.split(" ")
				: new String[] { this.relMat };
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Requirement [id=" + this.id + ", name=" + this.name //$NON-NLS-1$ //$NON-NLS-2$
				+ ", relMat=" + this.relMat + ", reqLevel=" + this.reqLevel //$NON-NLS-1$ //$NON-NLS-2$
				+ ", description=" + this.description + ", examples=" //$NON-NLS-1$ //$NON-NLS-2$
				+ this.examples + ", xPath=" + this.xPath + ", cardinality=" //$NON-NLS-1$ //$NON-NLS-2$
				+ this.cardinality + "]"; //$NON-NLS-1$
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.cardinality == null) ? 0
				: this.cardinality.hashCode());
		result = prime * result + ((this.description == null) ? 0
				: this.description.hashCode());
		result = prime * result
				+ ((this.examples == null) ? 0 : this.examples.hashCode());
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		result = prime * result
				+ ((this.name == null) ? 0 : this.name.hashCode());
		result = prime * result
				+ ((this.relMat == null) ? 0 : this.relMat.hashCode());
		result = prime * result
				+ ((this.reqLevel == null) ? 0 : this.reqLevel.hashCode());
		result = prime * result
				+ ((this.xPath == null) ? 0 : this.xPath.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Requirement)) {
			return false;
		}
		Requirement other = (Requirement) obj;
		if (this.cardinality == null) {
			if (other.cardinality != null) {
				return false;
			}
		} else if (!this.cardinality.equals(other.cardinality)) {
			return false;
		}
		if (this.description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!this.description.equals(other.description)) {
			return false;
		}
		if (this.examples == null) {
			if (other.examples != null) {
				return false;
			}
		} else if (!this.examples.equals(other.examples)) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.relMat == null) {
			if (other.relMat != null) {
				return false;
			}
		} else if (!this.relMat.equals(other.relMat)) {
			return false;
		}
		if (this.reqLevel == null) {
			if (other.reqLevel != null) {
				return false;
			}
		} else if (!this.reqLevel.equals(other.reqLevel)) {
			return false;
		}
		if (this.xPath == null) {
			if (other.xPath != null) {
				return false;
			}
		} else if (!this.xPath.equals(other.xPath)) {
			return false;
		}
		return true;
	}

	static boolean isRequirementEle(final String eleName) {
		return Requirement.requirementEle.equals(eleName);
	}

	static class Builder {
		private Requirement.RequirementId id;
		private String name;
		private String reqLevel;
		private String relMat;
		private List<String> description;
		private List<String> examples;
		private String xPath;
		private String cardinality;

		public Builder() {
			this(Requirement.DEFAULT);
		}

		public Builder(Requirement.Builder builder) {
			this(builder.build());
		}

		public Builder(Requirement req) {
			super();
			this.id = req.id;
			this.name = req.name;
			this.reqLevel = req.reqLevel;
			this.relMat = req.relMat;
			this.description = new ArrayList<>(req.description);
			this.examples = new ArrayList<>(req.examples);
			this.xPath = req.xPath;
			this.cardinality = req.cardinality;
		}

		public Requirement.Builder processAttr(final String attName,
				final String attValue) {
			switch (attName) {
			case "ID": //$NON-NLS-1$
				this.id(Requirement.RequirementId.fromIdString(attValue));
				break;

			case "REQLEVEL": //$NON-NLS-1$
				this.reqLevel(attValue);
				break;

			case "RELATEDMAT": //$NON-NLS-1$
				this.relMat(attValue);
				break;

			case "EXAMPLES": //$NON-NLS-1$
				for (String example : attValue.split(" ")) { //$NON-NLS-1$
					this.example(example);
				}
				break;

			default:
				break;
			}
			return this;
		}

		/**
		 * @param id
		 *            the id to set
		 */
		public Requirement.Builder id(Requirement.RequirementId iD) {
			this.id = iD;
			return this;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public Requirement.Builder name(String nm) {
			this.name = nm;
			return this;
		}

		/**
		 * @param reqLevel
		 *            the reqLevel to set
		 */
		public Requirement.Builder relMat(String rlMt) {
			this.relMat = rlMt;
			return this;
		}

		/**
		 * @param reqLevel
		 *            the reqLevel to set
		 */
		public Requirement.Builder reqLevel(String rqLvl) {
			this.reqLevel = rqLvl;
			return this;
		}

		/**
		 * @param description
		 *            the description to set
		 */
		public Requirement.Builder description(String dscrptn) {
			if (dscrptn == null || dscrptn.isEmpty())
				return this;
			this.description.add(dscrptn);
			return this;
		}

		/**
		 * @param description
		 *            the description to set
		 */
		public Requirement.Builder descriptions(List<String> dscrptns) {
			this.description = new ArrayList<>(dscrptns);
			return this;
		}

		/**
		 * @param examples
		 *            the examples to set
		 */
		public Requirement.Builder examples(List<String> xmpls) {
			this.examples = new ArrayList<>(xmpls);
			return this;
		}

		/**
		 * @param example
		 *            the example to add
		 */
		public Requirement.Builder example(String xmpl) {
			this.examples.add(xmpl);
			return this;
		}

		/**
		 * @param xPath
		 *            the xPath to set
		 */
		public Requirement.Builder defPair(String term, final String def) {
			switch (term) {
			case Requirement.xPathTerm:
				return this.xPath(def);
			case Requirement.cardTerm:
				return this.cardinality(def);
			default:
				break;
			}
			return this;
		}

		/**
		 * @param xPath
		 *            the xPath to set
		 */
		public Requirement.Builder xPath(String xPth) {
			this.xPath = xPth;
			return this;
		}

		/**
		 * @param cardinality
		 *            the cardinality to set
		 */
		public Requirement.Builder cardinality(String crdnlty) {
			this.cardinality = crdnlty;
			return this;
		}

		public Requirement build() {
			return new Requirement(this.id, this.name, this.reqLevel,
					this.relMat, this.description, this.examples, this.xPath,
					this.cardinality);
		}
	}

	public static class RequirementId implements Comparable<RequirementId> {
		final static String defPrefix = "PREF"; //$NON-NLS-1$
		final static int defNumber = -1;
		public final static RequirementId DEFAULT_ID = new RequirementId();
		public final String prefix;
		public final int number;

		private RequirementId() {
			this(defPrefix, defNumber);
		}

		private RequirementId(final String prefix, final int number) {
			super();
			this.prefix = prefix;
			this.number = number;
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "RequirementId [prefix=" + this.prefix + ", number=" //$NON-NLS-1$ //$NON-NLS-2$
					+ this.number + "]"; //$NON-NLS-1$
		}

		@Override
		public int compareTo(RequirementId other) {
			if (other.prefix.equals(this.prefix)) {
				return (this.number < other.number) ? -1
						: (this.number == other.number) ? 0 : -1;
			}
			return this.prefix.compareTo(other.prefix);
		}

		/**
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + this.number;
			result = prime * result
					+ ((this.prefix == null) ? 0 : this.prefix.hashCode());
			return result;
		}

		/**
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof RequirementId)) {
				return false;
			}
			RequirementId other = (RequirementId) obj;
			if (this.number != other.number) {
				return false;
			}
			if (this.prefix == null) {
				if (other.prefix != null) {
					return false;
				}
			} else if (!this.prefix.equals(other.prefix)) {
				return false;
			}
			return true;
		}

		static RequirementId fromIdString(final String idString) {
			StringBuffer prefixBuff = new StringBuffer();
			StringBuffer numBuff = new StringBuffer();
			for (int i = 0; i < idString.length(); i++) {
				char c = idString.charAt(i);
				if (Character.isDigit(c)) {
					numBuff.append(c);
				} else {
					prefixBuff.append(c);
				}
			}
			return new RequirementId(prefixBuff.toString(),
					Integer.parseInt(numBuff.toString()));
		}
	}
}