package net.sf.anathema.character.equipment.impl.view;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.sf.anathema.character.equipment.view.IEquipmentItemDataView;

public class EquimentItemDataView implements IEquipmentItemDataView {

  private JPanel content = new JPanel();

  public JComponent getComponent() {
    return content;
  }
}