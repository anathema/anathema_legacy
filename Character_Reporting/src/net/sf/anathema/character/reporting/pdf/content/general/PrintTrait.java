package net.sf.anathema.character.reporting.pdf.content.general;

import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.lib.resources.Resources;

public class PrintTrait implements NamedValue {

  private Resources resources;
  private GenericTrait trait;

  public PrintTrait(Resources resources, GenericTrait trait) {
    this.resources = resources;
    this.trait = trait;
  }

  @Override
  public String getLabel() {
    String typeId = getTypeId();
    return resources.getString(typeId);
  }

  private String getTypeId() {
    TraitType type = trait.getType();
    return type.getId();
  }

  @Override
  public int getValue() {
    return trait.getCurrentValue();
  }
}
