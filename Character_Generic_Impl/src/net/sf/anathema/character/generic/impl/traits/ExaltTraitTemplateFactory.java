package net.sf.anathema.character.generic.impl.traits;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.generic.traits.types.YoziType;

public class ExaltTraitTemplateFactory implements ITraitTemplateFactory {

  public ITraitTemplate createBackgroundTemplate(IBackgroundTemplate template) {
    return SimpleTraitTemplate.createStaticLimitedTemplate(0, 5, template.getExperiencedState());
  }
  
  public ITraitTemplate createDefaultBackgroundTemplate() {
	return SimpleTraitTemplate.createStaticLimitedTemplate(0, 5, LowerableState.Default);
  }

  public ITraitTemplate createWillpowerTemplate() {
    return SimpleTraitTemplate.createStaticLimitedTemplate(2, 10);
  }

  public ITraitTemplate createEssenceTemplate() {
    return EssenceTemplate.createExaltTemplate();
  }

  public ITraitTemplate createVirtueTemplate(VirtueType type) {
    return SimpleTraitTemplate.createStaticLimitedTemplate(1, 5);
  }

  public ITraitTemplate createAttributeTemplate(AttributeType type) {
    return SimpleTraitTemplate.createEssenceLimitedTemplate(1);
  }

  public ITraitTemplate createAbilityTemplate(AbilityType type) {
    return SimpleTraitTemplate.createEssenceLimitedTemplate(0);
  }
  
  public ITraitTemplate createYoziTemplate(YoziType type) {
	return SimpleTraitTemplate.createEssenceLimitedTemplate(0);
  }
}
