package net.sf.anathema.character.generic.impl.backgrounds;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.traits.types.ITraitTypeVisitor;
import net.sf.anathema.lib.util.Identificate;

public class CustomizedBackgroundTemplate extends Identificate implements IBackgroundTemplate {

  public CustomizedBackgroundTemplate(String name) {
    super(name);
  }

  public boolean acceptsTemplate(TemplateType templateType, IExaltedEdition edition) {
    return true;
  }

  public void accept(ITraitTypeVisitor visitor) {
    visitor.visitBackground(this);
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