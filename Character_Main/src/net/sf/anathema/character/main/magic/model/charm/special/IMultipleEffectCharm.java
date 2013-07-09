package net.sf.anathema.character.main.magic.model.charm.special;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charmtree.ICharmLearnableArbitrator;
import net.sf.anathema.character.main.magic.model.charm.CharmSpecialist;

public interface IMultipleEffectCharm extends ISpecialCharm {

  SubEffects buildSubeffects(CharmSpecialist specialist, ICharmLearnableArbitrator arbitrator, Charm charm);
}