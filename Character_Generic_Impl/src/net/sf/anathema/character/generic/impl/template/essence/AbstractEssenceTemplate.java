package net.sf.anathema.character.generic.impl.template.essence;

import java.util.List;

import net.sf.anathema.character.generic.impl.traits.ValueWeightGenericTraitSorter;
import net.sf.anathema.character.generic.template.essence.IEssenceTemplate;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;

public abstract class AbstractEssenceTemplate implements IEssenceTemplate {

  protected final List<IGenericTrait> sortVirtuesDescending(IGenericTrait[] virtues) {
    return new ValueWeightGenericTraitSorter().sortDescending(virtues);
  }

  protected final IGenericTrait getTrait(ITraitType traitType, IGenericTrait[] traits) {
    for (IGenericTrait trait : traits) {
      if (trait.getType() == traitType) {
        return trait;
      }
    }
    throw new IllegalArgumentException("No trait given for type " + traitType); //$NON-NLS-1$
  }
}