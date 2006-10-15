package net.sf.anathema.character.linguistics;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.linguistics.template.LinguisticsTemplate;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public class LinguisticsModule extends NullObjectCharacterModuleAdapter {

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = characterGenerics.getAdditionalModelFactoryRegistry();
    String templateId = LinguisticsTemplate.ID;
    additionalModelFactoryRegistry.register(templateId, new LinguisticsModelFactory());
    IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry = characterGenerics.getAdditionalViewFactoryRegistry();
    additionalViewFactoryRegistry.register(templateId, new LinguisticsViewFactory());
    IRegistry<String, IAdditionalPersisterFactory> persisterFactory = characterGenerics.getAdditonalPersisterFactoryRegistry();
    persisterFactory.register(templateId, new LinguisticsPersisterFactory());
    characterGenerics.getGlobalAdditionalTemplateRegistry().add(new LinguisticsTemplate());
  }

  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    //included in PdfSecondPageEncoder
  }
}