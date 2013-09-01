package net.sf.anathema.lib.compare;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.util.Identifier;

import java.util.Comparator;

public class I18nedIdentificateComparator implements Comparator<Identifier> {

  private final Resources resources;

  public I18nedIdentificateComparator(Resources resources) {
    this.resources = resources;
  }

  @Override
  public int compare(Identifier group1, Identifier group2) {
    String firstGroupName = getString(group1);
    String secondGroupName = getString(group2);
    return firstGroupName.compareToIgnoreCase(secondGroupName);
  }

  protected String getString(Identifier group1) {
    return resources.getString(group1.getId());
  }
}