package net.sf.anathema.character.generic.template;

import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.VirtueType;

public interface ITraitTemplateFactory {

  ITraitTemplate createWillpowerTemplate();

  ITraitTemplate createEssenceTemplate();

  ITraitTemplate createVirtueTemplate(VirtueType type);

  ITraitTemplate createAttributeTemplate(AttributeType type);

  ITraitTemplate createAbilityTemplate(AbilityType type);
}