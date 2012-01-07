package net.sf.anathema.character.intimacies;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.intimacies.reporting.ExtendedIntimaciesEncoderFactory;
import net.sf.anathema.character.intimacies.reporting.SimpleIntimaciesEncoderFactory;
import net.sf.anathema.character.intimacies.reporting.content.ExtendedIntimaciesContent;
import net.sf.anathema.character.intimacies.reporting.content.ExtendedIntimaciesContentFactory;
import net.sf.anathema.character.intimacies.reporting.content.SimpleIntimaciesContent;
import net.sf.anathema.character.intimacies.reporting.content.SimpleIntimaciesContentFactory;
import net.sf.anathema.character.intimacies.template.IntimaciesTemplate;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.pdf.content.ReportContentRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

@CharacterModule
public class IntimaciesModule extends NullObjectCharacterModuleAdapter {

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = characterGenerics.getAdditionalModelFactoryRegistry();
    String templateId = IntimaciesTemplate.ID;
    additionalModelFactoryRegistry.register(templateId, new IntimaciesModelFactory());
    IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry = characterGenerics.getAdditionalViewFactoryRegistry();
    additionalViewFactoryRegistry.register(templateId, new IntimaciesViewFactory());
    IRegistry<String, IAdditionalPersisterFactory> persisterFactory = characterGenerics.getAdditonalPersisterFactoryRegistry();
    persisterFactory.register(templateId, new IntimaciesPersisterFactory());
    characterGenerics.getGlobalAdditionalTemplateRegistry().add(new IntimaciesTemplate());
  }

  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    CharacterReportingModuleObject moduleObject = generics.getModuleObjectMap().getModuleObject(CharacterReportingModule.class);
    registerReportContent(moduleObject.getContentRegistry(), resources);
    registerEncoders(moduleObject.getEncoderRegistry());
  }

  private void registerEncoders(EncoderRegistry registry) {
    registry.add(new SimpleIntimaciesEncoderFactory());
    registry.add(new ExtendedIntimaciesEncoderFactory());
  }

  private void registerReportContent(ReportContentRegistry registry, IResources resources) {
    registry.addFactory(ExtendedIntimaciesContent.class, new ExtendedIntimaciesContentFactory(resources));
    registry.addFactory(SimpleIntimaciesContent.class, new SimpleIntimaciesContentFactory(resources));
  }
}
