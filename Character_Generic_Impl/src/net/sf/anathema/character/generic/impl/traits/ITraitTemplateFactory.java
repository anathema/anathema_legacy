package net.sf.anathema.character.generic.impl.traits;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.generic.traits.types.YoziType;

public interface ITraitTemplateFactory {

  public ITraitTemplate createBackgroundTemplate(IBackgroundTemplate template);
  
  public ITraitTemplate createDefaultBackgroundTemplate();

  public ITraitTemplate createWillpowerTemplate();

  public ITraitTemplate createEssenceTemplate();

  public ITraitTemplate createVirtueTemplate(VirtueType type);

  public ITraitTemplate createAttributeTemplate(AttributeType type);

  public ITraitTemplate createAbilityTemplate(AbilityType type);
  
  public ITraitTemplate createYoziTemplate(YoziType type);
}
