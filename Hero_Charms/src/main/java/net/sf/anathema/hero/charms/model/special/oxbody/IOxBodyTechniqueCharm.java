package net.sf.anathema.hero.charms.model.special.oxbody;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.health.HealthLevelType;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;

import java.util.Map;

public interface IOxBodyTechniqueCharm extends ISpecialCharm {

  TraitType[] getRelevantTraits();

  Map<String, HealthLevelType[]> getHealthLevels();
}