package net.sf.anathema.character.intimacies;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.CharacterModuleAdapter;
import net.sf.anathema.character.intimacies.template.IntimaciesTemplate;
import net.sf.anathema.lib.registry.IRegistry;

@CharacterModule
public class IntimaciesModule extends CharacterModuleAdapter {

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = characterGenerics.getAdditionalModelFactoryRegistry();
    String templateId = IntimaciesTemplate.ID;
    additionalModelFactoryRegistry.register(templateId, new IntimaciesModelFactory());
    IRegistry<String, IAdditionalPersisterFactory> persisterFactory = characterGenerics.getAdditonalPersisterFactoryRegistry();
    persisterFactory.register(templateId, new IntimaciesPersisterFactory());
    characterGenerics.getGlobalAdditionalTemplateRegistry().add(new IntimaciesTemplate());
  }
}