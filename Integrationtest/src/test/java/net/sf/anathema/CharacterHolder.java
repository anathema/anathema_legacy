package net.sf.anathema;

import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.main.model.concept.HeroConcept;
import net.sf.anathema.character.main.model.concept.HeroConceptFetcher;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.charm.CharmModel;

public class CharacterHolder {
  private ICharacter character;

  public void setCharacter(ICharacter character) {
    this.character = character;
  }

  public CharmModel getCharms() {
    return character.getCharms();
  }

  public HeroTemplate getCharacterTemplate() {
    return character.getTemplate();
  }

  public HeroConcept getCharacterConcept() {
    return HeroConceptFetcher.fetch(character);
  }

  public TraitMap getTraitConfiguration() {
    return TraitModelFetcher.fetch(character);
  }

  public ICharacter getCharacter() {
    return character;
  }
}