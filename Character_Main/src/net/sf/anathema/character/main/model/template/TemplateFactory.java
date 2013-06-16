package net.sf.anathema.character.main.model.template;

import net.sf.anathema.lib.util.Identifier;

public interface TemplateFactory {

  <T> T loadModelTemplate(Identifier modelId, TemplateLoader<T> loader);
}
