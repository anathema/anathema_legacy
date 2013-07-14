package net.sf.anathema.character.main.traits;

import net.sf.anathema.character.main.template.ITraitTemplateFactory;
import net.sf.anathema.character.main.traits.creation.TypedTraitTemplateFactory;
import net.sf.anathema.character.main.traits.types.AbilityType;

public class AbilityTemplateFactory implements TypedTraitTemplateFactory {
  private ITraitTemplateFactory factory;

  public AbilityTemplateFactory(ITraitTemplateFactory factory) {
    this.factory = factory;
  }

  @Override
  public ITraitTemplate create(TraitType type) {
    return factory.createAbilityTemplate((AbilityType) type);
  }
}