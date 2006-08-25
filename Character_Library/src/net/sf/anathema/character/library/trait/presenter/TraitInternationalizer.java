package net.sf.anathema.character.library.trait.presenter;

import net.sf.anathema.character.library.trait.specialties.ITraitReference;
import net.sf.anathema.lib.resources.IResources;

public class TraitInternationalizer {

  private final IResources resources;

  public TraitInternationalizer(IResources resources) {
    this.resources = resources;
  }

  public String getName(ITraitReference reference) {
    String id = reference.getTraitType().getId();
    if (reference.getName() == null) {
      return resources.getString(id);
    }
    return resources.getString("SubtraitMessage", id, reference.getName()); //$NON-NLS-1$
  }
}