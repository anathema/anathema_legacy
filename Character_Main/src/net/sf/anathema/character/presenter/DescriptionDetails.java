package net.sf.anathema.character.presenter;

import net.sf.anathema.character.main.concept.model.CharacterConcept;
import net.sf.anathema.character.main.description.model.CharacterDescription;

public class DescriptionDetails {
  private final CharacterDescription description;
  private final CharacterConcept characterConcept;
  private final boolean hasAnima;

  public DescriptionDetails(CharacterDescription description, CharacterConcept characterConcept, boolean hasAnima) {
    this.description = description;
    this.characterConcept = characterConcept;
    this.hasAnima = hasAnima;
  }

  public CharacterDescription getDescription() {
    return description;
  }

  public CharacterConcept getCharacterConcept() {
    return characterConcept;
  }

  public boolean isHasAnima() {
    return hasAnima;
  }
}
