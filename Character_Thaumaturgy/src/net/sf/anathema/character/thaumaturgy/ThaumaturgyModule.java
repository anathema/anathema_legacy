package net.sf.anathema.character.thaumaturgy;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.thaumaturgy.model.ThaumaturgyModelFactory;
import net.sf.anathema.character.thaumaturgy.persistence.ThaumaturgyPersisterFactory;
import net.sf.anathema.character.thaumaturgy.template.ThaumaturgyTemplate;
import net.sf.anathema.character.thaumaturgy.view.ThaumaturgyViewFactory;
import net.sf.anathema.lib.registry.IRegistry;

public class ThaumaturgyModule extends NullObjectCharacterModuleAdapter {

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = characterGenerics.getAdditionalModelFactoryRegistry();
    String templateId = ThaumaturgyTemplate.ID;
    additionalModelFactoryRegistry.register(templateId, new ThaumaturgyModelFactory());
    IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry = characterGenerics.getAdditionalViewFactoryRegistry();
    additionalViewFactoryRegistry.register(templateId, new ThaumaturgyViewFactory());
    IRegistry<String, IAdditionalPersisterFactory> persisterFactory = characterGenerics.getAdditonalPersisterFactoryRegistry();
    persisterFactory.register(templateId, new ThaumaturgyPersisterFactory());
    characterGenerics.getGlobalAdditionalTemplateRegistry().add(new ThaumaturgyTemplate());
  }
}