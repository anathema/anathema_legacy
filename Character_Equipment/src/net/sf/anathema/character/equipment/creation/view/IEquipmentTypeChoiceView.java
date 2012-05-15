package net.sf.anathema.character.equipment.creation.view;

import net.sf.anathema.lib.gui.dialog.core.IPageContent;

import javax.swing.Action;
import java.awt.event.ItemListener;

public interface IEquipmentTypeChoiceView extends IPageContent {

  public void addStatisticsRow(String categoryLabel, Action action, String typeLabel, boolean isSelected);
  
  public void addCheckBox(String label, ItemListener listener, boolean isSelected);

  public void addHorizontalLine();
}