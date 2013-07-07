package net.sf.anathema.character.main.testing.dummy.template;

import net.sf.anathema.character.main.traits.EssenceTemplate;
import net.sf.anathema.character.main.traits.SimpleTraitTemplate;
import net.sf.anathema.character.main.template.ITraitTemplateFactory;
import net.sf.anathema.character.main.traits.ITraitTemplate;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.character.main.traits.types.AttributeType;
import net.sf.anathema.character.main.traits.types.VirtueType;

public class DummyTraitTemplateFactory implements ITraitTemplateFactory {

  @Override
  public ITraitTemplate createWillpowerTemplate() {
    return SimpleTraitTemplate.createStaticLimitedTemplate(2, 10);
  }

  @Override
  public ITraitTemplate createEssenceTemplate() {
    return EssenceTemplate.createExaltTemplate();
  }

  @Override
  public ITraitTemplate createVirtueTemplate(VirtueType type) {
    return SimpleTraitTemplate.createStaticLimitedTemplate(1, 5);
  }

  @Override
  public ITraitTemplate createAttributeTemplate(AttributeType type) {
    return SimpleTraitTemplate.createEssenceLimitedTemplate(1);
  }

  @Override
  public ITraitTemplate createAbilityTemplate(AbilityType type) {
    return SimpleTraitTemplate.createEssenceLimitedTemplate(0);
  }

}
