package net.sf.anathema.character.mutations;

import com.lowagie.text.pdf.BaseFont;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.framework.module.object.ICharacterModuleObjectMap;
import net.sf.anathema.character.mutations.model.MutationsModelFactory;
import net.sf.anathema.character.mutations.persistence.MutationPersisterFactory;
import net.sf.anathema.character.mutations.reporting.MutationsEncoder;
import net.sf.anathema.character.mutations.template.MutationsTemplate;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
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
    ICharacterModuleObjectMap moduleMap = generics.getModuleObjectMap();
    CharacterReportingModuleObject moduleObject = moduleMap.getModuleObject(CharacterReportingModule.class);
    PdfEncodingRegistry registry = moduleObject.getPdfEncodingRegistry();
    fillEncodingRegistry(resources, registry);
  }
  
  private void fillEncodingRegistry(IResources resources, PdfEncodingRegistry registry) {
	    BaseFont baseFont = registry.getBaseFont();
	    registry.setMutationsEncoder(new MutationsEncoder(baseFont, resources));
	  }
}