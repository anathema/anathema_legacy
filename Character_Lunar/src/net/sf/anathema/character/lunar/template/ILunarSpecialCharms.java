package net.sf.anathema.character.lunar.template;

import java.util.LinkedHashMap;

import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.impl.magic.charm.special.OxBodyTechniqueCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.StaticMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.SubeffectCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.TraitDependentMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.IOxBodyTechniqueCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffectCharm;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;

public interface ILunarSpecialCharms {

  public static final IOxBodyTechniqueCharm OX_BODY_TECHNIQUE = new OxBodyTechniqueCharm(
      "Lunar.Ox-BodyTechnique", AttributeType.Stamina,//$NON-NLS-1$
      new LinkedHashMap<String, HealthLevelType[]>() {
        {
          put("Category.-1x2", new HealthLevelType[] { HealthLevelType.ONE, HealthLevelType.ONE }); //$NON-NLS-1$
          put(
              "Category.-2x4", new HealthLevelType[] { HealthLevelType.TWO, HealthLevelType.TWO, HealthLevelType.TWO, HealthLevelType.TWO }); //$NON-NLS-1$
        }
      });
  
  public static final IMultiLearnableCharm DEADLY_BEASTMAN_TRANSFORMATION = new TraitDependentMultiLearnableCharm(
      "Lunar.DeadlyBeastmanTransformation", //$NON-NLS-1$
      7,
      OtherTraitType.Essence);
  
  public static final IOxBodyTechniqueCharm OX_BODY_TECHNIQUE_2ND = new OxBodyTechniqueCharm(
	      "Lunar.OxBodyTechnique", AttributeType.Stamina,//$NON-NLS-1$
	      new LinkedHashMap<String, HealthLevelType[]>() {
	        {
	          put("Category.-1x2", new HealthLevelType[] { HealthLevelType.ONE, HealthLevelType.ONE, HealthLevelType.DYING, HealthLevelType.DYING }); //$NON-NLS-1$
	          put(
	              "Category.-2x4", new HealthLevelType[] { HealthLevelType.TWO, HealthLevelType.TWO, HealthLevelType.TWO, HealthLevelType.TWO,
	            		  HealthLevelType.DYING, HealthLevelType.DYING }); //$NON-NLS-1$
	        }
	      });
  
  public static final ISubeffectCharm IMPRESSIONS_OF_STRENGTH = new SubeffectCharm(
	      "Lunar.ImpressionsOfStrength", //$NON-NLS-1$
	      new String[] {
	          "OgresLovingCaress", "RockToPebbleAttitude", "UndeniableMight", "BirthOfFlight", "MightyRamPractice" }, 1); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
  
  public static final ISubeffectCharm ADDER_FANG_METHOD = new SubeffectCharm(
	      "Lunar.AdderFangMethod", //$NON-NLS-1$
	      new String[] {
	      	"LunasSpite", "ChosensBane", "ClosingEyeToxin", "NightsLure", "StillWaterInfusion",
	      	"Custom1", "Custom2", "Custom3"}, 2);
  
  public static final ISubeffectCharm COBRA_EYE_METHOD = new SubeffectCharm(
	      "Lunar.CobraEyeMethod", //$NON-NLS-1$
	      new String[] {
	      	"BurningTar", "FoeSofteningDischarge", "SoporificNectar", "SteelEater",
	      	"Custom1", "Custom2", "Custom3"}, 2);
  
  public static final ISubeffectCharm PERFECT_OUTSIDER_UNDERSTANDING = new SubeffectCharm(
	      "Lunar.PerfectOutsiderUnderstanding", //$NON-NLS-1$
	      new String[] {
	          "Demons", "DragonKings", "Elementals", "Ghosts", "Jadeborn", "Lintha", "Raksha", "Custom" }, 1); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
  
  public static final ISubeffectCharm BESIEGING_THE_BASTION = new SubeffectCharm(
	      "Lunar.BesiegingTheBastionOfForm", //$NON-NLS-1$
	      new String[] {
	          "FastProgression", "Fatal", "Selectable", "Custom" }, 1); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
  
  public static final IMultiLearnableCharm RIGHTEOUS_LION_DEFENSE = new StaticMultiLearnableCharm(
	      "Lunar.RighteousLionDefense", //$NON-NLS-1$
	      2);
  
  public static final IMultiLearnableCharm SILVER_LUNAR_STA = new TraitDependentMultiLearnableCharm(
	      "Lunar.SilverLunarResolution", //$NON-NLS-1$
	      7,
	      OtherTraitType.Essence);
  
  public static final IMultiLearnableCharm SILVER_LUNAR_CHA = new TraitDependentMultiLearnableCharm(
	      "Lunar.SilverLunarResolutionCharisma", //$NON-NLS-1$
	      7,
	      OtherTraitType.Essence);
  
  public static final IMultiLearnableCharm SILVER_LUNAR_WIT = new TraitDependentMultiLearnableCharm(
	      "Lunar.SilverLunarResolutionWits", //$NON-NLS-1$
	      7,
	      OtherTraitType.Essence);
}