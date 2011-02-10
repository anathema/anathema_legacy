package net.sf.anathema.character.lunar.template;

import java.util.LinkedHashMap;

import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.impl.magic.charm.special.OxBodyTechniqueCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.StaticMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.TraitDependentMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.IOxBodyTechniqueCharm;
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
  
  public static final IMultiLearnableCharm DEADLY_BEASTMAN_TRANSFORMATION_2ND = new StaticMultiLearnableCharm(
	      "Lunar.DeadlyBeastmanTransformation", //$NON-NLS-1$
	      1);
  
  public static final IOxBodyTechniqueCharm OX_BODY_TECHNIQUE_2ND = new OxBodyTechniqueCharm(
	      "Lunar.OxBodyTechnique", AttributeType.Stamina,//$NON-NLS-1$
	      new LinkedHashMap<String, HealthLevelType[]>() {
	        {
	          put("Category.-1x2", new HealthLevelType[] { HealthLevelType.ONE, HealthLevelType.ONE }); //$NON-NLS-1$
	          put(
	              "Category.-2x4", new HealthLevelType[] { HealthLevelType.TWO, HealthLevelType.TWO, HealthLevelType.TWO, HealthLevelType.TWO }); //$NON-NLS-1$
	        }
	      });
  
  public static final IMultiLearnableCharm DIRECTIONAL_MASTERY_TECHNIQUE = new StaticMultiLearnableCharm(
		  "Lunar.DirectionalMasteryTechnique", 5);
}