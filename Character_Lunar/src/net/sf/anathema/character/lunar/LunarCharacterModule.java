package net.sf.anathema.character.lunar;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.CharacterTypeModule;
import net.sf.anathema.character.generic.impl.backgrounds.CharacterTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.backgrounds.TemplateTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.lunar.beastform.BeastformModelFactory;
import net.sf.anathema.character.lunar.beastform.BeastformPersisterFactory;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.BeastformViewFactory;
import net.sf.anathema.character.lunar.caste.LunarCaste;
import net.sf.anathema.character.lunar.heartsblood.HeartsBloodFactory;
import net.sf.anathema.character.lunar.heartsblood.HeartsBloodPersisterFactory;
import net.sf.anathema.character.lunar.heartsblood.HeartsBloodTemplate;
import net.sf.anathema.character.lunar.heartsblood.HeartsBloodViewFactory;
import net.sf.anathema.character.lunar.virtueflaw.LunarVirtueFlawModelFactory;
import net.sf.anathema.character.lunar.virtueflaw.LunarVirtueFlawPersisterFactory;
import net.sf.anathema.character.lunar.virtueflaw.LunarVirtueFlawTemplate;
import net.sf.anathema.character.lunar.virtueflaw.LunarVirtueFlawViewFactory;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.util.Identifier;

import java.util.HashMap;
import java.util.Map;

@CharacterModule
public class LunarCharacterModule extends CharacterTypeModule {

  public static final ICharacterType type = new LunarCharacterType();
  public static final String BACKGROUND_ID_HEARTS_BLOOD = "HeartsBlood";
  public static final String BACKGROUND_ID_REPUTATION = "Reputation";
  public static final String BACKGROUND_ID_SOLAR_BOND = "SolarBond";
  public static final String BACKGROUND_ID_TATTOO_ARTIFACT = "TattooArtifact";
  public static final String BACKGROUND_ID_TABOO = "Taboo";

  private static final TemplateType castelessType = new TemplateType(type, new Identifier("Casteless"));
  private static final TemplateType dreamsType = new TemplateType(type, new Identifier("Dreams"));
  private static final TemplateType dreamsEstablished = new TemplateType(type, new Identifier("DreamsEstablished"));
  private static final TemplateType dreamsInfluential = new TemplateType(type, new Identifier("DreamsInfluential"));
  private static final TemplateType dreamsLegendary = new TemplateType(type, new Identifier("DreamsLegendary"));
  private static final TemplateType silverpact = new TemplateType(type);

  private static final TemplateType[] dreams = {dreamsType, dreamsEstablished, dreamsInfluential, dreamsLegendary};
  private static final TemplateType[] allButCasteless = {silverpact, dreamsType, dreamsEstablished, dreamsInfluential, dreamsLegendary};

  public static final String BACKGROUND_ID_ARSENAL = "LunarDreamsArsenal";
  public static final String BACKGROUND_ID_COMMAND = "LunarDreamsCommand";
  public static final String BACKGROUND_ID_CONNECTIONS = "LunarDreamsConnections";
  public static final String BACKGROUND_ID_HENCHMEN = "LunarDreamsHenchmen";
  public static final String BACKGROUND_ID_PANOPLY = "LunarDreamsPanoply";
  public static final String BACKGROUND_ID_RETAINERS = "LunarDreamsRetainers";
  public static final String BACKGROUND_ID_SALARY = "LunarDreamsSalary";
  public static final String BACKGROUND_ID_SAVANT = "LunarDreamsSavant";
  public static final String BACKGROUND_ID_SIFU = "LunarDreamsSifu";
  public static final String BACKGROUND_ID_WEALTH = "LunarDreamsWealth";

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    Map<ITemplateType, ICasteType[]> templateMap = new HashMap<>();
    templateMap.put(castelessType, LunarCaste.getCastelessValues());
    templateMap.put(dreamsType, LunarCaste.getDreamsValues());
    CasteCollection casteCollection = new CasteCollection(LunarCaste.values(), templateMap);
    characterGenerics.getCasteCollectionRegistry().register(type, casteCollection);
  }

  @Override
  public void addBackgroundTemplates(ICharacterGenerics generics) {
    IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry = generics.getBackgroundRegistry();
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_HEARTS_BLOOD, type));
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_SOLAR_BOND, type));
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_REPUTATION, type));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_TATTOO_ARTIFACT, allButCasteless));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_TABOO, castelessType));

    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_ARSENAL, dreams));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_COMMAND, dreams));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_CONNECTIONS, dreams));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_HENCHMEN, dreams));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_PANOPLY, dreams));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_RETAINERS, dreams));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_SALARY, dreams));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_SAVANT, dreams));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_SIFU, dreams));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_WEALTH, dreams));
  }

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = characterGenerics.getAdditionalModelFactoryRegistry();
    IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry = characterGenerics.getAdditionalViewFactoryRegistry();
    IRegistry<String, IAdditionalPersisterFactory> persisterFactory = characterGenerics.getAdditonalPersisterFactoryRegistry();
    registerBeastform(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
    registerHeartsblood(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
    registerVirtueFlaw(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
  }

  private void registerVirtueFlaw(IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry,
                                  IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry,
                                  IRegistry<String, IAdditionalPersisterFactory> persisterFactory) {
    String templateId = LunarVirtueFlawTemplate.TEMPLATE_ID;
    additionalModelFactoryRegistry.register(templateId, new LunarVirtueFlawModelFactory());
    additionalViewFactoryRegistry.register(templateId, new LunarVirtueFlawViewFactory());
    persisterFactory.register(templateId, new LunarVirtueFlawPersisterFactory());

  }

  private void registerHeartsblood(IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry,
                                   IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry,
                                   IRegistry<String, IAdditionalPersisterFactory> persisterFactory) {
    String templateId = HeartsBloodTemplate.TEMPLATE_ID;
    additionalModelFactoryRegistry.register(templateId, new HeartsBloodFactory());
    additionalViewFactoryRegistry.register(templateId, new HeartsBloodViewFactory());
    persisterFactory.register(templateId, new HeartsBloodPersisterFactory());
  }

  private void registerBeastform(IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry,
                                 IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry,
                                 IRegistry<String, IAdditionalPersisterFactory> persisterFactory) {
    String templateId = BeastformTemplate.TEMPLATE_ID;
    additionalModelFactoryRegistry.register(templateId, new BeastformModelFactory());
    additionalViewFactoryRegistry.register(templateId, new BeastformViewFactory());
    persisterFactory.register(templateId, new BeastformPersisterFactory());
  }

  @Override
  protected ICharacterType getType() {
    return type;
  }
}