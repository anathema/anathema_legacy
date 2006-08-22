package net.sf.anathema.character.library.trait.specialties;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.FriendlyValueChangeChecker;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.character.library.trait.subtrait.AbstractSubTraitContainer;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;

public class Specialty extends DefaultTrait implements ISubTrait {

  private final String subTraitName;
  private final AbstractSubTraitContainer container;
  private final ITraitType parentType;

  public Specialty(
      AbstractSubTraitContainer container,
      ITraitType type,
      String specialtyName,
      ITraitRules traitRules,
      ITraitValueStrategy traitValueStrategy) {
    super(
        traitRules.derive(OtherTraitType.Specialty, SimpleTraitTemplate.createStaticLimitedTemplate(0, 3)),
        traitValueStrategy,
        new FriendlyValueChangeChecker());
    this.container = container;
    this.parentType = type;
    this.subTraitName = specialtyName;
    this.setCurrentValue(1);
  }

  public String getName() {
    return subTraitName;
  }

  public ITraitType getBasicTraitType() {
    return parentType;
  }

  @Override
  public void setCurrentValue(int value) {
    int increment = value - getCurrentValue();
    if (container.getCurrentDotTotal() + increment <= SpecialtiesContainer.ALLOWED_SPECIALTY_COUNT) {
      super.setCurrentValue(value);
    }
    else {
      super.resetCurrentValue();
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Specialty)) {
      return false;
    }
    Specialty other = (Specialty) obj;
    return super.equals(obj) && other.getName().equals(getName()) && other.parentType == parentType;
  }

  @Override
  public int hashCode() {
    return getName().hashCode() + parentType.hashCode();
  }
}