package net.sf.anathema.hero.framework.perspective.model;

import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.resources.Resources;

import java.io.IOException;

public interface ItemSelectionModel {

  void saveCurrent() throws IOException;

  void whenCurrentSelectionBecomesDirty(ChangeListener listener);

  void whenCurrentSelectionBecomesClean(ChangeListener listener);

  void whenGetsSelection(ChangeListener listener);

  void whenCurrentSelectionBecomesExperienced(ChangeListener listener);

  void whenCurrentSelectionBecomesInexperienced(ChangeListener listener);

  void convertCurrentToExperienced();

  void printCurrentItemQuickly(Resources resources);

  void printCurrentItemInto(Report report, Resources resources);

  void createNew(Resources resources);

  void whenNewCharacterIsAdded(NewCharacterListener listener);

  void registerAllReportsOn(ReportRegister register, Resources resources);
}