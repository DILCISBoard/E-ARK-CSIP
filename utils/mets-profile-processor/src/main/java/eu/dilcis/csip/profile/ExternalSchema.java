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
 * Created 19 Nov 2018:23:24:31
 */

public final class ExternalSchema {
	final static String empty = ""; //$NON-NLS-1$
	final static String defaultUrlVal = "http://example.com"; //$NON-NLS-1$
	public final String name;
	public final URI url;
	public final String context;
	public final List<String> note;

	ExternalSchema(final String name, final URI url, final String context,
			final List<String> note) {
		super();
		this.name = name;
		this.url = url;
		this.context = context;
		this.note = Collections.unmodifiableList(note);
	}

	public static class Builder {
		private String nm = empty;
		private URI rl = URI.create(defaultUrlVal);
		private String cntxt = empty;
		private List<String> nt = new ArrayList<>();

		public Builder() {
			super();
		}

		public Builder name(final String name) {
			this.nm = name;
			return this;
		}

		public Builder url(final URI url) {
			this.rl = url.normalize();
			return this;
		}

		public Builder url(final String url) {
			this.rl = URI.create(url);
			return this;
		}

		public Builder context(final String context) {
			this.cntxt = context;
			return this;
		}

		public Builder note(final String note) {
			this.nt.add(note);
			return this;
		}

		public Builder notes(final List<String> notes) {
			this.nt = new ArrayList<>(notes);
			return this;
		}
		
		public ExternalSchema build( ) {
			return new ExternalSchema(this.nm, this.rl, this.cntxt, this.nt);
		}
	}
}
