package net.sf.anathema.hero.spells.model;

import net.sf.anathema.character.main.magic.model.spells.CircleType;
import net.sf.anathema.character.main.template.magic.ISpellMagicTemplate;
import net.sf.anathema.hero.model.Hero;

public abstract class CircleModel {

  private Hero hero;

  protected CircleModel(Hero hero) {
    this.hero = hero;
  }

  public abstract CircleType[] getCircles();

  protected final ISpellMagicTemplate getSpellMagicTemplate() {
    return hero.getTemplate().getMagicTemplate().getSpellMagic();
  }
}
