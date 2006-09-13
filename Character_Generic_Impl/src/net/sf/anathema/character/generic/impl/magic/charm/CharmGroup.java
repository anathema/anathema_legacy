package net.sf.anathema.character.generic.impl.magic.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.util.Identificate;

public class CharmGroup extends Identificate implements ICharmGroup {

  private final ICharm[] charms;
  private final CharacterType type;
  private final boolean isMartialArtsGroup;

  public CharmGroup(CharacterType type, String id, ICharm[] charms, boolean isMartialArtsGroup) {
    super(id);
    this.type = type;
    this.charms = charms;
    this.isMartialArtsGroup = isMartialArtsGroup;
  }

  public ICharm[] getAllCharms() {
    return charms;
  }

  public CharacterType getCharacterType() {
    return type;
  }

  public boolean isMartialArtsGroup() {
    return isMartialArtsGroup;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof CharmGroup)) {
      return false;
    }
    CharmGroup other = (CharmGroup) obj;
    return super.equals(obj) && other.getCharacterType() == getCharacterType();
  }
}