package net.sf.anathema.character.sidereal.template;

import java.util.LinkedHashMap;

import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.impl.magic.charm.special.OxBodyTechniqueCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.StaticMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.SubeffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffectCharm;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public interface ISiderealSpecialCharms {

  public static final ISpecialCharm OX_BODY_TECHNIQUE = new OxBodyTechniqueCharm(
      "Sidereal.Ox-BodyTechnique", AbilityType.Endurance, //$NON-NLS-1$
      new LinkedHashMap<String, HealthLevelType[]>() {
        {
          put("Category.-0", new HealthLevelType[] { HealthLevelType.ZERO }); //$NON-NLS-1$
        }
      });

  public static final ISpecialCharm WORLD_SHAPING_ARTISTIC_VISION = new StaticMultiLearnableCharm(
      "Sidereal.World-ShapingArtisticVision", //$NON-NLS-1$
      3);
  
  public static final ISpecialCharm OX_BODY_TECHNIQUE_2ND = new OxBodyTechniqueCharm(
	      "Sidereal.OxBodyTechnique", AbilityType.Resistance, //$NON-NLS-1$
	      new LinkedHashMap<String, HealthLevelType[]>() {
	        {
	          put("Category.-0", new HealthLevelType[] { HealthLevelType.ZERO }); //$NON-NLS-1$
	        }
	      });

	  public static final ISpecialCharm WORLD_SHAPING_ARTISTIC_VISION_2ND = new StaticMultiLearnableCharm(
	      "Sidereal.WorldShapingArtisticVision", //$NON-NLS-1$
	      3);
	  
	  public static final ISubeffectCharm WALLS_OF_SALT_AND_ASH = new SubeffectCharm(
		      "Sidereal.WallsOfSaltAndAsh", //$NON-NLS-1$
		      new String[] {
		          "GodsElementals", "FairFolk", "Undead", "Demons" }, 1);
	  
	  public static final ISubeffectCharm MIRROR_SHATTERING_METHOD = new SubeffectCharm(
		      "Sidereal.MirrorShatteringMethod", //$NON-NLS-1$
		      new String[] {
		          "YuShan", "Underworld", "Malfeas" }, 1);
	  
	  public static final ISubeffectCharm MANY_MISSILES_BOW = new SubeffectCharm(
		      "Sidereal.ManyMissilesBowTechnique", //$NON-NLS-1$
		      new String[] {
		          "Boulder", "Fire", "Glass", "Grain", "Health", "Love", "Precipitation", "Custom1", "Custom2", "Custom3" }, .5);
	  
	  public static final ISubeffectCharm MASQUE_OF_THE_UNCANNY = new SubeffectCharm(
		      "Sidereal.MasqueOfTheUncanny", //$NON-NLS-1$
		      new String[] {
		          "Ghosts", "Raksha", "Demons" }, 1);
	  
	  

}
