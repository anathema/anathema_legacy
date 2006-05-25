package net.sf.anathema.character.reporting.sheet.common;

import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.resources.IResources;

public class I18nNamedGenericTrait implements INamedGenericTrait {

  private final IResources resources;
  private final INamedGenericTrait trait;

  public I18nNamedGenericTrait(INamedGenericTrait trait, IResources resources) {
    this.trait = trait;
    this.resources = resources;
  }

  public String getName() {
    return resources.getString(getBasicTrait().getType().getId()) + " - " + trait.getName(); //$NON-NLS-1$
  }

  public int getCurrentValue() {
    return trait.getCurrentValue();
  }

  public ITraitType getType() {
    return trait.getType();
  }

  public IGenericTrait getBasicTrait() {
    return trait.getBasicTrait();
  }
}