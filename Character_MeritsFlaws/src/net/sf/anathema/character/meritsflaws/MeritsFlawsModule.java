package net.sf.anathema.character.meritsflaws;

import com.lowagie.text.pdf.BaseFont;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.framework.module.object.ICharacterModuleObjectMap;
import net.sf.anathema.character.meritsflaws.model.MeritsFlawsModelFactory;
import net.sf.anathema.character.meritsflaws.persistence.MeritsFlawsPersisterFactory;
import net.sf.anathema.character.meritsflaws.reporting.MeritsAndFlawsEncoder;
import net.sf.anathema.character.meritsflaws.template.MeritsFlawsTemplate;
import net.sf.anathema.character.meritsflaws.view.MeritsFlawsViewFactory;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.pdf.layout.simple.SimpleEncodingRegistry;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public class MeritsFlawsModule extends NullObjectCharacterModuleAdapter {

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = characterGenerics.getAdditionalModelFactoryRegistry();
    String templateId = MeritsFlawsTemplate.ID;
    additionalModelFactoryRegistry.register(templateId, new MeritsFlawsModelFactory());
    IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry = characterGenerics.getAdditionalViewFactoryRegistry();
    additionalViewFactoryRegistry.register(templateId, new MeritsFlawsViewFactory());
    IRegistry<String, IAdditionalPersisterFactory> persisterFactory = characterGenerics.getAdditonalPersisterFactoryRegistry();
    persisterFactory.register(templateId, new MeritsFlawsPersisterFactory());
    characterGenerics.getGlobalAdditionalTemplateRegistry().add(new MeritsFlawsTemplate());
  }
  
  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    ICharacterModuleObjectMap moduleMap = generics.getModuleObjectMap();
    CharacterReportingModuleObject moduleObject = moduleMap.getModuleObject(CharacterReportingModule.class);
    SimpleEncodingRegistry registry = moduleObject.getSimpleEncodingRegistry();
    fillEncodingRegistry(resources, registry);
  }
  
  private void fillEncodingRegistry(IResources resources, SimpleEncodingRegistry registry) {
	    BaseFont baseFont = registry.getBaseFont();
	    registry.setMeritsAndFlawsEncoder(new MeritsAndFlawsEncoder(baseFont, resources));
	  }
}
