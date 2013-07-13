package net.sf.anathema.lib.gui;

public interface ConfigurableTooltip {
  void showNoTooltip();

  void appendLine(String text);

  void appendTitleLine(String title);

  void appendLine(String label, String value);

  void appendDescriptiveLine(String description);
}