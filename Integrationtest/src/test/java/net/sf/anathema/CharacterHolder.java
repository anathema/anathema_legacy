package net.sf.anathema;

import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.model.charms.CharmsModel;
import net.sf.anathema.character.main.model.charms.CharmsModelFetcher;
import net.sf.anathema.character.main.model.concept.HeroConcept;
import net.sf.anathema.character.main.model.concept.HeroConceptFetcher;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
import net.sf.anathema.character.main.Character;

public class CharacterHolder {
  private Character character;

  public void setCharacter(Character character) {
    this.character = character;
  }

  public CharmsModel getCharms() {
    return CharmsModelFetcher.fetch(character);
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

  public Character getCharacter() {
    return character;
  }
}