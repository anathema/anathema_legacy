package net.sf.anathema;

import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.main.concept.model.CharacterConcept;
import net.sf.anathema.character.main.concept.model.CharacterConceptFetcher;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

public class CharacterHolder {
  private ICharacter character;

  public void setCharacter(ICharacter character) {
    this.character = character;
  }

  public ICharmConfiguration getCharms() {
    return character.getCharms();
  }

  public ICharacterTemplate getCharacterTemplate() {
    return character.getCharacterTemplate();
  }

  public CharacterConcept getCharacterConcept() {
    return CharacterConceptFetcher.fetch(character);
  }

  public ICoreTraitConfiguration getTraitConfiguration() {
    return character.getTraitConfiguration();
  }

  public ICharacter getCharacter() {
    return character;
  }
}