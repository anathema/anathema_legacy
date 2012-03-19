package net.sf.anathema.character.presenter.magic.spells;

import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.model.ICharacterStatistics;

public class NecromancyModel extends SpellModel {

  public NecromancyModel(ICharacterStatistics statistics) {
    super(statistics);
  }

  @Override
  public CircleType[] getCircles() {
    return getSpellMagicTemplate().getNecromancyCircles();
  }
}
