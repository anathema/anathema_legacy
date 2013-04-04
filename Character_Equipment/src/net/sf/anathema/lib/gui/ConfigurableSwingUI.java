package net.sf.anathema.lib.gui;

import net.sf.anathema.lib.gui.icon.ImageProvider;
import net.sf.anathema.lib.gui.ui.ObjectUi;

import javax.swing.Icon;

public class ConfigurableSwingUI<T> implements ObjectUi<T> {

  private TechnologyAgnosticUIConfiguration<T> configuration;

  public ConfigurableSwingUI(TechnologyAgnosticUIConfiguration<T> configuration) {
    this.configuration = configuration;
  }

  @SuppressWarnings("StringEquality")
  @Override
  public Icon getIcon(T value) {
    String relativePath = configuration.getIconsRelativePath(value);
    if (relativePath == TechnologyAgnosticUIConfiguration.NO_ICON) {
      return null;
    }
    return new ImageProvider().getImageIcon(relativePath);
  }

  @Override
  public String getLabel(T value) {
    return configuration.getLabel(value);
  }

  @Override
  public String getToolTipText(T value) {
    return configuration.getToolTipText(value);
  }
}
