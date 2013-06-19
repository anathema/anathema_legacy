package net.sf.anathema.hero.template;

import net.sf.anathema.lib.util.Identifier;

public interface TemplateFactory {

  <T> T loadModelTemplate(Identifier modelId, TemplateLoader<T> loader);
}
