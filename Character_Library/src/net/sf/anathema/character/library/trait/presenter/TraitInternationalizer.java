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
    String name = reference.getName();
    if (name == null) {
      return resources.getString(id);
    }
    String subtraitNameKey = id + "." + name; //$NON-NLS-1$
    if (!resources.supportsKey(subtraitNameKey)) {
      return resources.getString("SubtraitMessage", id, name); //$NON-NLS-1$
    }
    String i18nedSubtraitName = resources.getString(subtraitNameKey);
    return resources.getString("SubtraitMessage", id, i18nedSubtraitName); //$NON-NLS-1$
  }
}