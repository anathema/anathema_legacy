package net.sf.anathema.character.persistence.charm;

import net.sf.anathema.lib.util.Identifier;

import java.util.Comparator;

public class IdentifiedComparator implements Comparator<Identifier> {
  @Override
  public int compare(Identifier o1, Identifier o2) {
    return o1.getId().compareTo(o2.getId());
  }
}
