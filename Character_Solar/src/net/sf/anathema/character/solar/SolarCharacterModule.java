package net.sf.anathema.character.solar;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalInitializer;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.CharacterTypeModule;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.solar.caste.SolarCaste;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawInitializer;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawModelFactory;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawPersisterFactory;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawTemplate;
import net.sf.anathema.lib.registry.IRegistry;

@CharacterModule
public class SolarCharacterModule extends CharacterTypeModule {

  public static final ICharacterType type = new SolarCharacterType();
  @SuppressWarnings("unused")
  private static final TemplateType solarTemplateType = new TemplateType(type);

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    characterGenerics.getCasteCollectionRegistry().register(type, new CasteCollection(SolarCaste.values()));
  }

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = characterGenerics.getAdditionalModelFactoryRegistry();
    String templateId = SolarVirtueFlawTemplate.ID;
    additionalModelFactoryRegistry.register(templateId, new SolarVirtueFlawModelFactory());
    IRegistry<String, IAdditionalInitializer> additionalViewFactoryRegistry = characterGenerics.getAdditionalInitializerRegistry();
    additionalViewFactoryRegistry.register(templateId, new SolarVirtueFlawInitializer());
    IRegistry<String, IAdditionalPersisterFactory> persisterFactory = characterGenerics.getAdditonalPersisterFactoryRegistry();
    persisterFactory.register(templateId, new SolarVirtueFlawPersisterFactory());
  }

  @Override
  protected ICharacterType getType() {
	  return type;
  }
}
