package net.sf.anathema.hero.dummy.template;

import net.sf.anathema.character.main.template.ITraitTemplateFactory;
import net.sf.anathema.character.main.traits.DeprecatedTraitTemplate;
import net.sf.anathema.character.main.traits.EssenceTemplate;
import net.sf.anathema.character.main.traits.ITraitTemplate;
import net.sf.anathema.character.main.traits.types.AttributeType;
import net.sf.anathema.character.main.traits.types.VirtueType;

public class DummyTraitTemplateFactory implements ITraitTemplateFactory {

  @Override
  public ITraitTemplate createWillpowerTemplate() {
    return DeprecatedTraitTemplate.createStaticLimitedTemplate(2, 10);
  }

  @Override
  public ITraitTemplate createEssenceTemplate() {
    return EssenceTemplate.createExaltTemplate();
  }

  @Override
  public ITraitTemplate createVirtueTemplate(VirtueType type) {
    return DeprecatedTraitTemplate.createStaticLimitedTemplate(1, 5);
  }

  @Override
  public ITraitTemplate createAttributeTemplate(AttributeType type) {
    return DeprecatedTraitTemplate.createEssenceLimitedTemplate(1);
  }
}
