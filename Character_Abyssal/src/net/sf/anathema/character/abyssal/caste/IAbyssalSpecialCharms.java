package net.sf.anathema.character.abyssal.caste;

import java.util.LinkedHashMap;

import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.impl.magic.charm.special.OxBodyTechniqueCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.StaticMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.StaticPainToleranceCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.SubeffectCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.TraitDependentMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.IOxBodyTechniqueCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffectCharm;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;

public interface IAbyssalSpecialCharms {

  public static final IMultiLearnableCharm INSENSIBLE_CORPSE_TECHNIQUE = new StaticPainToleranceCharm(
      "Abyssal.InsensibleCorpseTechniquePermanent", //$NON-NLS-1$
      3,
      new int[] { 2, 4, 4 });
  public static final IMultiLearnableCharm ESSENCE_ENGORGEMENT_TECHNIQUE = new TraitDependentMultiLearnableCharm(
      "Abyssal.EssenceEngorgementTechnique", //$NON-NLS-1$
      7,
      OtherTraitType.Essence);
  public static final IOxBodyTechniqueCharm OX_BODY_TECHNIQUE = new OxBodyTechniqueCharm(
      "Abyssal.Ox-BodyTechnique", AbilityType.Endurance, //$NON-NLS-1$
      new LinkedHashMap<String, HealthLevelType[]>() {
        {
          put("Category.-0", new HealthLevelType[] { HealthLevelType.ZERO }); //$NON-NLS-1$
          put("Category.-1x2", new HealthLevelType[] { HealthLevelType.ONE, HealthLevelType.ONE }); //$NON-NLS-1$
          put(
              "Category.-1-2x2", new HealthLevelType[] { HealthLevelType.ONE, HealthLevelType.TWO, HealthLevelType.TWO }); //$NON-NLS-1$
        }
      });
  
  public static final IOxBodyTechniqueCharm OX_BODY_TECHNIQUE_2ND = new OxBodyTechniqueCharm(
	      "Abyssal.OxBodyTechnique", AbilityType.Resistance, //$NON-NLS-1$
	      new LinkedHashMap<String, HealthLevelType[]>() {
	        {
	          put("Category.-0", new HealthLevelType[] { HealthLevelType.ZERO }); //$NON-NLS-1$
	          put("Category.-1x2", new HealthLevelType[] { HealthLevelType.ONE, HealthLevelType.ONE }); //$NON-NLS-1$
	          put(
	              "Category.-1-2x2", new HealthLevelType[] { HealthLevelType.ONE, HealthLevelType.TWO, HealthLevelType.TWO }); //$NON-NLS-1$
	        }
	      });
  
  public static final IMultiLearnableCharm WRITHING_BLOOD_CHAIN_TECHNIQUE = new TraitDependentMultiLearnableCharm(
	      "Abyssal.WrithingBloodChainTechnique", //$NON-NLS-1$
	      EssenceTemplate.SYSTEM_ESSENCE_MAX,
	      OtherTraitType.Essence, -2);
  
  public static final IMultiLearnableCharm CADAVEROUS_TORPOR_TECHNIQUE = new TraitDependentMultiLearnableCharm(
	      "Abyssal.CadaverousTorporTechnique", //$NON-NLS-1$
	      EssenceTemplate.SYSTEM_ESSENCE_MAX,
	      AbilityType.Resistance);
  
  public static final IMultiLearnableCharm ETERNAL_EMNITY_APPROACH = new StaticMultiLearnableCharm(
	      "Abyssal.EternalEnmityApproach", //$NON-NLS-1$
	      2);
  
  public static final IMultiLearnableCharm WORLD_SLAYING_ARSENEL = new StaticMultiLearnableCharm(
	      "Abyssal.WorldSlayingArsenalEphiphany", //$NON-NLS-1$
	      2);
  
  public static final ISubeffectCharm VOID_SPLINTER = new SubeffectCharm("Abyssal.SplinterOfTheVoid", //$NON-NLS-1$
	      new String[] { "BloodthirstyArrow", "DreamShatteringSpite", "HundredPacesBite", "ImpositionOfAgony" }, 0.5); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  
  public static final ISubeffectCharm WICKED_DARTS_OF_SUFFERING = new SubeffectCharm("Abyssal.WickedDartsOfSuffering", //$NON-NLS-1$
	      new String[] { "Corrosion", "Destruction", "Infection", "Pain" }, 0.5); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  
  public static final ISubeffectCharm PESTILENCE_BEARING_TOUCH = new SubeffectCharm("Abyssal.PestilenceBearingTouch", //$NON-NLS-1$
	      new String[] { "Disease1", "Disease2", "Disease3", "Disease4", "Disease5" }, 0.5); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

}