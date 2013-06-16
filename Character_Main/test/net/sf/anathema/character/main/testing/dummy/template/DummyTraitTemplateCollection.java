package net.sf.anathema.character.main.testing.dummy.template;

import net.sf.anathema.character.generic.template.ITraitTemplateCollection;
import net.sf.anathema.character.generic.template.ITraitTemplateFactory;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.TraitType;

import static net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate.createStaticLimitedTemplate;

public class DummyTraitTemplateCollection implements ITraitTemplateCollection {
  @Override
  public ITraitTemplate getTraitTemplate(TraitType traitType) {
    return createStaticLimitedTemplate(0, 5);
  }

  @Override
  public ITraitTemplateFactory getTraitTemplateFactory() {
    return new DummyTraitTemplateFactory();
  }
}
