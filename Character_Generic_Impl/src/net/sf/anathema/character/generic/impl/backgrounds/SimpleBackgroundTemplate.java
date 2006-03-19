package net.sf.anathema.character.generic.impl.backgrounds;

import net.sf.anathema.character.generic.template.TemplateType;

public final class SimpleBackgroundTemplate extends CalculatedLowerableBackground {

  public SimpleBackgroundTemplate(String id) {
    super(id);
  }

  public boolean acceptsTemplate(TemplateType templateType) {
    return true;
  }
}