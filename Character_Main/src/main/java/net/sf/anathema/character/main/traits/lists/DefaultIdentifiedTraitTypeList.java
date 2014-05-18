package net.sf.anathema.character.main.traits.lists;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.lib.util.Identifier;

public class DefaultIdentifiedTraitTypeList extends DefaultTraitTypeList implements IdentifiedTraitTypeList {

  private final Identifier groupId;

  public DefaultIdentifiedTraitTypeList(TraitType[] traitTypes, Identifier groupId) {
    super(traitTypes);
    this.groupId = groupId;
  }

  @Override
  public Identifier getListId() {
    return groupId;
  }
}