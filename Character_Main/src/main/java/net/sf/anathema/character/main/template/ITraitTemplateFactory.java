package net.sf.anathema.character.main.template;

import net.sf.anathema.character.main.traits.ITraitTemplate;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.character.main.traits.types.AttributeType;
import net.sf.anathema.character.main.traits.types.VirtueType;

public interface ITraitTemplateFactory {

  ITraitTemplate createWillpowerTemplate();

  ITraitTemplate createEssenceTemplate();

  ITraitTemplate createVirtueTemplate(VirtueType type);

  ITraitTemplate createAttributeTemplate(AttributeType type);

  ITraitTemplate createAbilityTemplate(AbilityType type);
}