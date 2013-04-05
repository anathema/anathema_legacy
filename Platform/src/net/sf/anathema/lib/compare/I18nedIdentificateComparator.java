package net.sf.anathema.lib.compare;

import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identified;

import java.util.Comparator;

public class I18nedIdentificateComparator implements Comparator<Identified> {

  private final Resources resources;

  public I18nedIdentificateComparator(Resources resources) {
    this.resources = resources;
  }

  @Override
  public int compare(Identified group1, Identified group2) {
    String firstGroupName = getString(group1);
    String secondGroupName = getString(group2);
    return firstGroupName.compareToIgnoreCase(secondGroupName);
  }

  protected String getString(Identified group1) {
    return resources.getString(group1.getId());
  }
}