package net.sf.anathema.character.infernal.traits;

import net.sf.anathema.character.generic.template.ITraitTemplateFactory;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.YoziType;
import net.sf.anathema.character.impl.model.traits.creation.TypedTraitTemplateFactory;

public class YoziTemplateFactory implements TypedTraitTemplateFactory {
  private ITraitTemplateFactory factory;

  public YoziTemplateFactory(ITraitTemplateFactory factory) {
    this.factory = factory;
  }

  @Override
  public ITraitTemplate create(ITraitType type) {
    return factory.createYoziTemplate((YoziType) type);
  }
}