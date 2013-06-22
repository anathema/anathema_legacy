package net.sf.anathema.character.view.advance;

import javax.swing.table.DefaultTableModel;

public interface ExperienceView {

  void initGui(IExperienceViewProperties properties);

  void addExperienceConfigurationViewListener(IExperienceConfigurationViewListener listener);

  void setRemoveButtonEnabled(boolean enabled);

  void setTotalValueLabel(int overallExperiencePoints);

  //TODO (Swing->FX) TableModel
  DefaultTableModel getTableModel();

  void updateEntry(int rowIndex, int experiencePoints, String text);

  void removeEntry(int rowIndex);
}