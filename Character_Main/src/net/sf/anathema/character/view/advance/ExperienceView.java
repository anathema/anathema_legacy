package net.sf.anathema.character.view.advance;

import net.sf.anathema.character.presenter.advance.ExperienceUpdateListener;

import javax.swing.table.DefaultTableModel;

public interface ExperienceView {

  void initGui(IExperienceViewProperties properties);

  void addExperienceConfigurationViewListener(ExperienceConfigurationViewListener listener);

  void setRemoveButtonEnabled(boolean enabled);

  void setTotalValueLabel(int overallExperiencePoints);

  //TODO (Swing->FX) TableModel
  DefaultTableModel getTableModel();

  void addEntry(int experiencePoints, String text);

  void clearEntries();

  void addUpdateListener(ExperienceUpdateListener experienceUpdateListener);
}