package net.sf.anathema.character.impl.persistence.charm;

import net.sf.anathema.lib.util.Identified;

import java.util.Comparator;

public class IdentifiedComparator implements Comparator<Identified> {
  @Override
  public int compare(Identified o1, Identified o2) {
    return o1.getId().compareTo(o2.getId());
  }
}
