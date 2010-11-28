package net.sf.anathema.character.sidereal.template.trait;

import net.sf.anathema.character.generic.impl.traits.ExaltTraitTemplateFactory;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class SiderealTraitTemplateCollection extends ExaltTraitTemplateFactory {

  private final SiderealAbilityTemplateCreationVisitor visitor = new SiderealAbilityTemplateCreationVisitor();

  @Override
  public ITraitTemplate createAbilityTemplate(AbilityType type) {
    type.accept(visitor);
    return visitor.getTraitTemplate();
  }
}