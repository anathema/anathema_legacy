package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;

public abstract class AbstractItemTypeViewProperties implements IItemTypeViewProperties {

  private final RelativePath icon;
  private final IItemType type;
  private final AgnosticUIConfiguration ui;

  public AbstractItemTypeViewProperties(IItemType type, RelativePath icon, AgnosticUIConfiguration ui) {
    this.type = type;
    this.icon = icon;
    this.ui = ui;
  }

  @Override
  public RelativePath getIcon() {
    return icon;
  }

  @Override
  public AgnosticUIConfiguration getItemTypeUI() {
    return ui;
  }

  @Override
  public String getLabelKey() {
    return "ItemType." + type.getId() + ".PrintName";
  }
}