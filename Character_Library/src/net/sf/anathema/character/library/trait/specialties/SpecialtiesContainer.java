package net.sf.anathema.character.library.trait.specialties;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.character.library.trait.subtrait.AbstractSubTraitContainer;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;

public class SpecialtiesContainer extends AbstractSubTraitContainer {

  public static final int ALLOWED_SPECIALTY_COUNT = 3;
  private final ITraitRules traitRules;
  private final ITraitValueStrategy traitValueStrategy;
  private final ITraitType type;

  public SpecialtiesContainer(ITraitType type, ITraitRules traitRules, ITraitValueStrategy traitValueStrategy) {
    this.type = type;
    this.traitRules = traitRules;
    this.traitValueStrategy = traitValueStrategy;
  }

  @Override
  public void handleAdditionOfContainedEquivalent(ISubTrait subTrait) {
    int maxAddition = ALLOWED_SPECIALTY_COUNT - getCurrentDotTotal();
    int addition = Math.min(subTrait.getCurrentValue(), maxAddition);
    subTrait.setCurrentValue(subTrait.getCurrentValue() + addition);
  }

  @Override
  public boolean isNewSubTraitAllowed() {
    return getCurrentDotTotal() < ALLOWED_SPECIALTY_COUNT;
  }

  @Override
  public ISubTrait createSubTrait(String name) {
    return new Specialty(this, type, name, traitRules, traitValueStrategy);
  }
}