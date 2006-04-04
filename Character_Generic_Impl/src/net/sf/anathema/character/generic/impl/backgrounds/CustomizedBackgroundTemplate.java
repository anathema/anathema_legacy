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

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof CustomizedBackgroundTemplate)) {
      return false;
    }
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  public LowerableState getExperiencedState() {
    return LowerableState.LowerableRegain;
  }
}