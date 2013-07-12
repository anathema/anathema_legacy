package net.sf.anathema.hero.magic.model;

import net.sf.anathema.character.main.magic.sheet.content.IMagicStats;

import java.util.List;

public interface PrintMagicProvider {

  void addPrintMagic(List<IMagicStats> printMagic);
}
