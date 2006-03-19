package net.sf.anathema.character.db.additional;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.magic.ISpell;

public class DefaultSorceryLearnPool extends AbstractSorceryLearnPool {

  public DefaultSorceryLearnPool(IBackgroundTemplate sorceryTemplate) {
    super(sorceryTemplate);
  }

  @Override
  protected boolean isTerrestrialSpellAllowed(ISpell spell) {
    return true;
  }
}