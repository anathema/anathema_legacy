package net.sf.anathema.framework.presenter.view;

import javax.swing.Icon;

import net.sf.anathema.framework.item.IItemType;

public abstract class AbstractItemTypeCreationViewProperties implements IItemTypeCreationViewProperties {

  private final Icon icon;
  private final IItemType type;

  public AbstractItemTypeCreationViewProperties(IItemType type, Icon icon) {
    this.type = type;
    this.icon = icon;
  }

  public Icon getIcon() {
    return icon;
  }

  public String getLabelKey() {
    return "ItemType." + type.getId() + ".PrintName"; //$NON-NLS-1$ //$NON-NLS-2$
  }
}