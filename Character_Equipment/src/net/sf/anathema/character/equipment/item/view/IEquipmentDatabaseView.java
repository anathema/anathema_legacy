package net.sf.anathema.character.equipment.item.view;

import javax.swing.JComponent;

import net.sf.anathema.lib.gui.IView;

public interface IEquipmentDatabaseView extends IView {

  public void fillDescriptionPanel(JComponent content);
}