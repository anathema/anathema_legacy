package net.sf.anathema.character.main.magic.charms.special;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.main.magic.charms.CharmSpecialist;

public interface IMultipleEffectCharm extends ISpecialCharm {

  SubEffects buildSubeffects(CharmSpecialist specialist, ICharmLearnableArbitrator arbitrator, ICharm charm);
}