package eu.dilcis.csip.profile;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author  <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *          <a href="https://github.com/carlwilson">carlwilson AT github</a>
 *
 * @version 0.1
 * 
 * Created 20 Nov 2018:00:07:06
 */

public final class ControlledVocabulary {
	final static String empty = ""; //$NON-NLS-1$
	final static String defaultUriVal = "http://example.com"; //$NON-NLS-1$

	public final String id;
	public final String name;
	public final String maintenanceAgency;
	public final URI uri;
	public final String context;
	public final List<String> description;

	ControlledVocabulary(final String id, final String name, final String maintenanceAgency, final URI uri,
			final String context, final List<String> description) {
		super();
		this.id = id;
		this.name = name;
		this.maintenanceAgency = maintenanceAgency;
		this.uri = uri;
		this.context = context;
		this.description = Collections.unmodifiableList(description);
	}

	public static class Builder {
		private String ident = empty;
		private String nm = empty;
		private String mntnceAgncy = empty;
		private URI ri = URI.create(defaultUriVal);
		private String cntxt = empty;
		private List<String> desc = new ArrayList<>();

		public Builder() {
			super();
		}

		public Builder id(final String id) {
			this.ident = id;
			return this;
		}

		public Builder name(final String name) {
			this.nm = name;
			return this;
		}

		public Builder maintenanceAgency(final String agency) {
			this.mntnceAgncy = agency;
			return this;
		}

		public Builder uri(final URI uri) {
			this.ri = uri.normalize();
			return this;
		}

		public Builder uri(final String uri) {
			this.ri = URI.create(uri);
			return this;
		}

		public Builder context(final String context) {
			this.cntxt = context;
			return this;
		}

		public Builder description(final String description) {
			this.desc.add(description);
			return this;
		}

		public Builder descriptions(final List<String> descriptions) {
			this.desc = new ArrayList<>(descriptions);
			return this;
		}
		
		public ControlledVocabulary build( ) {
			return new ControlledVocabulary(this.ident, this.nm, this.mntnceAgncy, this.ri, this.cntxt, this.desc);
		}
	}
}
