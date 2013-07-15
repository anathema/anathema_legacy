package net.sf.anathema.character.main.traits;

import net.sf.anathema.character.main.template.ITraitTemplateFactory;
import net.sf.anathema.character.main.traits.creation.TypedTraitTemplateFactory;

public class EssenceTemplateFactory implements TypedTraitTemplateFactory{
  private ITraitTemplateFactory traitTemplateFactory;

  public EssenceTemplateFactory(ITraitTemplateFactory traitTemplateFactory) {
    this.traitTemplateFactory = traitTemplateFactory;
  }

  @Override
  public ITraitTemplate create(TraitType type) {
    return traitTemplateFactory.createEssenceTemplate();
  }
}