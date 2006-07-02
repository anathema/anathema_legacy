package net.sf.anathema.character.equipment.impl.character.view;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.sf.anathema.character.equipment.character.view.IEquipmentAdditionalView;

public class EquipmentAdditionalView implements IEquipmentAdditionalView {
  
  private final JPanel panel = new JPanel();

  public JComponent getComponent() {
    return panel;
  }

  public boolean needsScrollbar() {
    return false;
  }
}