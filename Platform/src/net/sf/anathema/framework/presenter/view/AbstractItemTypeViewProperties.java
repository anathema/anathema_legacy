package net.sf.anathema.framework.presenter.view;

import javax.swing.Icon;

import net.disy.commons.swing.ui.IObjectUi;
import net.sf.anathema.framework.item.IItemType;

public abstract class AbstractItemTypeViewProperties implements IItemTypeViewProperties {

  private final Icon icon;
  private final IItemType type;
  private final IObjectUi ui;

  public AbstractItemTypeViewProperties(IItemType type, Icon icon, IObjectUi ui) {
    this.type = type;
    this.icon = icon;
    this.ui = ui;
  }

  public Icon getIcon() {
    return icon;
  }

  public IObjectUi getItemTypeUI() {
    return ui;
  }

  public String getLabelKey() {
    return "ItemType." + type.getId() + ".PrintName"; //$NON-NLS-1$ //$NON-NLS-2$
  }
}