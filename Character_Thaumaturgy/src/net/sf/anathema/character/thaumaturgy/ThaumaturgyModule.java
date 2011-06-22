package net.sf.anathema.character.thaumaturgy;

import com.lowagie.text.pdf.BaseFont;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.framework.module.object.ICharacterModuleObjectMap;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.thaumaturgy.model.ThaumaturgyModelFactory;
import net.sf.anathema.character.thaumaturgy.persistence.ThaumaturgyPersisterFactory;
import net.sf.anathema.character.thaumaturgy.reporting.ThaumaturgyEncoder;
import net.sf.anathema.character.thaumaturgy.template.ThaumaturgyTemplate;
import net.sf.anathema.character.thaumaturgy.view.ThaumaturgyViewFactory;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

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
  
  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    ICharacterModuleObjectMap moduleMap = generics.getModuleObjectMap();
    CharacterReportingModuleObject moduleObject = moduleMap.getModuleObject(CharacterReportingModule.class);
    PdfEncodingRegistry registry = moduleObject.getPdfEncodingRegistry();
    fillEncodingRegistry(resources, registry);
  }
  
  private void fillEncodingRegistry(IResources resources, PdfEncodingRegistry registry) {
	    BaseFont baseFont = registry.getBaseFont();
	    registry.setThaumaturgyEncoder(new ThaumaturgyEncoder(resources, baseFont));
	  }
}