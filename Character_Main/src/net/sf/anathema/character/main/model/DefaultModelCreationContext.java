package net.sf.anathema.character.main.model;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.change.ChangeFlavor;
import net.sf.anathema.character.generic.modeltemplate.CharacterModelTemplateCache;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.modeltemplate.TemplateLoader;
import net.sf.anathema.character.model.ModelCreationContext;
import net.sf.anathema.lib.util.Identified;

public class DefaultModelCreationContext implements ModelCreationContext {

  private final CharacterModelTemplateCache templateCache;
  private ChangeAnnouncer announcer;

  public DefaultModelCreationContext(ICharacterGenerics generics, ChangeAnnouncer announcer) {
    this.announcer = announcer;
    this.templateCache = generics.getDataSet(CharacterModelTemplateCache.class);
  }

  public <T> T loadModelTemplate(Identified templateId, TemplateLoader<T> loader) {
    return templateCache.loadTemplate(templateId, loader);
  }

  @Override
  public void announceChangeOf(ChangeFlavor flavor) {
    announcer.announceChangeOf(flavor);
  }
}
