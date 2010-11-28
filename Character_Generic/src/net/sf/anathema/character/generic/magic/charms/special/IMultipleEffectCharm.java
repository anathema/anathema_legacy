package net.sf.anathema.character.generic.magic.charms.special;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;

public interface IMultipleEffectCharm extends ISpecialCharm {

  public ISubeffect[] buildSubeffects(
      IBasicCharacterData basicCharacterContext,
      ICharmLearnableArbitrator arbitrator,
      ICharm charm);
}