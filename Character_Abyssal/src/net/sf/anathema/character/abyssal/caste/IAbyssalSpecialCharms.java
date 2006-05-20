package net.sf.anathema.character.abyssal.caste;

import java.util.LinkedHashMap;

import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.impl.magic.charm.special.OxBodyTechniqueCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.StaticPainToleranceCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.TraitDependentMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.IOxBodyTechniqueCharm;
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
}