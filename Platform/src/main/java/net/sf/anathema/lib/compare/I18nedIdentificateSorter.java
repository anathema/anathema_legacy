package net.sf.anathema.lib.compare;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class I18nedIdentificateSorter<T extends Identifier> {

  public List<T> sortAscending(List<T> originalGroup, final Resources resources) {
    ArrayList<T> listCopy = new ArrayList<>(originalGroup);
    Collections.sort(listCopy, new Comparator<T>() {
      @Override
      public int compare(T id1, T id2) {
        String firstGroupName = getString(resources, id1);
        String secondGroupName = getString(resources, id2);
        return firstGroupName.compareToIgnoreCase(secondGroupName);
      }
    });
    return listCopy;
  }

  private String getString(Resources resources, T identifier) {
    return resources.getString(identifier.getId());
  }
}