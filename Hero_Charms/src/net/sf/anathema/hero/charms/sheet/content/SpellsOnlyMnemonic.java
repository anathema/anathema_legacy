package net.sf.anathema.hero.charms.sheet.content;

import net.sf.anathema.character.main.magic.IMagicStats;
import net.sf.anathema.hero.charms.sheet.content.mnemonic.AbstractMagicMnemonic;

import java.util.List;

public class SpellsOnlyMnemonic extends AbstractMagicMnemonic {

  public SpellsOnlyMnemonic(List<IMagicStats> printMagic) {
    super(printMagic);
  }
}
