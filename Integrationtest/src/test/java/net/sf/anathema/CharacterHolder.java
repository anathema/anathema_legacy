package net.sf.anathema;

import com.google.inject.Singleton;
import net.sf.anathema.character.framework.Character;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;

@Singleton
public class CharacterHolder {
  private Character character;

  public void setCharacter(Character character) {
    this.character = character;
  }

  public CharmsModel getCharms() {
    return CharmsModelFetcher.fetch(character);
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