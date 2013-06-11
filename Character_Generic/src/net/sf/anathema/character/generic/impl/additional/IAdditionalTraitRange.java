package net.sf.anathema.character.generic.impl.additional;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.data.Range;

public interface IAdditionalTraitRange {

  ITraitType getType();

  void modify(Range range);
}