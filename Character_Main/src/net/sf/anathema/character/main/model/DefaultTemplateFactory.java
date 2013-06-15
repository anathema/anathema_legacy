package net.sf.anathema.character.main.model;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.modeltemplate.CharacterModelTemplateCache;
import net.sf.anathema.character.generic.modeltemplate.TemplateLoader;
import net.sf.anathema.character.model.TemplateFactory;
import net.sf.anathema.lib.util.Identifier;

public class DefaultTemplateFactory implements TemplateFactory {

  private final CharacterModelTemplateCache templateCache;

  public DefaultTemplateFactory(ICharacterGenerics generics) {
    this.templateCache = generics.getDataSet(CharacterModelTemplateCache.class);
  }

  public <T> T loadModelTemplate(Identifier templateId, TemplateLoader<T> loader) {
    return templateCache.loadTemplate(templateId, loader);
  }
}
