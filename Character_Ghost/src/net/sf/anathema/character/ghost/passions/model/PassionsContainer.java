package net.sf.anathema.character.ghost.passions.model;

import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.library.trait.subtrait.AbstractSubTraitContainer;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;

public class PassionsContainer extends AbstractSubTraitContainer
{
  private final IGenericTrait parentTrait;

  private final ITraitReference reference;
  private final ITraitContext traitContext;
  private final IGhostPassionsModel model;

  public PassionsContainer(IGenericTrait parentTrait,
		  ITraitReference reference,
		  ITraitContext traitContext,
		  IGhostPassionsModel model) {
    this.parentTrait = parentTrait;
    this.reference = reference;
    this.traitContext = traitContext;
    this.model = model;
  }

  @Override
  public void handleAdditionOfContainedEquivalent(ISubTrait subTrait) {
    int maxAddition = getAllowedDots() - getCurrentDotTotal();
    int addition = Math.min(1, maxAddition);
    subTrait.setCurrentValue(subTrait.getCurrentValue() + addition);
  }

  @Override
  public boolean isNewSubTraitAllowed() {
    return getCurrentDotTotal() < getAllowedDots();
  }
  
  public int getAllowedDots()
  {
	  return parentTrait.getCurrentValue();
  }

  @Override
  public ISubTrait createSubTrait(String name) {
    return new Passion(this, model, reference, name, traitContext);
  }
}