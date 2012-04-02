package net.sf.anathema.character.generic.backgrounds;

import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.LowerableState;

public interface IBackgroundTemplate extends ITraitType {

  LowerableState getExperiencedState();

  boolean acceptsTemplate(ITemplateType templateType);
}