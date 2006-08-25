package net.sf.anathema.character.library.trait.specialties;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.library.trait.subtrait.AbstractSubTraitContainer;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;

public class SpecialtiesContainer extends AbstractSubTraitContainer {

  public static final int ALLOWED_SPECIALTY_COUNT = 3;
  private final ITraitReference reference;
  private final ITraitContext traitContext;

  public SpecialtiesContainer(ITraitReference reference, ITraitContext traitContext) {
    this.reference = reference;
    this.traitContext = traitContext;
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
    return new Specialty(this, reference, name, traitContext);
  }  
}