package net.sf.anathema.character.presenter.util;

import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

import java.util.Comparator;

public class I18nComparator implements Comparator<Identifier> {

  private final String resourceKeyPrefix;
  private final Resources resources;

  public I18nComparator(Resources resources, String resourceKeyPrefix) {
    this.resources = resources;
    this.resourceKeyPrefix = resourceKeyPrefix;
  }

  @Override
  public int compare(Identifier identificate1, Identifier identificate2) {
    String firstDescription = resources.getString(resourceKeyPrefix + identificate1.getId());
    String secondDescription = resources.getString(resourceKeyPrefix + identificate2.getId());
    return firstDescription.compareToIgnoreCase(secondDescription);
  }
}