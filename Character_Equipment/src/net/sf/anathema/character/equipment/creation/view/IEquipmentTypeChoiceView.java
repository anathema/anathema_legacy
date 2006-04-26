package net.sf.anathema.character.equipment.creation.view;

import javax.swing.Action;

import net.disy.commons.swing.dialog.core.IPageContent;

public interface IEquipmentTypeChoiceView extends IPageContent {

  public void addStatisticsRow(String categoryLabel, Action action, String typeLabel);

  public void addHorizontalLine();
}