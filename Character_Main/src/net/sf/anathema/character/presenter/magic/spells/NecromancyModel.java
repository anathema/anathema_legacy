package net.sf.anathema.character.presenter.magic.spells;

import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.model.ICharacter;

public class NecromancyModel extends SpellModel {

  public NecromancyModel(ICharacter character) {
    super(character);
  }

  @Override
  public CircleType[] getCircles() {
    return getSpellMagicTemplate().getNecromancyCircles();
  }
}
