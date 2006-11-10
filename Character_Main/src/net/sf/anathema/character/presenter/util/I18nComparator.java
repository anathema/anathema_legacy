package net.sf.anathema.character.presenter.util;

import java.util.Comparator;

import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class I18nComparator implements Comparator<IIdentificate> {

  private final String resourceKeyPrefix;
  private final IResources resources;

  public I18nComparator(IResources resources, String resourceKeyPrefix) {
    this.resources = resources;
    this.resourceKeyPrefix = resourceKeyPrefix;
  }

  public int compare(IIdentificate identificate1, IIdentificate identificate2) {
    String firstDescription = resources.getString(resourceKeyPrefix + identificate1.getId());
    String secondDescription = resources.getString(resourceKeyPrefix + identificate2.getId());
    return firstDescription.compareToIgnoreCase(secondDescription);
  }
}