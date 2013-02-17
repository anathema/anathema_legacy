package net.sf.anathema.character.solar;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.CharacterTypeModule;
import net.sf.anathema.character.generic.impl.backgrounds.TemplateTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.generic.type.SolarCharacterType;
import net.sf.anathema.character.solar.caste.SolarCaste;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawModelFactory;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawPersisterFactory;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawTemplate;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawViewFactory;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.util.Identificate;

@CharacterModule
public class SolarCharacterModule extends CharacterTypeModule {

  public static final ICharacterType type = new SolarCharacterType();
  @SuppressWarnings("unused")
  private static final TemplateType solarTemplateType = new TemplateType(type);
  private static final TemplateType dreamsSolarTemplateType = new TemplateType(type, new Identificate("Dreams")); //$NON-NLS-1$
  private static final TemplateType dreamsSolarEstablished = new TemplateType(type, new Identificate("DreamsEstablished")); //$NON-NLS-1$
  private static final TemplateType dreamsSolarInfluential = new TemplateType(type, new Identificate("DreamsInfluential")); //$NON-NLS-1$
  private static final TemplateType dreamsSolarLegendary = new TemplateType(type, new Identificate("DreamsLegendary")); //$NON-NLS-1$

  private static final TemplateType[] dreams = {dreamsSolarTemplateType, dreamsSolarEstablished, dreamsSolarInfluential, dreamsSolarLegendary};

  public static final String BACKGROUND_ID_ARSENAL = "SolarDreamsArsenal"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_COMMAND = "SolarDreamsCommand"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_CONNECTIONS = "SolarDreamsConnections"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_HENCHMEN = "SolarDreamsHenchmen"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_PANOPLY = "SolarDreamsPanoply"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_REPUTATION = "SolarDreamsReputation"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_RETAINERS = "SolarDreamsRetainers"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_SALARY = "SolarDreamsSalary"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_SAVANT = "SolarDreamsSavant"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_SIFU = "SolarDreamsSifu"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_WEALTH = "SolarDreamsWealth"; //$NON-NLS-1$

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    characterGenerics.getAdditionalTemplateParserRegistry().register(SolarVirtueFlawTemplate.ID, new SolarVirtueFlawParser());
    characterGenerics.getCasteCollectionRegistry().register(type, new CasteCollection(SolarCaste.values()));
  }

  @Override
  public void addBackgroundTemplates(ICharacterGenerics generics) {
    IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry = generics.getBackgroundRegistry();
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_ARSENAL, dreams));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_COMMAND, dreams));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_CONNECTIONS, dreams));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_HENCHMEN, dreams));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_PANOPLY, dreams));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_REPUTATION, dreams));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_RETAINERS, dreams));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_SALARY, dreams));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_SAVANT, dreams));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_SIFU, dreams));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_WEALTH, dreams));
  }

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = characterGenerics.getAdditionalModelFactoryRegistry();
    String templateId = SolarVirtueFlawTemplate.ID;
    additionalModelFactoryRegistry.register(templateId, new SolarVirtueFlawModelFactory());
    IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry = characterGenerics.getAdditionalViewFactoryRegistry();
    additionalViewFactoryRegistry.register(templateId, new SolarVirtueFlawViewFactory());
    IRegistry<String, IAdditionalPersisterFactory> persisterFactory = characterGenerics.getAdditonalPersisterFactoryRegistry();
    persisterFactory.register(templateId, new SolarVirtueFlawPersisterFactory());
  }

  @Override
  protected ICharacterType getType() {
	  return type;
  }
}
