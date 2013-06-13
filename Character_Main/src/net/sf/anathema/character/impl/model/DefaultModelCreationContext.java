package net.sf.anathema.character.impl.model;

import net.sf.anathema.character.generic.modeltemplate.CharacterModelTemplateCache;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.modeltemplate.TemplateLoader;
import net.sf.anathema.character.model.ModelCreationContext;
import net.sf.anathema.lib.util.Identified;

public class DefaultModelCreationContext implements ModelCreationContext {

  private final CharacterModelTemplateCache templateCache;

  public DefaultModelCreationContext(ICharacterGenerics generics) {
    this.templateCache = generics.getDataSet(CharacterModelTemplateCache.class);
  }

  public <T> T loadModelTemplate(Identified templateId, TemplateLoader<T> loader) {
    return templateCache.loadTemplate(templateId, loader);
  }
}
