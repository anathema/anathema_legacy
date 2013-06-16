package net.sf.anathema.character.generic.impl.additional;

import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.lib.data.Range;

public interface IAdditionalTraitRange {

  TraitType getType();

  void modify(Range range);
}