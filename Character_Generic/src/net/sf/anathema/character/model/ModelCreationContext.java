package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.modeltemplate.TemplateLoader;
import net.sf.anathema.lib.util.Identified;

public interface ModelCreationContext {

  <T> T loadModelTemplate(Identified modelId, TemplateLoader<T> loader);
}
