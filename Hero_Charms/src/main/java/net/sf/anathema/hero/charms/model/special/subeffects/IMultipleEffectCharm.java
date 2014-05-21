package net.sf.anathema.hero.charms.model.special.subeffects;

import net.sf.anathema.character.magic.charm.Charm;
import net.sf.anathema.character.magic.charm.ICharmLearnableArbitrator;
import net.sf.anathema.hero.charms.model.special.CharmSpecialist;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;

public interface IMultipleEffectCharm extends ISpecialCharm {

  SubEffects buildSubEffects(CharmSpecialist specialist, ICharmLearnableArbitrator arbitrator, Charm charm);
}