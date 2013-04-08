package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AbstractUIConfiguration;

public class SimpleItemTypeUi extends AbstractUIConfiguration {

  private final RelativePath icon;

  public SimpleItemTypeUi(RelativePath icon) {
    this.icon = icon;
  }

  @Override
  public RelativePath getIconsRelativePath(Object value) {
    return icon;
  }

  @Override
  public String getLabel(Object file) {
    return file.toString();
  }
}