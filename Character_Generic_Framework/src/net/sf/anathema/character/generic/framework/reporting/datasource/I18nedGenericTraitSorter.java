package net.sf.anathema.character.generic.framework.reporting.datasource;

import java.util.Arrays;
import java.util.Comparator;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.lib.resources.IResources;

public class I18nedGenericTraitSorter<T extends IGenericTrait> {

  public T[] sortAscending(T[] originalGroup, T[] emptyArray, final IResources resources) {
    Ensure.ensureTrue("No traits found.", originalGroup.length > 0); //$NON-NLS-1$
    Ensure.ensureTrue("Arrays must be of equal length", originalGroup.length == emptyArray.length); //$NON-NLS-1$
    System.arraycopy(originalGroup, 0, emptyArray, 0, emptyArray.length);
    Arrays.sort(emptyArray, new Comparator<T>() {
      public int compare(T group1, T group2) {
        String firstGroupName = getString(resources, group1);
        String secondGroupName = getString(resources, group2);
        return firstGroupName.compareToIgnoreCase(secondGroupName);
      }
    });
    return emptyArray;
  }

  protected String getString(final IResources resources, T group1) {
    return resources.getString(group1.getType().getId());
  }
}