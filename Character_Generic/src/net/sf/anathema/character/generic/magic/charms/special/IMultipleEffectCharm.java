package net.sf.anathema.character.generic.magic.charms.special;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;

public interface IMultipleEffectCharm extends ISpecialCharm {

  ISubeffect[] buildSubeffects(
          IBasicCharacterData basicCharacterContext,
          IGenericTraitCollection traitCollection,
          ICharmLearnableArbitrator arbitrator,
          ICharm charm);
}