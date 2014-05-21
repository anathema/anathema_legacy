package net.sf.anathema.hero.framework.perspective.sheet;

import net.sf.anathema.character.framework.reporting.Report;
import net.sf.anathema.hero.model.Hero;

public interface DefaultReportFinder {
  Report getDefaultReport(Hero hero);
}