package net.sf.anathema.character.main.presenter.magic.spells;

import net.sf.anathema.character.main.magic.model.spells.CircleType;
import net.sf.anathema.hero.model.Hero;

public class SorceryModel extends SpellModel {

  public SorceryModel(Hero hero) {
    super(hero);
  }

  @Override
  public CircleType[] getCircles() {
    return getSpellMagicTemplate().getSorceryCircles();
  }
}
