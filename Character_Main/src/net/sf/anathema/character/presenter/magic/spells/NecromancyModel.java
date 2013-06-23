package net.sf.anathema.character.presenter.magic.spells;

import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.hero.model.Hero;

public class NecromancyModel extends SpellModel {

  public NecromancyModel(Hero hero) {
    super(hero);
  }

  @Override
  public CircleType[] getCircles() {
    return getSpellMagicTemplate().getNecromancyCircles();
  }
}
