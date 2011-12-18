package net.sf.anathema.character.reporting.pdf.content.general;

import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.resources.IResources;

public class PrintTrait implements NamedValue {

  private IResources resources;
  private IGenericTrait trait;

  public PrintTrait(IResources resources, IGenericTrait trait) {
    this.resources = resources;
    this.trait = trait;
  }

  public String getLabel() {
    String typeId = getTypeId();
    return resources.getString(typeId);
  }

  private String getTypeId() {
    ITraitType type = trait.getType();
    return type.getId();
  }

  public int getValue() {
    return trait.getCurrentValue();
  }
}
