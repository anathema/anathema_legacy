package net.sf.anathema.lib.compare;

import java.util.Comparator;

import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class I18nedIdentificateComparator implements Comparator<IIdentificate> {

  private final IResources resources;

  public I18nedIdentificateComparator(IResources resources) {
    this.resources = resources;
  }

  public int compare(IIdentificate group1, IIdentificate group2) {
    String firstGroupName = getString(group1);
    String secondGroupName = getString(group2);
    return firstGroupName.compareToIgnoreCase(secondGroupName);
  }

  protected String getString(IIdentificate group1) {
    return resources.getString(group1.getId());
  }
}