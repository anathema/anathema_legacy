package net.sf.anathema.character.generic.impl.traits;

import net.sf.anathema.character.generic.traits.ValuedTraitType;
import net.sf.anathema.character.generic.util.GenericTraitSorter;

import java.util.List;

public class ValueWeightGenericTraitSorter {

  private GenericTraitSorter sorter;

  public ValueWeightGenericTraitSorter() {
    this.sorter = new GenericTraitSorter();
  }

  public List<ValuedTraitType> sortDescending(ValuedTraitType[] traits) {
    int[] traitValues = new int[traits.length];
    for (int index = 0; index < traitValues.length; index++) {
      traitValues[index] = traits[index].getCurrentValue();
    }
    return sorter.sortDescending(traits, traitValues);
  }
}