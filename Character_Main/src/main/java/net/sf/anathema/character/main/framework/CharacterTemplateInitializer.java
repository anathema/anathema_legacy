package net.sf.anathema.character.main.framework;

import net.sf.anathema.character.main.xml.CharacterTemplateParser;
import net.sf.anathema.character.main.xml.GenericCharacterTemplate;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.ResourceFile;

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
    ICharacterTemplateRegistryCollection characterTemplateRegistries = environment.getCharacterTemplateRegistries();
    new CharacterTemplateParser(environment.getCharacterTypes(), characterTemplateRegistries);
    try {
      GenericCharacterTemplate template = characterTemplateRegistries.getCharacterTemplateRegistry().get(resource);
      environment.getTemplateRegistry().register(template);
    } catch (PersistenceException e) {
      Logger.getLogger(CharacterTemplateInitializer.class).error(resource.getFileName(), e);
    }
  }
}