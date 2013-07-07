package net.sf.anathema.character.main.traits;

import net.sf.anathema.character.generic.template.ITraitTemplateFactory;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.main.traits.creation.TypedTraitTemplateFactory;

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