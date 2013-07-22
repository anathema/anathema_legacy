package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.character.main.magic.charm.Charm;

public interface ICharmLearnableArbitrator {

  boolean isLearnable(Charm charm);
}