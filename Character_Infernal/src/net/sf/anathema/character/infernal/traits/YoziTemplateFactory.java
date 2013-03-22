package net.sf.anathema.character.infernal.traits;

import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.impl.model.traits.creation.TypedTraitTemplateFactory;

public class YoziTemplateFactory implements TypedTraitTemplateFactory {

  @Override
  public ITraitTemplate create(ITraitType type) {
    return SimpleTraitTemplate.createEssenceLimitedTemplate(0);
  }
}