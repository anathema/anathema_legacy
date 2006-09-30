package net.sf.anathema.character.equipment.impl.module;

import javax.swing.Icon;
import javax.swing.JComponent;

import net.sf.anathema.character.equipment.impl.item.view.EquipmentDatabaseView;
import net.sf.anathema.framework.view.item.AbstractItemView;

public class EquipmentDatabaseItemView extends AbstractItemView {

  private final EquipmentDatabaseView view;

  protected EquipmentDatabaseItemView(String name, Icon icon, EquipmentDatabaseView view) {
    super(name, icon);
    this.view = view;
  }

  public JComponent getComponent() {
    return view.getComponent();
  }
}