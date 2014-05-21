package net.sf.anathema.hero.traits.sheet.content;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.ValuedTraitType;
import net.sf.anathema.framework.environment.Resources;

public class PrintTrait implements NamedValue {

  private Resources resources;
  private ValuedTraitType trait;

  public PrintTrait(Resources resources, ValuedTraitType trait) {
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
