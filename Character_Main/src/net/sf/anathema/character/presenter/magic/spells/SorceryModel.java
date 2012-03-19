package net.sf.anathema.character.presenter.magic.spells;

import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.model.ICharacterStatistics;

public class SorceryModel extends SpellModel {

  public SorceryModel(ICharacterStatistics statistics) {
    super(statistics);
  }

  @Override
  public CircleType[] getCircles() {
    return getSpellMagicTemplate().getSorceryCircles();
  }
}
