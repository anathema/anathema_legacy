package net.sf.anathema.hero.charms.model.special.subeffects;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.CharmSpecialist;
import net.sf.anathema.character.main.magic.charm.special.SubEffects;
import net.sf.anathema.character.main.magic.charmtree.ICharmLearnableArbitrator;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;

public interface IMultipleEffectCharm extends ISpecialCharm {

  SubEffects buildSubEffects(CharmSpecialist specialist, ICharmLearnableArbitrator arbitrator, Charm charm);
}