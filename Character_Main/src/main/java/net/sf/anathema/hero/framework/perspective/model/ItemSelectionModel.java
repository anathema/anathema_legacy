package net.sf.anathema.hero.framework.perspective.model;

import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.hero.creation.CharacterTemplateCreator;

import java.io.IOException;

public interface ItemSelectionModel {

  void saveCurrent() throws IOException;

  void whenCurrentSelectionBecomesDirty(ChangeListener listener);

  void whenCurrentSelectionBecomesClean(ChangeListener listener);

  void whenGetsSelection(ChangeListener listener);

  void whenCurrentSelectionBecomesExperienced(ChangeListener listener);

  void whenCurrentSelectionBecomesInexperienced(ChangeListener listener);

  void convertCurrentToExperienced();

  void printCurrentItemQuickly(Environment environment);

  void printCurrentItemInto(Report report, Environment environment);

  void createNew(CharacterTemplateCreator factory, Environment environment);

  void whenNewCharacterIsAdded(NewCharacterListener listener);

  void registerAllReportsOn(ReportRegister register, Environment environment);
}