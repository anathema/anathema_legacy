package net.sf.anathema.character.presenter;

import net.sf.anathema.character.main.description.model.CharacterDescription;
import net.sf.anathema.character.model.concept.ICharacterConcept;

public class DescriptionDetails {
  private final CharacterDescription description;
  private final ICharacterConcept characterConcept;
  private final boolean hasAnima;

  public DescriptionDetails(CharacterDescription description, ICharacterConcept characterConcept, boolean hasAnima) {
    this.description = description;
    this.characterConcept = characterConcept;
    this.hasAnima = hasAnima;
  }

  public CharacterDescription getDescription() {
    return description;
  }

  public ICharacterConcept getCharacterConcept() {
    return characterConcept;
  }

  public boolean isHasAnima() {
    return hasAnima;
  }
}
