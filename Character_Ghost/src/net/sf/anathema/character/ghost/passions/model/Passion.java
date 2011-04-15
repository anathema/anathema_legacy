package net.sf.anathema.character.ghost.passions.model;

import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.FriendlyValueChangeChecker;
import net.sf.anathema.character.library.trait.TraitType;
import net.sf.anathema.character.library.trait.rules.TraitRules;
import net.sf.anathema.character.library.trait.subtrait.AbstractSubTraitContainer;

public class Passion extends DefaultTrait implements IPassion {

  private final String subTraitName;
  private final PassionsContainer container;
  private final ITraitReference reference;

  public Passion(
      AbstractSubTraitContainer container,
      ITraitReference reference,
      String specialtyName,
      ITraitContext context) {
    super(new TraitRules(new TraitType("Passion (" + specialtyName + ")"), //$NON-NLS-1$
        SimpleTraitTemplate.createStaticLimitedTemplate(0, 5),
        context.getLimitationContext()), context, new FriendlyValueChangeChecker());
    this.container = (PassionsContainer)container;
    this.reference = reference;
    this.subTraitName = specialtyName;
  }

  public String getName() {
    return subTraitName;
  }

  public ITraitReference getTraitReference() {
    return reference;
  }

  public ITraitType getBasicTraitType() {
    return reference.getTraitType();
  }

  @Override
  public void setCurrentValue(int value) {
    int increment = value - getCurrentValue();
    if (container.getCurrentDotTotal() + increment <= container.getAllowedDots()) {
      super.setCurrentValue(value);
    }
    else {
      super.resetCurrentValue();
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Passion)) {
      return false;
    }
    Passion other = (Passion) obj;
    return super.equals(obj) && other.getName().equals(getName()) && other.getBasicTraitType() == getBasicTraitType();
  }

  @Override
  public int hashCode() {
    return getName().hashCode() + getBasicTraitType().hashCode();
  }
}