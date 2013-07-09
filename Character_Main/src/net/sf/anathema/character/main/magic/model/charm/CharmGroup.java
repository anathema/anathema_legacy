package net.sf.anathema.character.main.magic.model.charm;

import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.lib.util.Identifier;

public class CharmGroup implements ICharmGroup, Identifier {

  private final String id;
  private final Charm[] charms;
  private final ICharacterType type;
  private final boolean isMartialArtsGroup;

  public CharmGroup(ICharacterType type, String id, Charm[] charms, boolean isMartialArtsGroup) {
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
  public ICharacterType getCharacterType() {
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