package net.sf.anathema.character.presenter;

import net.sf.anathema.character.main.model.concept.CharacterConcept;
import net.sf.anathema.character.main.model.description.HeroDescription;

public class DescriptionDetails {
  private final HeroDescription description;
  private final CharacterConcept characterConcept;
  private final boolean hasAnima;

  public DescriptionDetails(HeroDescription description, CharacterConcept characterConcept, boolean hasAnima) {
    this.description = description;
    this.characterConcept = characterConcept;
    this.hasAnima = hasAnima;
  }

  public HeroDescription getDescription() {
    return description;
  }

  public CharacterConcept getCharacterConcept() {
    return characterConcept;
  }

  public boolean isHasAnima() {
    return hasAnima;
  }
}
