package net.sf.anathema.charmentry.model.data;

public class ConfigurableMagicSource implements IConfigurableMagicSource {

  private String page;
  private String source;

  public String getSource() {
    return source;
  }

  public String getPage() {
    return page;
  }

  public void setSource(String newSource) {
    if (newSource == null || newSource.equalsIgnoreCase("Custom")) {
      newSource = "Custom";
      page = null;
    }
    this.source = newSource;
  }

  public void setPage(Integer newPage) {
    if (source != null && source.equalsIgnoreCase("Custom")) {
      this.page = null;
      return;
    }
    this.page = String.valueOf(newPage);
  }
}