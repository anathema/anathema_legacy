package net.sf.anathema.character.equipment.item;

import net.sf.anathema.framework.resources.ImageProvider;
import net.sf.anathema.lib.gui.ui.ObjectUi;

import javax.swing.Icon;

public class ConfigurableSwingUI<T> implements ObjectUi<T> {

  private TechnologyAgnosticUIConfiguration<T> configuration;

  public ConfigurableSwingUI(TechnologyAgnosticUIConfiguration<T> configuration) {
    this.configuration = configuration;
  }

  @Override
  public Icon getIcon(T value) {
    String relativePath = configuration.getIconsRelativePath(value);
    return new ImageProvider(".").getImageIcon(this.getClass(), relativePath);
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
