package net.sf.anathema.character.lunar;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.module.CharacterGenericsModuleAdapter;
import net.sf.anathema.character.generic.impl.backgrounds.TemplateTypeBackgroundTemplate;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.BeastformModelFactory;
import net.sf.anathema.character.lunar.beastform.BeastformPersisterFactory;
import net.sf.anathema.character.lunar.beastform.BeastformViewFactory;
import net.sf.anathema.character.lunar.caste.LunarCaste;
import net.sf.anathema.character.lunar.heartsblood.HeartsBloodFactory;
import net.sf.anathema.character.lunar.heartsblood.HeartsBloodPersisterFactory;
import net.sf.anathema.character.lunar.heartsblood.HeartsBloodTemplate;
import net.sf.anathema.character.lunar.heartsblood.HeartsBloodViewFactory;
import net.sf.anathema.character.lunar.renown.RenownFactory;
import net.sf.anathema.character.lunar.renown.RenownPersisterFactory;
import net.sf.anathema.character.lunar.renown.RenownTemplate;
import net.sf.anathema.character.lunar.renown.RenownViewFactory;
import net.sf.anathema.character.lunar.reporting.LunarVoidstateReportTemplate;
import net.sf.anathema.character.lunar.template.ILunarSpecialCharms;
import net.sf.anathema.character.lunar.virtueflaw.LunarVirtueFlawModelFactory;
import net.sf.anathema.character.lunar.virtueflaw.LunarVirtueFlawPersisterFactory;
import net.sf.anathema.character.lunar.virtueflaw.LunarVirtueFlawTemplate;
import net.sf.anathema.character.lunar.virtueflaw.LunarVirtueFlawViewFactory;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public class LunarCharacterModule extends CharacterGenericsModuleAdapter {

  public static final String BACKGROUND_ID_HEARTS_BLOOD = "HeartsBlood"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_HEARTS_BLOOD_HUMAN = "HeartsBloodHuman"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_RENOWN = "Renown"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_CULT = "Cult"; //$NON-NLS-1$
  private static final ITemplateType[] lunarTemplateType = new ITemplateType[] { new TemplateType(CharacterType.LUNAR) };
  public static final IBackgroundTemplate RENOWN_BACKGROUND_TYPE = new TemplateTypeBackgroundTemplate(
      BACKGROUND_ID_RENOWN,
      lunarTemplateType);

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    characterGenerics.getAdditionalTemplateParserRegistry().register(
        BeastformTemplate.TEMPLATE_ID,
        new LunarBeastformParser());
    characterGenerics.getAdditionalTemplateParserRegistry().register(
        HeartsBloodTemplate.TEMPLATE_ID,
        new LunarHeartsBloodParser());
    characterGenerics.getAdditionalTemplateParserRegistry().register(
        RenownTemplate.TEMPLATE_ID,
        new LunarRenownParser());
    characterGenerics.getAdditionalTemplateParserRegistry().register(
        LunarVirtueFlawTemplate.TEMPLATE_ID,
        new LunarVirtueFlawParser());
    ISpecialCharm[] specialCharms = new ISpecialCharm[] {
        ILunarSpecialCharms.DEADLY_BEASTMAN_TRANSFORMATION,
        ILunarSpecialCharms.OX_BODY_TECHNIQUE };
    characterGenerics.getCharmProvider().setSpecialCharms(CharacterType.LUNAR, specialCharms);
    characterGenerics.getCasteCollectionRegistry().register(
        CharacterType.LUNAR,
        new CasteCollection(LunarCaste.values()));
  }

  @Override
  public void addBackgroundTemplates(ICharacterGenerics generics) {
    IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry = generics.getBackgroundRegistry();
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_CULT, lunarTemplateType));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_HEARTS_BLOOD, lunarTemplateType));
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_HEARTS_BLOOD_HUMAN, lunarTemplateType));
    backgroundRegistry.add(RENOWN_BACKGROUND_TYPE);
  }

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    registerParsedTemplate(characterGenerics, "template/Lunar.template"); //$NON-NLS-1$
  }

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = characterGenerics.getAdditionalModelFactoryRegistry();
    IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry = characterGenerics.getAdditionalViewFactoryRegistry();
    IRegistry<String, IAdditionalPersisterFactory> persisterFactory = characterGenerics.getAdditonalPersisterFactoryRegistry();
    registerBeastform(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
    registerHeartsblood(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
    registerRenown(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
    registerVirtueFlaw(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
  }

  private void registerVirtueFlaw(
      IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry,
      IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry,
      IRegistry<String, IAdditionalPersisterFactory> persisterFactory) {
    String templateId = LunarVirtueFlawTemplate.TEMPLATE_ID;
    additionalModelFactoryRegistry.register(templateId, new LunarVirtueFlawModelFactory());
    additionalViewFactoryRegistry.register(templateId, new LunarVirtueFlawViewFactory());
    persisterFactory.register(templateId, new LunarVirtueFlawPersisterFactory());

  }

  private void registerRenown(
      IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry,
      IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry,
      IRegistry<String, IAdditionalPersisterFactory> persisterFactory) {
    String templateId = RenownTemplate.TEMPLATE_ID;
    additionalModelFactoryRegistry.register(templateId, new RenownFactory());
    additionalViewFactoryRegistry.register(templateId, new RenownViewFactory());
    persisterFactory.register(templateId, new RenownPersisterFactory());
  }

  private void registerHeartsblood(
      IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry,
      IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry,
      IRegistry<String, IAdditionalPersisterFactory> persisterFactory) {
    String templateId = HeartsBloodTemplate.TEMPLATE_ID;
    additionalModelFactoryRegistry.register(templateId, new HeartsBloodFactory());
    additionalViewFactoryRegistry.register(templateId, new HeartsBloodViewFactory());
    persisterFactory.register(templateId, new HeartsBloodPersisterFactory());
  }

  private void registerBeastform(
      IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry,
      IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry,
      IRegistry<String, IAdditionalPersisterFactory> persisterFactory) {
    String templateId = BeastformTemplate.TEMPLATE_ID;
    additionalModelFactoryRegistry.register(templateId, new BeastformModelFactory());
    additionalViewFactoryRegistry.register(templateId, new BeastformViewFactory());
    persisterFactory.register(templateId, new BeastformPersisterFactory());
  }

  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    generics.getReportTemplateRegistry().add(new LunarVoidstateReportTemplate(resources));
  }
}