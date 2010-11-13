package net.sf.anathema.character.generic.magic.charms;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface IComboRestrictions {

  public abstract boolean combosAllAbilities();

  public abstract boolean isComboAllowed(boolean isAllowedByDefault);

  public boolean isRestrictedCharm(ICharm charm);

  public abstract ITraitType[] getRestrictedTraitTypes();
}