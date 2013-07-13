package net.sf.anathema.lib.gui;

public interface ConfigurableTooltip {
  void showNoTooltip();

  void appendLine(String text);

  void appendTitleLine(String title);

  void appendLine(String label, String value);

  void appendParagraphs(String... paragraphs);

  void appendDescriptiveLine(String description);
}