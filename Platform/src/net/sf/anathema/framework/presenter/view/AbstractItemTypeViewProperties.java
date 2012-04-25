package net.sf.anathema.framework.presenter.view;

import javax.swing.Icon;

import net.disy.commons.swing.ui.IObjectUi;
import net.sf.anathema.framework.item.IItemType;

public abstract class AbstractItemTypeViewProperties implements IItemTypeViewProperties {

  private final Icon icon;
  private final IItemType type;
  private final IObjectUi<Object> ui;

  public AbstractItemTypeViewProperties(IItemType type, Icon icon, IObjectUi<Object> ui) {
    this.type = type;
    this.icon = icon;
    this.ui = ui;
  }

  @Override
  public Icon getIcon() {
    return icon;
  }

  @Override
  public IObjectUi<Object> getItemTypeUI() {
    return ui;
  }

  @Override
  public String getLabelKey() {
    return "ItemType." + type.getId() + ".PrintName"; //$NON-NLS-1$ //$NON-NLS-2$
  }
}