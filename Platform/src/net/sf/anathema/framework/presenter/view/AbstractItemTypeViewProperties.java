package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.lib.gui.ui.ObjectUi;

import javax.swing.Icon;

public abstract class AbstractItemTypeViewProperties implements IItemTypeViewProperties {

  private final Icon icon;
  private final IItemType type;
  private final ObjectUi<Object> ui;

  public AbstractItemTypeViewProperties(IItemType type, Icon icon, ObjectUi<Object> ui) {
    this.type = type;
    this.icon = icon;
    this.ui = ui;
  }

  @Override
  public Icon getIcon() {
    return icon;
  }

  @Override
  public ObjectUi<Object> getItemTypeUI() {
    return ui;
  }

  @Override
  public String getLabelKey() {
    return "ItemType." + type.getId() + ".PrintName";
  }
}