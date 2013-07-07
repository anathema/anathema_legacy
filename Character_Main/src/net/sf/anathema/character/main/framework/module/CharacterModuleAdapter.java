package net.sf.anathema.character.main.framework.module;

import net.sf.anathema.character.main.framework.HeroEnvironment;
import net.sf.anathema.character.main.framework.ICharacterTemplateRegistryCollection;
import net.sf.anathema.character.main.xml.CharacterTemplateParser;
import net.sf.anathema.character.main.xml.GenericCharacterTemplate;
import net.sf.anathema.character.main.magic.persistence.ICharmCache;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.ResourceFile;

public abstract class CharacterModuleAdapter implements ICharacterModule {

  @Override
  public void addAdditionalTemplateData(HeroEnvironment characterGenerics) throws InitializationException {
    // Nothing to do
  }

  @Override
  public void addCharacterTemplates(HeroEnvironment characterGenerics) {
    // Nothing to do
  }

  @Override
  public void registerCommonData(HeroEnvironment characterGenerics) {
    // Nothing to do
  }

  protected final GenericCharacterTemplate registerParsedTemplate(HeroEnvironment generics, ResourceFile resource) {
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