package net.sf.anathema.character.generic.framework.module;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.ICharacterTemplateRegistryCollection;
import net.sf.anathema.character.generic.framework.module.object.ICharacterModuleObject;
import net.sf.anathema.character.generic.framework.xml.CharacterTemplateParser;
import net.sf.anathema.character.generic.framework.xml.additional.IAdditionalTemplateParser;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public abstract class CharacterModuleAdapter<M extends ICharacterModuleObject> implements ICharacterModule<M> {

  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) {
    // Nothing to do
  }

  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    // Nothing to do
  }

  public void addBackgroundTemplates(ICharacterGenerics generics) {
    // Nothing to do
  }

  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    // Nothing to do
  }

  public void registerCommonData(ICharacterGenerics characterGenerics) {
    // Nothing to do
  }

  protected final void registerParsedTemplate(ICharacterGenerics generics, String templateId) {
    ICharacterTemplateRegistryCollection characterTemplateRegistries = generics.getCharacterTemplateRegistries();
    IRegistry<String, IAdditionalTemplateParser> additionalTemplateParserRegistry = generics.getAdditionalTemplateParserRegistry();
    new CharacterTemplateParser(
        characterTemplateRegistries,
        generics.getCasteCollectionRegistry(),
        generics.getCharmProvider(),
        additionalTemplateParserRegistry);
    try {
      generics.getTemplateRegistry().register(
          characterTemplateRegistries.getCharacterTemplateRegistry().get(templateId));
    }
    catch (PersistenceException e) {
      Logger.getLogger(CharacterModuleAdapter.class).error(e);
    }
  }
}