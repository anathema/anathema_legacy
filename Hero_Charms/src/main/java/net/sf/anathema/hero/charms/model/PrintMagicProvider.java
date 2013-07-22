package net.sf.anathema.hero.charms.model;

import net.sf.anathema.hero.charms.sheet.content.IMagicStats;

import java.util.List;

public interface PrintMagicProvider {

  void addPrintMagic(List<IMagicStats> printMagic);
}
