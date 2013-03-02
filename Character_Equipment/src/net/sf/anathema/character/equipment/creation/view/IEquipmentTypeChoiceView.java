package net.sf.anathema.character.equipment.creation.view;

import net.sf.anathema.lib.gui.dialog.core.IPageContent;

import javax.swing.Action;

public interface IEquipmentTypeChoiceView extends IPageContent {

  void addStatisticsRow(String categoryLabel, Action action, String typeLabel, boolean isSelected);
  
  void addHorizontalLine();
}