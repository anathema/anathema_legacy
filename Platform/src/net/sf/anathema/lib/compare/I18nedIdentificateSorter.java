package net.sf.anathema.lib.compare;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

import java.util.Arrays;
import java.util.Comparator;

public class I18nedIdentificateSorter<T extends Identifier> {

  public T[] sortAscending(T[] originalGroup, T[] emptyArray, final Resources resources) {
    Preconditions.checkState(originalGroup.length == emptyArray.length, "Arrays must be of equal length");
    System.arraycopy(originalGroup, 0, emptyArray, 0, emptyArray.length);
    Arrays.sort(emptyArray, new Comparator<T>() {
      @Override
      public int compare(T group1, T group2) {
        String firstGroupName = getString(resources, group1);
        String secondGroupName = getString(resources, group2);
        return firstGroupName.compareToIgnoreCase(secondGroupName);
      }
    });
    return emptyArray;
  }

  protected String getString(Resources resources, T group1) {
    return resources.getString(group1.getId());
  }
}