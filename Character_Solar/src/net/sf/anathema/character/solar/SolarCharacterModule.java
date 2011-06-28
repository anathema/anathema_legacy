package net.sf.anathema.character.solar;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.magic.FirstExcellency;
import net.sf.anathema.character.generic.framework.magic.SecondExcellency;
import net.sf.anathema.character.generic.framework.magic.ThirdExcellency;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.page.IPdfPartEncoder;
import net.sf.anathema.character.solar.caste.SolarCaste;
import net.sf.anathema.character.solar.generic.EssenceFlow;
import net.sf.anathema.character.solar.generic.InfiniteMastery;
import net.sf.anathema.character.solar.generic.SupremePerfectionOf;
import net.sf.anathema.character.solar.generic.DivineTranscendenceOf;
import net.sf.anathema.character.solar.reporting.FirstEditionSolarPartEncoder;
import net.sf.anathema.character.solar.reporting.SecondEditionSolarPartEncoder;
import net.sf.anathema.character.solar.template.ISolarSpecialCharms;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawModelFactory;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawPersisterFactory;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawTemplate;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawViewFactory;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

public class SolarCharacterModule extends NullObjectCharacterModuleAdapter {

  private static final int ESSENCE_MAX = EssenceTemplate.SYSTEM_ESSENCE_MAX;

  @SuppressWarnings("unused")
  private static final TemplateType solarTemplateType = new TemplateType(CharacterType.SOLAR);
  
  @SuppressWarnings("unused")
  private static final TemplateType solarRevisedTemplateType = new TemplateType(CharacterType.SOLAR, new Identificate(
      "RevisedSolarSubtype")); //$NON-NLS-1$
  
  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    characterGenerics.getGenericCharmStatsRegistry().register(
        CharacterType.SOLAR,
        new IMagicStats[] { new FirstExcellency(CharacterType.SOLAR, ExaltedSourceBook.SecondEdition, "1 m per die"), //$NON-NLS-1$
            new SecondExcellency(CharacterType.SOLAR, ExaltedSourceBook.SecondEdition),
            new ThirdExcellency(CharacterType.SOLAR, "4 m", ExaltedSourceBook.SecondEdition), //$NON-NLS-1$
            new InfiniteMastery(),
            new EssenceFlow(),
            new DivineTranscendenceOf(),
            new SupremePerfectionOf()});
    characterGenerics.getCharmProvider().setSpecialCharms(
        CharacterType.SOLAR,
        ExaltedEdition.FirstEdition,
        ISolarSpecialCharms.OX_BODY_TECHNIQUE,
        ISolarSpecialCharms.ENVIRONMENTAL_HAZARD_RESISTING_MEDITATION);
    
    characterGenerics.getAdditionalTemplateParserRegistry().register(
        SolarVirtueFlawTemplate.ID,
        new SolarVirtueFlawParser());
    characterGenerics.getCasteCollectionRegistry().register(
        CharacterType.SOLAR,
        new CasteCollection(SolarCaste.values()));
  }

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    registerParsedTemplate(characterGenerics, "template/legacy/DreamsDawnSolar2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/legacy/DreamsZenithSolar2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/legacy/DreamsTwilightSolar2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/legacy/DreamsNightSolar2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/legacy/DreamsEclipseSolar2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/legacy/DreamsDawnSolar2ndRevised.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/legacy/DreamsZenithSolar2ndRevised.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/legacy/DreamsTwilightSolar2ndRevised.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/legacy/DreamsNightSolar2ndRevised.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/legacy/DreamsEclipseSolar2ndRevised.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/Solar.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/Solar2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/Solar2ndRevised.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/Solar2ndDreams.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/Solar2ndDreamsRevised.template"); //$NON-NLS-1$
  }

  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    CharacterReportingModuleObject moduleObject = generics.getModuleObjectMap().getModuleObject(
        CharacterReportingModule.class);
    PdfEncodingRegistry registry = moduleObject.getPdfEncodingRegistry();
    IPdfPartEncoder secondEditionEncoder = new SecondEditionSolarPartEncoder(resources, registry, ESSENCE_MAX);
    registry.setPartEncoder(CharacterType.SOLAR, ExaltedEdition.SecondEdition, secondEditionEncoder);
    IPdfPartEncoder firstEditionEncoder = new FirstEditionSolarPartEncoder(resources, registry, ESSENCE_MAX);
    registry.setPartEncoder(CharacterType.SOLAR, ExaltedEdition.FirstEdition, firstEditionEncoder);
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
}