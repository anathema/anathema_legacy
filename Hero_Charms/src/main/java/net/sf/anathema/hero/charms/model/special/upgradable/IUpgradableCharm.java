package net.sf.anathema.hero.charms.model.special.upgradable;

import net.sf.anathema.hero.charms.model.special.subeffects.IMultipleEffectCharm;

public interface IUpgradableCharm extends IMultipleEffectCharm {

  boolean requiresBase();
}
