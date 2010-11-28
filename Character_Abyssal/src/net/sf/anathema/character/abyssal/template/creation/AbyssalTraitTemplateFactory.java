package net.sf.anathema.character.abyssal.template.creation;

import net.sf.anathema.character.generic.impl.traits.ExaltTraitTemplateFactory;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.types.AttributeType;

public class AbyssalTraitTemplateFactory extends ExaltTraitTemplateFactory {

  @Override
  public ITraitTemplate createAttributeTemplate(AttributeType type) {
    if (type == AttributeType.Appearance) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(0, 1, LowerableState.LowerableLoss);
    }
    return super.createAttributeTemplate(type);
  }
}