package net.sf.anathema.character.main.magic.model.charm.special;

import net.sf.anathema.hero.health.model.HealthLevelType;
import net.sf.anathema.character.main.traits.TraitType;

import java.util.Map;

public interface IOxBodyTechniqueCharm extends ISpecialCharm {

  TraitType[] getRelevantTraits();

  Map<String, HealthLevelType[]> getHealthLevels();
}