package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.modeltemplate.TemplateLoader;
import net.sf.anathema.lib.util.Identifier;

public interface TemplateFactory {

  <T> T loadModelTemplate(Identifier modelId, TemplateLoader<T> loader);
}
