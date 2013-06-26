package net.sf.anathema.character.generic.impl.magic.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.util.Identified;

public class CharmGroup implements ICharmGroup, Identified {

  private final String id;
  private final ICharm[] charms;
  private final ICharacterType type;
  private final boolean isMartialArtsGroup;

  public CharmGroup(ICharacterType type, String id, ICharm[] charms, boolean isMartialArtsGroup) {
    this.id = id;
    this.type = type;
    this.charms = charms;
    this.isMartialArtsGroup = isMartialArtsGroup;
  }

  @Override
  public final String getId() {
    return id;
  }

  @Override
  public String toString() {
    return id;
  }

  @Override
  public ICharm[] getAllCharms() {
    return charms;
  }

  @Override
  public ICharacterType getCharacterType() {
    return type;
  }

  @Override
  public boolean isMartialArtsGroup() {
    return isMartialArtsGroup;
  }

  //TODO (#343) CharacterType Identity => Equality
  @Override
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != getClass()) {
      return false;
    }
    CharmGroup other = (CharmGroup) obj;
    return super.equals(obj) && other.getCharacterType() == getCharacterType();
  }
  
  @Override
  public int hashCode() {
    int hash = 7 * super.hashCode();
    hash += 11 * System.identityHashCode(getCharacterType());
    return hash;
  }
}