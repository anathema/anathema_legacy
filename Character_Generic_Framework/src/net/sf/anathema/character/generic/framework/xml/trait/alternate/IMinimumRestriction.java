package net.sf.anathema.character.generic.framework.xml.trait.alternate;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface IMinimumRestriction {

  public boolean isFullfilledWithout(IGenericTraitCollection collection, ITraitType traitType);

  public int getStrictMinimumValue();

  public void addTraitType(ITraitType type);
}