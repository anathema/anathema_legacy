package net.sf.anathema.character.equipment.impl.character.view;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.equipment.character.model.IEquipmentObject;
import net.sf.anathema.character.equipment.character.view.IEquipmentAdditionalView;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ListObjectSelectionView;

public class EquipmentAdditionalView implements IEquipmentAdditionalView {

  private final ListObjectSelectionView<IEquipmentObject> equipmentPickList = new ListObjectSelectionView<IEquipmentObject>(
      IEquipmentObject.class);
  private final JPanel panel = new JPanel(new GridDialogLayout(1, false));

  public EquipmentAdditionalView() {
    JScrollPane itemScrollpane = new JScrollPane(equipmentPickList.getContent());
    itemScrollpane.setPreferredSize(new Dimension(150, 350));
    panel.add(itemScrollpane, GridDialogLayoutData.FILL_BOTH);
  }

  public JComponent getComponent() {
    return panel;
  }

  public boolean needsScrollbar() {
    return false;
  }

  public IListObjectSelectionView<IEquipmentObject> getEquipmentObjectPickList() {
    return equipmentPickList;
  }
}