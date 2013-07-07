package net.sf.anathema.character.main.advance;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.hero.model.Hero;

public interface IPointCostCalculator {

  int getAbilityCosts(Trait ability, boolean favored);

  int getAttributeCosts(Trait attribute, boolean favored);

  int getEssenceCosts(Trait essence);

  int getVirtueCosts(Trait virtue);

  int getWillpowerCosts(Trait willpower);

  double getSpecialtyCosts(boolean favored);

  int getSpellCosts(Hero hero, ISpell spell, TraitMap traitMap);

  int getCharmCosts(Hero hero, ICharm charm, TraitMap traitMap);
}