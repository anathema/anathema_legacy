package net.sf.anathema.character.generic.impl.traits;

import java.util.List;

import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.util.GenericTraitSorter;

public class ValueWeightGenericTraitSorter {

  private GenericTraitSorter sorter;

  public ValueWeightGenericTraitSorter() {
    this.sorter = new GenericTraitSorter();
  }

  public List<IGenericTrait> sortDescending(IGenericTrait[] traits) {
    int[] traitValues = new int[traits.length];
    for (int index = 0; index < traitValues.length; index++) {
      traitValues[index] = traits[index].getCurrentValue();
    }
    return sorter.sortDescending(traits, traitValues);
  }
}