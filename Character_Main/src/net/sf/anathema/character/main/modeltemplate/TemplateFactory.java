package net.sf.anathema.character.main.modeltemplate;

import net.sf.anathema.character.main.modeltemplate.TemplateLoader;
import net.sf.anathema.lib.util.Identifier;

public interface TemplateFactory {

  <T> T loadModelTemplate(Identifier modelId, TemplateLoader<T> loader);
}
