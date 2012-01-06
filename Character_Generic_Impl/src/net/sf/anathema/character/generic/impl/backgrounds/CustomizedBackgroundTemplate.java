package net.sf.anathema.character.generic.impl.backgrounds;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.traits.LowerableState;

public class CustomizedBackgroundTemplate extends AbstractBackgroundTemplate {

  public CustomizedBackgroundTemplate(String name) {
    super(name);
  }

  public boolean acceptsTemplate(ITemplateType templateType, IExaltedEdition edition) {
    return true;
  }

  public LowerableState getExperiencedState() {
    return LowerableState.LowerableRegain;
  }
}