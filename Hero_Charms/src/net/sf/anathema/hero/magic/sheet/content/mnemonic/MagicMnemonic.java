package net.sf.anathema.hero.magic.sheet.content.mnemonic;

import net.sf.anathema.character.main.magic.IMagicStats;

import java.util.List;

public interface MagicMnemonic {
  void removePrintMagic(IMagicStats printMagic);

  List<IMagicStats> getRemainingPrintMagic();
}
