package net.sf.anathema.lib.compare;

import java.util.Comparator;

import net.sf.anathema.lib.util.IIdentificate;

public class IdentificateComparator implements Comparator<IIdentificate>{

  public int compare(IIdentificate o1, IIdentificate o2) {
    return o1.getId().compareTo(o2.getId());
  }
}