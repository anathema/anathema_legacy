package net.sf.anathema.character.main.advance;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.spells.Spell;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.TraitMap;

public interface PointCostCalculator {

  int getAbilityCosts(Trait ability, boolean favored);

  int getAttributeCosts(Trait attribute, boolean favored);

  int getEssenceCosts(Trait essence);

  int getVirtueCosts(Trait virtue);

  int getWillpowerCosts(Trait willpower);

  double getSpecialtyCosts(boolean favored);

  int getSpellCosts(Hero hero, Spell spell, TraitMap traitMap);

  int getCharmCosts(Hero hero, Charm charm, TraitMap traitMap);
}