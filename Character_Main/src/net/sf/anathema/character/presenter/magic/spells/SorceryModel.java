package net.sf.anathema.character.presenter.magic.spells;

import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.model.ICharacter;

public class SorceryModel extends SpellModel {

  public SorceryModel(ICharacter character) {
    super(character);
  }

  @Override
  public CircleType[] getCircles() {
    return getSpellMagicTemplate().getSorceryCircles();
  }
}
