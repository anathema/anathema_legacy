package net.sf.anathema.character.infernal.template;

import java.util.LinkedHashMap;

import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.impl.magic.charm.special.CharmTier;
import net.sf.anathema.character.generic.impl.magic.charm.special.MultipleEffectCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.OxBodyTechniqueCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.StaticMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.TieredMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.TraitDependentMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.IOxBodyTechniqueCharm;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.infernal.generic.InfernalFirstExcellency;

public interface IInfernalSpecialCharms
{
	public static final IMultiLearnableCharm MALFEAS_EXCELLENCY = new InfernalFirstExcellency(
		      "Infernal.1stExcellency.Malfeas", //$NON-NLS-1$
		      EssenceTemplate.SYSTEM_ESSENCE_MAX,
		      OtherTraitType.Essence);
	public static final IMultiLearnableCharm CECELYNE_EXCELLENCY = new InfernalFirstExcellency(
		      "Infernal.1stExcellency.Cecelyne", //$NON-NLS-1$
		      EssenceTemplate.SYSTEM_ESSENCE_MAX,
		      OtherTraitType.Essence);
	public static final IMultiLearnableCharm SWLIHN_EXCELLENCY = new InfernalFirstExcellency(
		      "Infernal.1stExcellency.SheWhoLivesInHerName", //$NON-NLS-1$
		      EssenceTemplate.SYSTEM_ESSENCE_MAX,
		      OtherTraitType.Essence);
	public static final IMultiLearnableCharm ADORJAN_EXCELLENCY = new InfernalFirstExcellency(
		      "Infernal.1stExcellency.Adorjan", //$NON-NLS-1$
		      EssenceTemplate.SYSTEM_ESSENCE_MAX,
		      OtherTraitType.Essence);
	public static final IMultiLearnableCharm EBON_DRAGON_EXCELLENCY = new InfernalFirstExcellency(
		      "Infernal.1stExcellency.EbonDragon", //$NON-NLS-1$
		      EssenceTemplate.SYSTEM_ESSENCE_MAX,
		      OtherTraitType.Essence);
	public static final IMultiLearnableCharm KIMBERY_EXCELLENCY = new InfernalFirstExcellency(
		      "Infernal.1stExcellency.Kimbery", //$NON-NLS-1$
		      EssenceTemplate.SYSTEM_ESSENCE_MAX,
		      OtherTraitType.Essence);
	
	//Adorjan
	public static final IMultiLearnableCharm WINDBORN_STRIDE = new TraitDependentMultiLearnableCharm(
		      "Infernal.Wind-BornStride", //$NON-NLS-1$
		      EssenceTemplate.SYSTEM_ESSENCE_MAX,
		      OtherTraitType.Essence);
	public static final IMultiLearnableCharm RUNNING_TO_FOREVER = new StaticMultiLearnableCharm(
		      "Infernal.RunningtoForever", //$NON-NLS-1$
		      2);
	
	//Cecelyne
	public static final IMultiLearnableCharm WAYWARD_DIVINITY_OVERSIGHT = new TieredMultiLearnableCharm(
		      "Infernal.WaywardDivinityOversight", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(3), new CharmTier(4), new CharmTier(4) });
	public static final IMultiLearnableCharm ANONYMITY_THROUGH_PROPRIETY = new TraitDependentMultiLearnableCharm(
		      "Infernal.AnonymityThroughPropriety", //$NON-NLS-1$
		      EssenceTemplate.SYSTEM_ESSENCE_MAX - 2,
		      OtherTraitType.Essence, -2);
	public static final IMultiLearnableCharm UNQUESTIONABLE_YOZI_AUTHORITY = new TieredMultiLearnableCharm(
		      "Infernal.UnquestionableYoziAuthority", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(3), new CharmTier(4) });
	public static final IMultiLearnableCharm SCORPION_TAILED_MIRAGE_TECHNIQUE = new StaticMultiLearnableCharm(
		      "Infernal.Scorpion-TailedMirageTechnique", //$NON-NLS-1$
		      2);
	
	//Kimbery
	public static final IMultipleEffectCharm INTOLERABLE_BURNING_TRUTHS = new MultipleEffectCharm(
			"Infernal.IntolerableBurningTruths", new String[] { "MotherBeforeDaughter", "TrustIsNaive", "ExistenceisAgony", "NeverForgive", "HateSpringsEternal" });
	
	//Malfeas
	  public static final IOxBodyTechniqueCharm HARDENED_DEVIL_BODY = new OxBodyTechniqueCharm(
		      "Infernal.HardenedDevilBody", new ITraitType[] { AttributeType.Stamina, AbilityType.Resistance },
		      new LinkedHashMap<String, HealthLevelType[]>() {
		        {
		          put("Category.Infernal", new HealthLevelType[] { HealthLevelType.TWO, HealthLevelType.TWO, HealthLevelType.TWO, HealthLevelType.FOUR }); //$NON-NLS-1$
		        }
		      });
	public static final IMultiLearnableCharm VIRIDIAN_LEGEND_EXOSKELETON = new TieredMultiLearnableCharm(
		      "Infernal.ViridianLegendExoskeleton", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(3), new CharmTier(4) });
	
	//She Who Lives In Her Name
	public static final IMultiLearnableCharm ANALYTIC_MODELING_TECHNIQUE = new StaticMultiLearnableCharm(
		      "Infernal.AnalyticalModellingIntuition", //$NON-NLS-1$
		      3);
	public static final IMultiLearnableCharm UNSHATTERED_TONGUE_PERFECTION = new TieredMultiLearnableCharm(
		      "Infernal.UnshatteredTonguePerfection", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(2), new CharmTier(3) });
	public static final IMultipleEffectCharm COSMIC_TRANSCENDENCE = new MultipleEffectCharm(
			"Infernal.CosmicTranscendenceofVirtue", new String[] { "Compassion", "Conviction", "Temperance", "Valor" });
	public static final IMultiLearnableCharm ESSENCE_INFUSED_EGO_PRIMACY = new TieredMultiLearnableCharm(
		      "Infernal.Ego-InfusedPatternPrimacy", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(2), new CharmTier(3), new CharmTier(3) });
	public static final IMultiLearnableCharm CONSTRUCTIVE_CONVERGANCE_OF_PRINCIPLES = new TieredMultiLearnableCharm(
		      "Infernal.ConstructiveConvergenceofPrinciples", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(3), new CharmTier(4), new CharmTier(5) });
	public static final IMultiLearnableCharm TOOL_TRANSCENDING_CONSTRUCTS = new TieredMultiLearnableCharm(
		      "Infernal.Tool-TranscendingConstructs", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(3), new CharmTier(4), new CharmTier(6), new CharmTier(7) });
	
	
}