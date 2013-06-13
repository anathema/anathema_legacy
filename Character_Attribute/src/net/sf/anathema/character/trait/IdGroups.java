package net.sf.anathema.character.trait;

import net.sf.anathema.lib.util.Identified;

import java.util.List;

public class IdGroups {

  private final Identified id;
  private final List<Identified> members;

  public IdGroups(Identified id, List<Identified> members) {
    this.id = id;
    this.members = members;
  }
}
