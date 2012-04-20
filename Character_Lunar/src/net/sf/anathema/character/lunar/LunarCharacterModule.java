package net.sf.anathema.character.lunar;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.impl.backgrounds.CharacterTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.backgrounds.TemplateTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
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
import net.sf.anathema.lib.util.Identificate;

import java.util.HashMap;
import java.util.Map;

import static net.sf.anathema.character.generic.type.CharacterType.LUNAR;

@CharacterModule
public class LunarCharacterModule extends NullObjectCharacterModuleAdapter {

  public static final String BACKGROUND_ID_HEARTS_BLOOD = "HeartsBlood"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_REPUTATION = "Reputation"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_SOLAR_BOND = "SolarBond"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_TATTOO_ARTIFACT = "TattooArtifact"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_TABOO = "Taboo"; //$NON-NLS-1$

  private static final TemplateType castelessType = new TemplateType(LUNAR,
          new Identificate("Casteless")); //$NON-NLS-1$
  private static final TemplateType dreamsType = new TemplateType(LUNAR, new Identificate("Dreams")); //$NON-NLS-1$
  private static final TemplateType dreamsEstablished = new TemplateType(LUNAR,
          new Identificate("DreamsEstablished")); //$NON-NLS-1$
  private static final TemplateType dreamsInfluential = new TemplateType(LUNAR,
          new Identificate("DreamsInfluential")); //$NON-NLS-1$
  private static final TemplateType dreamsLegendary = new TemplateType(LUNAR,
          new Identificate("DreamsLegendary")); //$NON-NLS-1$
  private static final TemplateType silverpact = new TemplateType(LUNAR); //$NON-NLS-1$

  private static final TemplateType[] dreams = {dreamsType, dreamsEstablished, dreamsInfluential, dreamsLegendary};
  private static final TemplateType[] allButCasteless = {silverpact, dreamsType, dreamsEstablished, dreamsInfluential, dreamsLegendary};

  public static final String BACKGROUND_ID_ARSENAL = "LunarDreamsArsenal"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_COMMAND = "LunarDreamsCommand"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_CONNECTIONS = "LunarDreamsConnections"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_HENCHMEN = "LunarDreamsHenchmen"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_PANOPLY = "LunarDreamsPanoply"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_RETAINERS = "LunarDreamsRetainers"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_SALARY = "LunarDreamsSalary"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_SAVANT = "LunarDreamsSavant"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_SIFU = "LunarDreamsSifu"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_WEALTH = "LunarDreamsWealth"; //$NON-NLS-1$

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    characterGenerics.getAdditionalTemplateParserRegistry().register(BeastformTemplate.TEMPLATE_ID,
            new LunarBeastformParser());
    characterGenerics.getAdditionalTemplateParserRegistry().register(HeartsBloodTemplate.TEMPLATE_ID,
            new LunarHeartsBloodParser());
    characterGenerics.getAdditionalTemplateParserRegistry().register(LunarVirtueFlawTemplate.TEMPLATE_ID,
            new LunarVirtueFlawParser());
    Map<ITemplateType, ICasteType[]> templateMap = new HashMap<ITemplateType, ICasteType[]>();
    templateMap.put(castelessType, new ICasteType[]{});
    templateMap.put(dreamsType, LunarCaste.getDreamsValues());
    characterGenerics.getCasteCollectionRegistry().register(LUNAR,
            new CasteCollection(LunarCaste.values(), templateMap));
  }

  @Override
  public void addBackgroundTemplates(ICharacterGenerics generics) {
    IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry = generics.getBackgroundRegistry();
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_HEARTS_BLOOD, LUNAR));
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_SOLAR_BOND, LUNAR));
    backgroundRegistry.add(new CharacterTypeBackgroundTemplate(BACKGROUND_ID_REPUTATION, LUNAR));
    backgroundRegistry.add(
            new TemplateTypeBackgroundTemplate(BACKGROUND_ID_TATTOO_ARTIFACT, allButCasteless));
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
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    registerParsedTemplate(characterGenerics, "template/Lunar2ndCasteless.template");
    registerParsedTemplate(characterGenerics, "template/Lunar2ndSilverPact.template");
    registerParsedTemplate(characterGenerics, "template/Lunar2ndDreams.template");
    registerParsedTemplate(characterGenerics, "template/Lunar2ndDreamsEstablished.template");
    registerParsedTemplate(characterGenerics, "template/Lunar2ndDreamsInfluential.template");
    registerParsedTemplate(characterGenerics, "template/Lunar2ndDreamsLegendary.template");
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
}