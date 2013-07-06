package net.sf.anathema.character.generic.framework.module;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.ICharacterTemplateRegistryCollection;
import net.sf.anathema.character.generic.framework.xml.CharacterTemplateParser;
import net.sf.anathema.character.generic.framework.xml.GenericCharacterTemplate;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.ResourceFile;

public abstract class CharacterModuleAdapter implements ICharacterModule {

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) throws InitializationException {
    // Nothing to do
  }

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    // Nothing to do
  }

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    // Nothing to do
  }

  protected final GenericCharacterTemplate registerParsedTemplate(ICharacterGenerics generics, ResourceFile resource) {
    ICharacterTemplateRegistryCollection characterTemplateRegistries = generics.getCharacterTemplateRegistries();
    new CharacterTemplateParser(generics.getCharacterTypes(), characterTemplateRegistries, generics.getCasteCollectionRegistry(), generics.getDataSet(ICharmCache.class));
    try {
      GenericCharacterTemplate template = characterTemplateRegistries.getCharacterTemplateRegistry().get(resource);
      generics.getTemplateRegistry().register(template);
      return template;
    } catch (PersistenceException e) {
      Logger.getLogger(CharacterModuleAdapter.class).error(resource.getFileName(), e);
      return null;
    }
  }
}