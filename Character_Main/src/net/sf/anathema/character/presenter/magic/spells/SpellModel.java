package net.sf.anathema.character.presenter.magic.spells;

import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.model.ICharacterStatistics;

public abstract class SpellModel {

  private ICharacterStatistics statistics;

  protected SpellModel(ICharacterStatistics statistics) {
    this.statistics = statistics;
  }

  public abstract CircleType[] getCircles();

  protected final ISpellMagicTemplate getSpellMagicTemplate() {
    return statistics.getCharacterTemplate().getMagicTemplate().getSpellMagic();
  }
}
