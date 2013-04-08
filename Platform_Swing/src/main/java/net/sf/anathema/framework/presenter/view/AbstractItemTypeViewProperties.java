package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.TechnologyAgnosticUIConfiguration;

public abstract class AbstractItemTypeViewProperties implements IItemTypeViewProperties {

  private final RelativePath icon;
  private final IItemType type;
  private final TechnologyAgnosticUIConfiguration ui;

  public AbstractItemTypeViewProperties(IItemType type, RelativePath icon, TechnologyAgnosticUIConfiguration ui) {
    this.type = type;
    this.icon = icon;
    this.ui = ui;
  }

  @Override
  public RelativePath getIcon() {
    return icon;
  }

  @Override
  public TechnologyAgnosticUIConfiguration getItemTypeUI() {
    return ui;
  }

  @Override
  public String getLabelKey() {
    return "ItemType." + type.getId() + ".PrintName";
  }
}