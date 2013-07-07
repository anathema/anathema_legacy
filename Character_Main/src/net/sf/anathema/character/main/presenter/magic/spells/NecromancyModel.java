package net.sf.anathema.character.main.presenter.magic.spells;

import net.sf.anathema.character.main.magic.model.spells.CircleType;
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
