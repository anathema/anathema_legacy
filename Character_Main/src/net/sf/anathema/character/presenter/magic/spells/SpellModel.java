package net.sf.anathema.character.presenter.magic.spells;

import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.model.ICharacter;

public abstract class SpellModel {

  private ICharacter character;

  protected SpellModel(ICharacter character) {
    this.character = character;
  }

  public abstract CircleType[] getCircles();

  protected final ISpellMagicTemplate getSpellMagicTemplate() {
    return character.getTemplate().getMagicTemplate().getSpellMagic();
  }
}
