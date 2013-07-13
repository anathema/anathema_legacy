package net.sf.anathema.lib.gui;

import net.sf.anathema.lib.file.RelativePath;

public interface AgnosticUIConfiguration<T> {
  RelativePath NO_ICON = null;
  String NO_LABEL= null;
  String NO_TOOLTIP= null;

  RelativePath getIconsRelativePath(T value);

  String getLabel(T value);

  void configureTooltip(T item, ConfigurableTooltip configurableTooltip);
}