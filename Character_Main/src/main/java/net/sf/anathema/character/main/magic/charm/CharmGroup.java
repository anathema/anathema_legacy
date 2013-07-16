package net.sf.anathema.character.main.magic.charm;

import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.lib.util.Identifier;

public class CharmGroup implements ICharmGroup, Identifier {

  private final String id;
  private final Charm[] charms;
  private final CharacterType type;
  private final boolean isMartialArtsGroup;

  public CharmGroup(CharacterType type, String id, Charm[] charms, boolean isMartialArtsGroup) {
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
  public Charm[] getAllCharms() {
    return charms;
  }

  @Override
  public CharacterType getCharacterType() {
    return type;
  }

  @Override
  public boolean isMartialArtsGroup() {
    return isMartialArtsGroup;
  }

  @Override
  public boolean isCharmFromGroup(Charm charm) {
    boolean isOfGroupType =  charm.getCharacterType().equals(type);
    boolean isFromGroupWithId = charm.getGroupId().equals(id);
    return isOfGroupType && isFromGroupWithId;
  }
}