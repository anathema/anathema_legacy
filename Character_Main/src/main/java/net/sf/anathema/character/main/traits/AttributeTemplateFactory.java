package net.sf.anathema.character.main.traits;

import net.sf.anathema.character.main.template.ITraitTemplateFactory;
import net.sf.anathema.character.main.traits.creation.TypedTraitTemplateFactory;
import net.sf.anathema.character.main.traits.types.AttributeType;

public class AttributeTemplateFactory implements TypedTraitTemplateFactory {
  private ITraitTemplateFactory traitTemplateFactory;

  public AttributeTemplateFactory(ITraitTemplateFactory traitTemplateFactory) {
    this.traitTemplateFactory = traitTemplateFactory;
  }

  @Override
  public ITraitTemplate create(TraitType type) {
    return traitTemplateFactory.createAttributeTemplate((AttributeType) type);
  }
}