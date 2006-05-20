package net.sf.anathema.character.db.template;

import java.util.LinkedHashMap;

import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.impl.magic.charm.special.OxBodyTechniqueCharm;
import net.sf.anathema.character.generic.magic.charms.special.IOxBodyTechniqueCharm;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public interface IDbSpecialCharms {
  public static final IOxBodyTechniqueCharm OX_BODY_TECHNIQUE = new OxBodyTechniqueCharm(
      "Dragon-Blooded.Ox-BodyTechnique", AbilityType.Endurance, //$NON-NLS-1$
      new LinkedHashMap<String, HealthLevelType[]>() {
        {
          put("Category.-1-2", new HealthLevelType[] { HealthLevelType.ONE, HealthLevelType.TWO }); //$NON-NLS-1$
        }
      });
}