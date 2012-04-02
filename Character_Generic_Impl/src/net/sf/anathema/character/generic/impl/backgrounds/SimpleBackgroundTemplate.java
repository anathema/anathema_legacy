package net.sf.anathema.character.generic.impl.backgrounds;

import net.sf.anathema.character.generic.template.ITemplateType;

public final class SimpleBackgroundTemplate extends CalculatedLowerableBackground {

  public SimpleBackgroundTemplate(String id) {
    super(id);
  }

  @Override
  public boolean acceptsTemplate(ITemplateType templateType) {
    return true;
  }
}