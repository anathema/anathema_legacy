package net.sf.anathema.character.generic.backgrounds;

import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface IBackgroundTemplate extends ITraitType {

  public boolean acceptsTemplate(TemplateType templateType);

  public LowerableState getExperiencedState();
}