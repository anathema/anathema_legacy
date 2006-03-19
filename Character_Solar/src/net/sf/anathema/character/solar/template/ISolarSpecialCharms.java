package net.sf.anathema.character.solar.template;

import java.util.LinkedHashMap;

import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.magic.charms.special.IOxBodyTechniqueCharm;
import net.sf.anathema.character.generic.magic.charms.special.OxBodyTechniqueCharm;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public interface ISolarSpecialCharms {

  public static final IOxBodyTechniqueCharm OX_BODY_TECHNIQUE = new OxBodyTechniqueCharm(
      "Solar.Ox-BodyTechnique", AbilityType.Endurance,//$NON-NLS-1$
      new LinkedHashMap<String, HealthLevelType[]>() {
        {
          put("Category.-0", new HealthLevelType[] { HealthLevelType.ZERO }); //$NON-NLS-1$
          put("Category.-1x2", new HealthLevelType[] { HealthLevelType.ONE, HealthLevelType.ONE }); //$NON-NLS-1$
          put(
              "Category.-1-2x2", new HealthLevelType[] { HealthLevelType.ONE, HealthLevelType.TWO, HealthLevelType.TWO }); //$NON-NLS-1$
        }
      });
}