package net.sf.anathema.character.equipment.impl.character.view;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.sf.anathema.character.equipment.character.model.IEquipmentObject;
import net.sf.anathema.character.equipment.character.view.IEquipmentAdditionalView;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ListObjectSelectionView;

public class EquipmentAdditionalView implements IEquipmentAdditionalView {

  private final ListObjectSelectionView<IEquipmentObject> equipmentPickList = new ListObjectSelectionView<IEquipmentObject>(
      IEquipmentObject.class);
  private final JPanel panel = new JPanel();

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