package net.sf.anathema.character.main.traits.model;

import net.sf.anathema.lib.util.Identifier;

import java.util.List;

public class IdGroups {

  private final Identifier id;
  private final List<Identifier> members;

  public IdGroups(Identifier id, List<Identifier> members) {
    this.id = id;
    this.members = members;
  }
}
