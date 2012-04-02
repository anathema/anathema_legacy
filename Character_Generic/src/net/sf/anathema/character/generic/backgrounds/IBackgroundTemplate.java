package net.sf.anathema.character.generic.backgrounds;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface IBackgroundTemplate extends ITraitType {

  LowerableState getExperiencedState();

  boolean acceptsTemplate(ITemplateType templateType, IExaltedEdition edition);
}