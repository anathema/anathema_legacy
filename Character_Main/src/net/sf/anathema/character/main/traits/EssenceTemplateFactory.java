package net.sf.anathema.character.main.traits;

import net.sf.anathema.character.generic.template.ITraitTemplateFactory;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.TraitType;
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