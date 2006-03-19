package net.sf.anathema.development.reporting.util;

public class HeaderData {

  private final String label;
  private final String parameter;

  public HeaderData(String label) {
    this(label, null);
  }

  public HeaderData(String label, String parameter) {
    this.parameter = parameter;
    this.label = label;
  }

  public String getParameter() {
    return parameter;
  }

  public String getLabel() {
    return label;
  }
}