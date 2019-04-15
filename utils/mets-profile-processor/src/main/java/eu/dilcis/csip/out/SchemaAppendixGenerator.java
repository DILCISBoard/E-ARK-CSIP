package eu.dilcis.csip.out;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.dilcis.csip.profile.ControlledVocabulary;
import eu.dilcis.csip.profile.ExternalSchema;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *         <a href="https://github.com/carlwilson">carlwilson AT github</a>
 *
 * @version 0.1
 * 
 *          Created 20 Nov 2018:00:45:00
 */

public final class SchemaAppendixGenerator {

	final List<ExternalSchema> schema = new ArrayList<>();
	final List<ControlledVocabulary> vocabs = new ArrayList<>();
	static final Map<String, String> vocabLookup = new HashMap<>();

	/**
	 * 
	 */
	public SchemaAppendixGenerator() {
		super();
	}

	public boolean add(final ExternalSchema toAdd) {
		return this.schema.add(toAdd);
	}

	public boolean add(final ControlledVocabulary toAdd) {
		SchemaAppendixGenerator.vocabLookup.put(toAdd.id, toAdd.name);
		return this.vocabs.add(toAdd);
	}

	public static String getVocabName(final String id) {
		return SchemaAppendixGenerator.vocabLookup.get(id);
	}

	public void generateAppendix(final Path projRoot) throws IOException {
		OutputHandler handler = OutputHandler.toAppendix(projRoot, "schema"); //$NON-NLS-1$
		handler.emit(MarkdownFormatter.h2("External Schema"));
		handler.nl();
		for (ExternalSchema scheme : this.schema) {
			handler.nl();
			handler.emit(MarkdownFormatter.h3(scheme.name));
			handler.nl();
			handler.emit(headString("Location:", MarkdownFormatter.href(scheme.url.toString(), scheme.url.toString())));
			handler.nl();
			handler.emit(headString("Context:", scheme.context));
			handler.nl();
			handler.emit(headString("Note:", " "));
			handler.nl();
			for (String para : scheme.note) {
				handler.emit(para);
				handler.emit(MarkdownFormatter.htmlBr);
				handler.nl();
			}
			handler.nl();
		}
		handler.emit(MarkdownFormatter.h2("Controlled Vocabularies"));
		handler.nl();
		for (ControlledVocabulary vocab : this.vocabs) {
			handler.nl();
			handler.emit(MarkdownFormatter.anchor(vocab.id));
			handler.nl();
			handler.emit(MarkdownFormatter.h3(vocab.name));
			handler.nl();
			handler.emit(headString("Maintained By:", vocab.maintenanceAgency));
			handler.nl();
			handler.emit(headString("Location:", MarkdownFormatter.href(vocab.uri.toString(), vocab.uri.toString())));
			handler.nl();
			handler.emit(headString("Context:", vocab.context));
			handler.nl();
			handler.emit(headString("Description:", " "));
			handler.nl();
			for (String para : vocab.description) {
				handler.emit(para);
				handler.emit(MarkdownFormatter.htmlBr);
				handler.nl();
			}
			handler.nl();
		}
	}

	private static String headString(final String head, final String val) {
		StringBuffer buff = new StringBuffer(MarkdownFormatter.makeBold(head));
		buff.append(" ");
		buff.append(val);
		buff.append(MarkdownFormatter.htmlBr);
		return buff.toString();
	}
}
