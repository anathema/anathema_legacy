package net.sf.anathema.hero.advance.experience;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.spells.Spell;
import net.sf.anathema.hero.model.Hero;

public interface PointCostCalculator {

  int getSpellCosts(Hero hero, Spell spell);

  int getCharmCosts(Hero hero, Charm charm);
}