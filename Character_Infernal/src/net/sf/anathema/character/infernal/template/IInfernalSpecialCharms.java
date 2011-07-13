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

public interface IInfernalSpecialCharms
{
	//Heretic
	public static final IMultiLearnableCharm UNSURPASSED_DEVIL_CRAFT = new TieredMultiLearnableCharm(
		      "Infernal.UnsurpassedDevil-Craft", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(4), new CharmTier(5) });
	
	//Adorjan
	public static final IMultiLearnableCharm WINDBORN_STRIDE = new TraitDependentMultiLearnableCharm(
		      "Infernal.Wind-BornStride", //$NON-NLS-1$
		      EssenceTemplate.SYSTEM_ESSENCE_MAX,
		      OtherTraitType.Essence);
	public static final IMultiLearnableCharm RUNNING_TO_FOREVER = new StaticMultiLearnableCharm(
		      "Infernal.RunningtoForever", //$NON-NLS-1$
		      2);
	public static final IMultiLearnableCharm SCARLET_RAPTURE_SHINTAI = new TieredMultiLearnableCharm(
		      "Infernal.ScarletRaptureShintai", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(4), new CharmTier(5), new CharmTier(6) });
	public static final IMultiLearnableCharm EARTHSKIMMING_GALE_TREAD = new TieredMultiLearnableCharm(
		      "Infernal.Earth-SkimmingGaleTread", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(3), new CharmTier(4), new CharmTier(5) });
	public static final IMultiLearnableCharm WIND_DAUGHTERS_WRATH = new TieredMultiLearnableCharm(
		      "Infernal.Wind-DaughtersWrath", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(3), new CharmTier(4) });
	public static final IMultiLearnableCharm VOICE_DRINKING_KISS = new TieredMultiLearnableCharm(
		      "Infernal.Voice-DrinkingKiss", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(3), new CharmTier(4) });
	public static final IMultiLearnableCharm CRIMSON_WIND_RIBBONS = new TieredMultiLearnableCharm(
		      "Infernal.CrimsonWindRibbons", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(3), new CharmTier(4) });
	public static final IMultiLearnableCharm SPLINTERED_GALE_SHINTAI = new TieredMultiLearnableCharm(
		      "Infernal.SplinteredGaleShintai", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(3), new CharmTier(4), new CharmTier(5), new CharmTier(6) });
	public static final IMultiLearnableCharm GIFT_OF_SILENCE = new TieredMultiLearnableCharm(
		      "Infernal.GiftofSilence", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(3), new CharmTier(4), new CharmTier(6), new CharmTier(8), new CharmTier(10) });
	
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
	public static final IMultiLearnableCharm SCOURING_BANISHMENT_TECHNIQUE = new TieredMultiLearnableCharm(
		      "Infernal.ScouringBanishmentTechnique", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(2), new CharmTier(3), new CharmTier(4), new CharmTier(4) });
	public static final IMultiLearnableCharm INEVITABILITY_OF_LAW = new TieredMultiLearnableCharm(
		      "Infernal.InevitabilityofLaw", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(5), new CharmTier(6), new CharmTier(7), new CharmTier(9) });
	
	//Ebon Dragon
	public static final IMultiLearnableCharm SELFISHNESS_IS_POWER = new TieredMultiLearnableCharm(
		      "Infernal.SelfishnessisPower", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(3), new CharmTier(3), new CharmTier(4) });
	public static final IMultiLearnableCharm LIFE_DENYING_HATE = new TieredMultiLearnableCharm(
		      "Infernal.Life-DenyingHate", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(3), new CharmTier(4), new CharmTier(7) });
	public static final IMultiLearnableCharm EVER_HUNGRY_SHADOW_AFFLICTION = new TieredMultiLearnableCharm(
		      "Infernal.Ever-HungryShadowAffliction", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(3), new CharmTier(4) });
	public static final IMultiLearnableCharm SOUL_CRACK_EXPLOITATION = new TieredMultiLearnableCharm(
		      "Infernal.SoulCrackExploitation", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(2), new CharmTier(3) });
	public static final IMultiLearnableCharm PUISSANCE_MIMICRY_INTUITION = new TieredMultiLearnableCharm(
		      "Infernal.PuissanceMimicryIntuition", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(3), new CharmTier(3), new CharmTier(4) });
	public static final IMultiLearnableCharm BLACK_MIRROR_SHINTAI = new TieredMultiLearnableCharm(
		      "Infernal.BlackMirrorShintai", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(3), new CharmTier(6) });
	public static final IMultiLearnableCharm FERVOR_DRIVEN_ANTAGONISM = new TieredMultiLearnableCharm(
		      "Infernal.Fervor-DrivenAntagonismTechnique", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(3), new CharmTier(4) });
	public static final IMultiLearnableCharm GOLDEN_YEARS_TARNISH_BLACK = new TieredMultiLearnableCharm(
		      "Infernal.GoldenYearsTarnishBlack", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(2), new CharmTier(3) });
	public static final IMultiLearnableCharm NAKED_WICKED_SOULS = new TieredMultiLearnableCharm(
		      "Infernal.NakedWickedSouls", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(2), new CharmTier(4), new CharmTier(6) });
	public static final IMultiLearnableCharm AGONY_OF_UNWISE_ADVERSITY = new TieredMultiLearnableCharm(
		      "Infernal.AgonyofUnwiseAdversity", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(2), new CharmTier(3), new CharmTier(3) });
	
	//Kimbery
	public static final IMultipleEffectCharm INTOLERABLE_BURNING_TRUTHS = new MultipleEffectCharm(
			"Infernal.IntolerableBurningTruths", new String[] { "MotherBeforeDaughter", "TrustIsNaive", "ExistenceisAgony", "NeverForgive", "HateSpringsEternal" });
	public static final IMultiLearnableCharm SPITEFUL_SEA_TINCTURE = new TieredMultiLearnableCharm(
		      "Infernal.SpitefulSeaTincture", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(2), new CharmTier(3) });
	public static final IMultiLearnableCharm SEA_WITHIN_VEINS_PRANA = new StaticMultiLearnableCharm(
		      "Infernal.SeaWithinVeinsPrana", //$NON-NLS-1$
		      2);
	public static final IMultipleEffectCharm GREAT_MOTHERS_TEARS = new MultipleEffectCharm(
			"Infernal.GreatMothersTears", new String[] { "PoisonImmunity", "KnockoutPoisons", "EmotionPoisons", "MutagenicPoisons" });
	public static final IMultiLearnableCharm ICHOR_FLUX_TENDRILS = new TieredMultiLearnableCharm(
		      "Infernal.IchorFluxTendrils", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(2), new CharmTier(3) });
	public static final IMultiLearnableCharm WHAT_LURKS_BENEATH = new TieredMultiLearnableCharm(
		      "Infernal.WhatLurksBeneath", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(2), new CharmTier(3), new CharmTier(5) });
	public static final IMultiLearnableCharm FATHOMLESS_POISON_HAVEN = new TieredMultiLearnableCharm(
		      "Infernal.FathomlessPoisonHaven", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(2), new CharmTier(3) });
	public static final IMultiLearnableCharm ACID_SLIPSTREAM_ASSIST = new TieredMultiLearnableCharm(
		      "Infernal.AcridSlipstreamAssist", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(2), new CharmTier(3) });
	
	//Malfeas
	  public static final IOxBodyTechniqueCharm HARDENED_DEVIL_BODY = new OxBodyTechniqueCharm(
		      "Infernal.HardenedDevilBody", new ITraitType[] { AttributeType.Stamina, AbilityType.Resistance },
		      new LinkedHashMap<String, HealthLevelType[]>() {
            private static final long serialVersionUID = -8422296765720844316L;

            {
		          put("Category.Infernal", new HealthLevelType[] { HealthLevelType.TWO, HealthLevelType.TWO, HealthLevelType.TWO, HealthLevelType.FOUR }); //$NON-NLS-1$
		        }
		      });
	public static final IMultiLearnableCharm VIRIDIAN_LEGEND_EXOSKELETON = new TieredMultiLearnableCharm(
		      "Infernal.ViridianLegendExoskeleton", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(3), new CharmTier(4) });
	public static final IMultiLearnableCharm SCAR_WRIT_SAGA_SHIELD = new TieredMultiLearnableCharm(
		      "Infernal.ScarWritSagaShield", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(2), new CharmTier(3), new CharmTier(4) });
	public static final IMultiLearnableCharm PATHETIC_DISTRACTION_REBUKE = new StaticMultiLearnableCharm(
				      "Infernal.PatheticDistractionRebuke", //$NON-NLS-1$
				      2);
	public static final IMultiLearnableCharm VITRIOLIC_CORNOA_ENDOWMENT = new StaticMultiLearnableCharm(
		      "Infernal.VitriolicCoronaEndowment", //$NON-NLS-1$
		      2);
	public static final IMultiLearnableCharm KISSED_BY_HELLISH_NOON = new TieredMultiLearnableCharm(
		      "Infernal.KissedByHellishNoon", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(3), new CharmTier(4) });
	public static final IMultiLearnableCharm DEMON_EMPEROR_SHINTAI = new TieredMultiLearnableCharm(
		      "Infernal.DemonEmperorShintai", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(5), new CharmTier(6) });
	public static final IMultiLearnableCharm TRIUMPH_OF_THE_WILL = new StaticMultiLearnableCharm(
		      "Infernal.TriumphoftheWill", //$NON-NLS-1$
		      2);
	public static final IMultiLearnableCharm EMBER_GIFT_REVOCATION = new TieredMultiLearnableCharm(
		      "Infernal.EmberGiftRevocation", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(4), new CharmTier(6) });
	
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
	//public static final IMultipleEffectCharm WHOLENESS_RIGHTFULLY_ASSUMED = new WholenessRightlyAssumed("Infernal.WholenessRightlyAssumed");
	public static final IMultiLearnableCharm ORBITAL_IMPACT_STORM = new TieredMultiLearnableCharm(
		      "Infernal.OrbitalImpactStorm", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(2), new CharmTier(3) });
	public static final IMultiLearnableCharm HOLLOW_MIND_POSESSION = new TieredMultiLearnableCharm(
		      "Infernal.HollowMindPossession", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(4), new CharmTier(4), new CharmTier(5) });
	public static final IMultiLearnableCharm VOICE_LIKE_CRYSTAL_FACETS = new TieredMultiLearnableCharm(
		      "Infernal.VoiceLikeCrystalFacets", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(4), new CharmTier(5), new CharmTier(7) });
	public static final IMultiLearnableCharm SPACE_MONSTER_SCREAM = new TieredMultiLearnableCharm(
		      "Infernal.SpaceMonsterScream", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(5), new CharmTier(6) });
	public static final IMultiLearnableCharm MIND_HAND_MANIPULATION = new TieredMultiLearnableCharm(
		      "Infernal.Mind-HandManipulation", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(2), new CharmTier(4) });
	
	
	
}