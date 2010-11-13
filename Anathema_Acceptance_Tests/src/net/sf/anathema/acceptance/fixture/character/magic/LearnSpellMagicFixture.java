package net.sf.anathema.acceptance.fixture.character.magic;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.acceptance.fixture.character.util.AbstractCharacterRowEntryFixture;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.model.ISpellConfiguration;

public class LearnSpellMagicFixture extends AbstractCharacterRowEntryFixture {

  public String id;

  @Override
  public void enterRow() throws Exception {
    ISpellConfiguration spells = getCharacterStatistics().getSpells();
    ISpell spell = spells.getSpellById(id);
    Ensure.ensureNotNull(spell);
    spells.addSpells(new ISpell[] { spell });
  }
}