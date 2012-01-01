package net.sf.anathema.character.ghost.fetters.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.FriendlyValueChangeChecker;
import net.sf.anathema.character.library.trait.TraitType;
import net.sf.anathema.character.library.trait.rules.TraitRules;

public class Fetter extends DefaultTrait {

  private final IGhostFettersModel model;
  private final String subTraitName;

  public Fetter(String fetterName, ITraitContext context, IGhostFettersModel model) {
    super(new TraitRules(new TraitType("Fetter (" + fetterName + ")"), //$NON-NLS-1$
      SimpleTraitTemplate.createStaticLimitedTemplate(0, 5), context.getLimitationContext()), context, new FriendlyValueChangeChecker());
    this.subTraitName = fetterName;
    this.model = model;
  }

  public String getName() {
    return subTraitName;
  }

  @Override
  public void setCurrentValue(int value) {
    int increment = value - getCurrentValue();
    if (model.getCurrentFetterDots() + increment <= model.getMaxFetterDots()) {
      super.setCurrentValue(value);
    }
    else {
      super.resetCurrentValue();
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Fetter)) {
      return false;
    }
    Fetter other = (Fetter) obj;
    return super.equals(obj) && other.getName().equals(getName());
  }
}
