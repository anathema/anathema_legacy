package net.sf.anathema.character.mutations;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.mutations.model.MutationsModelFactory;
import net.sf.anathema.character.mutations.persistence.MutationPersisterFactory;
import net.sf.anathema.character.mutations.reporting.MutationContent;
import net.sf.anathema.character.mutations.reporting.MutationContentFactory;
import net.sf.anathema.character.mutations.reporting.MutationsEncoder;
import net.sf.anathema.character.mutations.template.MutationsTemplate;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.pdf.content.ReportContentRegistry;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.layout.simple.SimpleEncodingRegistry;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public class MutationsModule extends NullObjectCharacterModuleAdapter {

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = characterGenerics.getAdditionalModelFactoryRegistry();
    String templateId = MutationsTemplate.ID;
    additionalModelFactoryRegistry.register(templateId, new MutationsModelFactory());
    IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry = characterGenerics.getAdditionalViewFactoryRegistry();
    additionalViewFactoryRegistry.register(templateId, new MutationsViewFactory());
    IRegistry<String, IAdditionalPersisterFactory> persisterFactory = characterGenerics.getAdditonalPersisterFactoryRegistry();
    persisterFactory.register(templateId, new MutationPersisterFactory());
    characterGenerics.getGlobalAdditionalTemplateRegistry().add(new MutationsTemplate());
  }

  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    CharacterReportingModuleObject moduleObject = generics.getModuleObjectMap().getModuleObject(CharacterReportingModule.class);
    registerReportContent(moduleObject.getReportContentRegistry(), resources);
    registerSimpleEncoders(moduleObject.getSimpleEncodingRegistry(), resources);
    registerExtendedEncoders(moduleObject.getExtendedEncodingRegistry(), resources);
  }

  private void registerReportContent(ReportContentRegistry registry, IResources resources) {
    registry.addFactory(MutationContent.class, new MutationContentFactory(resources));
  }

  private void registerSimpleEncoders(SimpleEncodingRegistry registry, IResources resources) {
    registry.setMutationsEncoder(new MutationsEncoder());
  }

  private void registerExtendedEncoders(ExtendedEncodingRegistry registry, IResources resources) {
    registry.setMutationsEncoder(new MutationsEncoder());
  }
}
