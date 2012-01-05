package net.sf.anathema.character.generic.impl.backgrounds;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateType;

public class EditionSpecificBackgroundTemplate extends CalculatedLowerableBackground {

  private final IExaltedEdition allowedEdition;

  public EditionSpecificBackgroundTemplate(String id, IExaltedEdition edition) {
    super(id);
    this.allowedEdition = edition;
  }

  public boolean acceptsTemplate(ITemplateType templateType, IExaltedEdition edition) {
    return this.allowedEdition == edition;
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}