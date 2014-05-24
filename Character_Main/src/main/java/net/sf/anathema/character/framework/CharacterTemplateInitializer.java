package net.sf.anathema.character.framework;

import net.sf.anathema.character.framework.xml.CharacterTemplateParser;
import net.sf.anathema.character.framework.xml.GenericCharacterTemplate;
import net.sf.anathema.character.framework.xml.registry.CharacterTemplateRegistryCollection;
import net.sf.anathema.framework.environment.resources.ResourceFile;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.logging.Logger;

public class CharacterTemplateInitializer {

  private HeroEnvironment environment;

  public CharacterTemplateInitializer(HeroEnvironment environment) {
    this.environment = environment;
  }

  public void addCharacterTemplates() {
    CharacterTemplateResources templateResources = environment.getDataSet(CharacterTemplateResources.class);
    for (ResourceFile templateResource : templateResources) {
      registerParsedTemplate(environment, templateResource);
    }
  }

  private void registerParsedTemplate(HeroEnvironment environment, ResourceFile resource) {
    ICharacterTemplateRegistryCollection characterTemplateRegistries = createSubTemplateRegistries(environment);
    registerCharacterTemplateParser(environment, characterTemplateRegistries);
    initCharacterTemplates(environment, resource, characterTemplateRegistries);
  }

  private void registerCharacterTemplateParser(HeroEnvironment environment,
                                               ICharacterTemplateRegistryCollection characterTemplateRegistries) {
    new CharacterTemplateParser(environment.getCharacterTypes(), characterTemplateRegistries);
  }

  private ICharacterTemplateRegistryCollection createSubTemplateRegistries(HeroEnvironment environment) {
    ICharacterTemplateExtensionResourceCache templateExtensionCache = environment.getDataSet(
            ICharacterTemplateExtensionResourceCache.class);
    return new CharacterTemplateRegistryCollection(templateExtensionCache);
  }

  private void initCharacterTemplates(HeroEnvironment environment, ResourceFile resource,
                                      ICharacterTemplateRegistryCollection characterTemplateRegistries) {
    try {
      GenericCharacterTemplate template = characterTemplateRegistries.getCharacterTemplateRegistry().get(resource);
      environment.getTemplateRegistry().register(template);
    } catch (PersistenceException e) {
      Logger.getLogger(CharacterTemplateInitializer.class).error(resource.getFileName(), e);
    }
  }
}