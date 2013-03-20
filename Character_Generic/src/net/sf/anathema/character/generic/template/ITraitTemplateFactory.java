package net.sf.anathema.character.generic.template;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.generic.traits.types.YoziType;

public interface ITraitTemplateFactory {

  ITraitTemplate createBackgroundTemplate(IBackgroundTemplate template);
  
  ITraitTemplate createDefaultBackgroundTemplate();

  ITraitTemplate createWillpowerTemplate();

  ITraitTemplate createEssenceTemplate();

  ITraitTemplate createVirtueTemplate(VirtueType type);

  ITraitTemplate createAttributeTemplate(AttributeType type);

  ITraitTemplate createAbilityTemplate(AbilityType type);
  
  ITraitTemplate createYoziTemplate(YoziType type);
}