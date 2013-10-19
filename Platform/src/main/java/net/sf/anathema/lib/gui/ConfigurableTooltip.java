package net.sf.anathema.lib.gui;

public interface ConfigurableTooltip {

  String CommaSpace = ", ";
  String Space = " ";
  String ColonSpace = ": ";

  void showNoTooltip();

  void appendLine(String text);

  void appendTitleLine(String title);

  void appendLine(String label, String value);

  void appendDescriptiveLine(String description);
}