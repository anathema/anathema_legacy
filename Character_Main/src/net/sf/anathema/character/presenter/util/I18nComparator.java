package net.sf.anathema.character.presenter.util;

import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identified;

import java.util.Comparator;

public class I18nComparator implements Comparator<Identified> {

  private final String resourceKeyPrefix;
  private final IResources resources;

  public I18nComparator(IResources resources, String resourceKeyPrefix) {
    this.resources = resources;
    this.resourceKeyPrefix = resourceKeyPrefix;
  }

  @Override
  public int compare(Identified identificate1, Identified identificate2) {
    String firstDescription = resources.getString(resourceKeyPrefix + identificate1.getId());
    String secondDescription = resources.getString(resourceKeyPrefix + identificate2.getId());
    return firstDescription.compareToIgnoreCase(secondDescription);
  }
}