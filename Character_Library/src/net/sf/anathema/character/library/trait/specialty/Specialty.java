package net.sf.anathema.character.library.trait.specialty;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.FriendlyValueChangeChecker;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.rules.ITraitRules;

public class Specialty extends DefaultTrait implements ISpecialty {

  private final ITrait trait;
  private final String specialtyName;

  public Specialty(ITrait ability, String specialtyName, ITraitRules traitRules, ITraitValueStrategy traitValueStrategy) {
    super(
        traitRules.derive(OtherTraitType.Specialty, SimpleTraitTemplate.createStaticLimitedTemplate(0, 3)),
        traitValueStrategy,
        new FriendlyValueChangeChecker());
    this.trait = ability;
    this.specialtyName = specialtyName;
    this.setCurrentValue(1);
  }

  public String getName() {
    return specialtyName;
  }

  public ITrait getBasicTrait() {
    return trait;
  }

  @Override
  public void setCurrentValue(int value) {
    int increment = value - getCurrentValue();
    if (trait.getSpecialtiesContainer().getCurrentSpecialtyCount() + increment <= SpecialtiesContainer.ALLOWED_SPECIALTY_COUNT) {
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
    return super.equals(obj)
        && other.getName().equals(getName())
        && other.getBasicTrait().getType() == getBasicTrait().getType();
  }

  @Override
  public int hashCode() {
    return getName().hashCode();
  }
}