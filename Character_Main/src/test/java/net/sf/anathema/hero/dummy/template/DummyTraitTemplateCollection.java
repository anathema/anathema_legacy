package net.sf.anathema.hero.dummy.template;

import net.sf.anathema.character.main.template.ITraitTemplateCollection;
import net.sf.anathema.character.main.template.ITraitTemplateFactory;
import net.sf.anathema.character.main.traits.ITraitTemplate;
import net.sf.anathema.character.main.traits.TraitType;

import static net.sf.anathema.character.main.traits.SimpleTraitTemplate.createStaticLimitedTemplate;

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
