package net.sf.anathema.acceptance.fixture.character.traits;

import net.sf.anathema.character.library.trait.ITrait;

public class AcceptanceTrait {

  public String traitType;
  public int creationValue;
  public int experiencedValue;
  public int currentValue;
  
  public AcceptanceTrait(ITrait trait) {
    traitType = trait.getType().getId();
    creationValue = trait.getCreationValue();
    experiencedValue = trait.getExperiencedValue();
    currentValue = trait.getCurrentValue();
  }
}