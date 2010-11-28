package net.sf.anathema.character.sidereal.template;

import java.util.LinkedHashMap;

import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.impl.magic.charm.special.OxBodyTechniqueCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.StaticMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
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

}
