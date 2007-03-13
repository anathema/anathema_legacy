package net.sf.anathema.character.db.magic;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffect;

public interface IElementalSubeffect extends ISubeffect {

  public boolean matches(ICasteType casteType);
}