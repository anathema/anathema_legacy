package net.sf.anathema.character.reporting.pdf.content.stats.anima;

import net.sf.anathema.hero.model.Hero;

public interface IAnimaTableRangeProvider {

  String getRange(int level, Hero hero);
}
