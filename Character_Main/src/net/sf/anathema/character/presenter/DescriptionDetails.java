package net.sf.anathema.character.presenter;

import net.sf.anathema.character.model.ICharacterDescription;
import net.sf.anathema.character.model.concept.ICharacterConcept;

public class DescriptionDetails {
  private final ICharacterDescription description;
  private final ICharacterConcept characterConcept;
  private final boolean hasAnima;

  public DescriptionDetails(ICharacterDescription description, ICharacterConcept characterConcept, boolean hasAnima) {
    this.description = description;
    this.characterConcept = characterConcept;
    this.hasAnima = hasAnima;
  }

  public ICharacterDescription getDescription() {
    return description;
  }

  public ICharacterConcept getCharacterConcept() {
    return characterConcept;
  }

  public boolean isHasAnima() {
    return hasAnima;
  }
}
