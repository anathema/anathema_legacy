package net.sf.anathema.character.infernal;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.magic.FirstExcellency;
import net.sf.anathema.character.generic.framework.magic.SecondExcellency;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.impl.magic.charm.special.CharmTier;
import net.sf.anathema.character.generic.impl.magic.charm.special.EssenceFixedMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.TieredMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.YoziType;
import net.sf.anathema.character.generic.type.CharacterType;

import net.sf.anathema.character.infernal.caste.InfernalCaste;
import net.sf.anathema.character.infernal.generic.EffortlessYoziDominance;
import net.sf.anathema.character.infernal.generic.SoSpeaksYozi;
import net.sf.anathema.character.infernal.generic.YoziInevitabilityTechnique;
import net.sf.anathema.character.infernal.generic.YoziMythosExultant;
import net.sf.anathema.character.infernal.patron.InfernalPatronModelFactory;
import net.sf.anathema.character.infernal.patron.InfernalPatronParser;
import net.sf.anathema.character.infernal.patron.InfernalPatronTemplate;
import net.sf.anathema.character.infernal.patron.InfernalPatronViewFactory;
import net.sf.anathema.character.infernal.patron.persistence.InfernalPatronPersisterFactory;
import net.sf.anathema.character.infernal.reporting.InfernalPartEncoder;
import net.sf.anathema.character.infernal.template.IInfernalSpecialCharms;
import net.sf.anathema.character.infernal.urge.InfernalUrgeModelFactory;
import net.sf.anathema.character.infernal.urge.InfernalUrgeParser;
import net.sf.anathema.character.infernal.urge.InfernalUrgePersisterFactory;
import net.sf.anathema.character.infernal.urge.InfernalUrgeTemplate;
import net.sf.anathema.character.infernal.urge.InfernalUrgeViewFactory;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.page.IPdfPartEncoder;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public class InfernalCharacterModule extends NullObjectCharacterModuleAdapter {

  /*private static final TemplateType baseType = new TemplateType(CharacterType.INFERNAL, new Identificate(
	  "default")); //$NON-NLS-1$*/
  
  //private static final ITemplateType[] baseTemplates = new ITemplateType[] { baseType };
	
  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    characterGenerics.getCasteCollectionRegistry().register(
        CharacterType.INFERNAL,
        new CasteCollection(InfernalCaste.values()));
    characterGenerics.getAdditionalTemplateParserRegistry().register(
    		InfernalPatronTemplate.ID,
            new InfernalPatronParser());
    characterGenerics.getAdditionalTemplateParserRegistry().register(
    		InfernalUrgeTemplate.ID,
            new InfernalUrgeParser());
    
    characterGenerics.getCharmProvider().setSpecialCharms(
            CharacterType.INFERNAL,
            ExaltedEdition.SecondEdition,
            getSpecialCharmArray(ExaltedEdition.SecondEdition));
    
    characterGenerics.getGenericCharmStatsRegistry().register(
            CharacterType.INFERNAL,
            new IMagicStats[] { new FirstExcellency(CharacterType.INFERNAL, ExaltedSourceBook.Infernals, "1 m per die"), //$NON-NLS-1$
                new SecondExcellency(CharacterType.INFERNAL, ExaltedSourceBook.Infernals),
                new YoziMythosExultant(),
                new YoziInevitabilityTechnique(),
                new EffortlessYoziDominance(),
                new SoSpeaksYozi()});
  }
  
  private ISpecialCharm[] getSpecialCharmArray(IExaltedEdition edition)
  {
	  if (edition == ExaltedEdition.SecondEdition)
	  {
		  ISpecialCharm baseSet[] = new ISpecialCharm[] {
                  IInfernalSpecialCharms.WINDBORN_STRIDE,
                  IInfernalSpecialCharms.SCARLET_RAPTURE_SHINTAI,
                  IInfernalSpecialCharms.EARTHSKIMMING_GALE_TREAD,
                  IInfernalSpecialCharms.SELFISHNESS_IS_POWER,
                  IInfernalSpecialCharms.LIFE_DENYING_HATE,
                  IInfernalSpecialCharms.EVER_HUNGRY_SHADOW_AFFLICTION,
                  IInfernalSpecialCharms.SOUL_CRACK_EXPLOITATION,
                  IInfernalSpecialCharms.PUISSANCE_MIMICRY_INTUITION,
                  IInfernalSpecialCharms.BLACK_MIRROR_SHINTAI,
                  IInfernalSpecialCharms.WHOLENESS_RIGHTFULLY_ASSUMED,
                  IInfernalSpecialCharms.WAYWARD_DIVINITY_OVERSIGHT,
                  IInfernalSpecialCharms.ANONYMITY_THROUGH_PROPRIETY,
                  IInfernalSpecialCharms.UNQUESTIONABLE_YOZI_AUTHORITY,
                  IInfernalSpecialCharms.SCORPION_TAILED_MIRAGE_TECHNIQUE,
                  IInfernalSpecialCharms.RUNNING_TO_FOREVER,
                  IInfernalSpecialCharms.INTOLERABLE_BURNING_TRUTHS,
                  IInfernalSpecialCharms.HARDENED_DEVIL_BODY,
                  IInfernalSpecialCharms.VIRIDIAN_LEGEND_EXOSKELETON,
                  IInfernalSpecialCharms.ANALYTIC_MODELING_TECHNIQUE,
                  IInfernalSpecialCharms.UNSHATTERED_TONGUE_PERFECTION,
                  IInfernalSpecialCharms.CONSTRUCTIVE_CONVERGANCE_OF_PRINCIPLES,
                  IInfernalSpecialCharms.ESSENCE_INFUSED_EGO_PRIMACY,
                  IInfernalSpecialCharms.COSMIC_TRANSCENDENCE,
                  IInfernalSpecialCharms.WIND_DAUGHTERS_WRATH,
                  IInfernalSpecialCharms.VOICE_DRINKING_KISS,
                  IInfernalSpecialCharms.SCOURING_BANISHMENT_TECHNIQUE,
                  IInfernalSpecialCharms.FERVOR_DRIVEN_ANTAGONISM,
                  IInfernalSpecialCharms.GOLDEN_YEARS_TARNISH_BLACK,
                  IInfernalSpecialCharms.ORBITAL_IMPACT_STORM,
                  IInfernalSpecialCharms.CRIMSON_WIND_RIBBONS,
                  IInfernalSpecialCharms.SPLINTERED_GALE_SHINTAI,
                  IInfernalSpecialCharms.NAKED_WICKED_SOULS,
                  IInfernalSpecialCharms.UNSURPASSED_DEVIL_CRAFT,
                  IInfernalSpecialCharms.SPITEFUL_SEA_TINCTURE,
                  IInfernalSpecialCharms.SEA_WITHIN_VEINS_PRANA,
                  IInfernalSpecialCharms.SCAR_WRIT_SAGA_SHIELD,
                  IInfernalSpecialCharms.PATHETIC_DISTRACTION_REBUKE,
                  IInfernalSpecialCharms.VITRIOLIC_CORNOA_ENDOWMENT,
                  IInfernalSpecialCharms.KISSED_BY_HELLISH_NOON,
                  IInfernalSpecialCharms.DEMON_EMPEROR_SHINTAI,
                  IInfernalSpecialCharms.GREAT_MOTHERS_TEARS,
                  IInfernalSpecialCharms.ICHOR_FLUX_TENDRILS,
                  IInfernalSpecialCharms.WHAT_LURKS_BENEATH,
                  IInfernalSpecialCharms.FATHOMLESS_POISON_HAVEN,
                  IInfernalSpecialCharms.ACID_SLIPSTREAM_ASSIST,
                  IInfernalSpecialCharms.GIFT_OF_SILENCE,
                  IInfernalSpecialCharms.AGONY_OF_UNWISE_ADVERSITY,
                  IInfernalSpecialCharms.HOLLOW_MIND_POSESSION,
                  IInfernalSpecialCharms.TRIUMPH_OF_THE_WILL,
                  IInfernalSpecialCharms.EMBER_GIFT_REVOCATION,
                  IInfernalSpecialCharms.VOICE_LIKE_CRYSTAL_FACETS,
                  IInfernalSpecialCharms.SPACE_MONSTER_SCREAM,
                  IInfernalSpecialCharms.INEVITABILITY_OF_LAW,
                  IInfernalSpecialCharms.TOOL_TRANSCENDING_CONSTRUCTS};
		  
		  int yoziCount = YoziType.values().length;
		  int generalSpecialCharmCount = 2;
		  ISpecialCharm[] masterSet = new ISpecialCharm[baseSet.length + generalSpecialCharmCount*yoziCount];
		  for (int i = 0; i != baseSet.length; i++) masterSet[i] = baseSet[i];
		  
		  for (int i = 0; i != yoziCount; i++)
		  {
			  ITraitType type = YoziType.values()[i];
			  masterSet[baseSet.length + 2*i] = new EssenceFixedMultiLearnableCharm(
				      "Infernal.1stExcellency." + type.getId(), //$NON-NLS-1$
				      EssenceTemplate.SYSTEM_ESSENCE_MAX,
				      OtherTraitType.Essence);
			  masterSet[baseSet.length + 2*i + 1] = new TieredMultiLearnableCharm(
				      "Infernal.YoziBodyUnity." + type.getId(), //$NON-NLS-1$
				      new CharmTier[] { new CharmTier(5), new CharmTier(7) });
		  }
		  return masterSet;		  
	  }
	  return null;
  }
  
  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    registerParsedTemplate(characterGenerics, "template/Infernal2nd.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/RevisedInfernal2nd.template"); //$NON-NLS-1$
  }
  
  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = characterGenerics.getAdditionalModelFactoryRegistry();
    IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry = characterGenerics.getAdditionalViewFactoryRegistry();
    IRegistry<String, IAdditionalPersisterFactory> persisterFactory = characterGenerics.getAdditonalPersisterFactoryRegistry();
    registerInfernalPatron(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
    registerInfernalUrge(additionalModelFactoryRegistry, additionalViewFactoryRegistry, persisterFactory);
  }
  
  private void registerInfernalPatron(
		  IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry,
	      IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry,
	      IRegistry<String, IAdditionalPersisterFactory> persisterFactory)
  {
	  String templateId = InfernalPatronTemplate.ID;
	  additionalModelFactoryRegistry.register(templateId, new InfernalPatronModelFactory());  
	  additionalViewFactoryRegistry.register(templateId, new InfernalPatronViewFactory());
	  persisterFactory.register(templateId, new InfernalPatronPersisterFactory());
  }
  
  private void registerInfernalUrge(
		  IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry,
	      IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry,
	      IRegistry<String, IAdditionalPersisterFactory> persisterFactory)
  {
	  String templateId = InfernalUrgeTemplate.ID;
	  additionalModelFactoryRegistry.register(templateId, new InfernalUrgeModelFactory());  
	  additionalViewFactoryRegistry.register(templateId, new InfernalUrgeViewFactory());
	  persisterFactory.register(templateId, new InfernalUrgePersisterFactory());
  }
  
  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    CharacterReportingModuleObject moduleObject = generics.getModuleObjectMap().getModuleObject(
        CharacterReportingModule.class);
    PdfEncodingRegistry registry = moduleObject.getPdfEncodingRegistry();
    IPdfPartEncoder secondEditionEncoder = new InfernalPartEncoder(resources, registry, EssenceTemplate.SYSTEM_ESSENCE_MAX);
    registry.setPartEncoder(CharacterType.INFERNAL, ExaltedEdition.SecondEdition, secondEditionEncoder);
  }

}