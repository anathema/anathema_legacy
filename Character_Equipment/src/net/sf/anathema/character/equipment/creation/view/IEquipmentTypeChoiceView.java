package net.sf.anathema.character.equipment.creation.view;

import java.awt.event.ItemListener;

import javax.swing.Action;

import net.disy.commons.swing.dialog.core.IPageContent;

public interface IEquipmentTypeChoiceView extends IPageContent {

  public void addStatisticsRow(String categoryLabel, Action action, String typeLabel, boolean isSelected);
  
  public void addCheckBox(String label, ItemListener listener, boolean isSelected);

  public void addHorizontalLine();
}