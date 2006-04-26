package net.sf.anathema.character.equipment.impl.view;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.character.equipment.view.IEquipmentItemDataView;

public class EquimentItemDataView implements IEquipmentItemDataView {

  private JPanel content = new JPanel(new GridDialogLayout(1, false));

  public JComponent getComponent() {
    return content;
  }

  public void fillDescriptionPanel(JComponent descriptionPanel) {
    content.add(descriptionPanel);
  }
}