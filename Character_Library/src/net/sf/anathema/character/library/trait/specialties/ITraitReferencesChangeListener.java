package net.sf.anathema.character.library.trait.specialties;

import net.sf.anathema.character.generic.framework.ITraitReference;

public interface ITraitReferencesChangeListener {

  void referenceAdded(ITraitReference reference);

  void referenceRemoved(ITraitReference reference);

}
