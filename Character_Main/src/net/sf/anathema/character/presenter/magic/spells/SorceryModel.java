package net.sf.anathema.character.presenter.magic.spells;

import net.sf.anathema.character.generic.magic.spells.CircleType;
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
