package net.sf.anathema.character.generic.magic.charms.special;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.impl.model.charm.CharmSpecialist;

public interface IMultipleEffectCharm extends ISpecialCharm {

  SubEffects buildSubeffects(CharmSpecialist specialist, ICharmLearnableArbitrator arbitrator, ICharm charm);
}