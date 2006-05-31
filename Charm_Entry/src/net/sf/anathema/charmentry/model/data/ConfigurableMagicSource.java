package net.sf.anathema.charmentry.model.data;

import net.sf.anathema.character.generic.rules.IExaltedEdition;

public class ConfigurableMagicSource implements IConfigurableMagicSource {

  private String page;
  private String source;
  private IExaltedEdition edition;

  public String getSource() {
    return source;
  }

  public String getPage() {
    return page;
  }

  public IExaltedEdition getEdition() {
    return edition;
  }

  public void setSource(String newSource) {
    if (newSource == null || newSource.equalsIgnoreCase(CUSTOM_SOURCE_NAME)) {
      newSource = "Custom";
      page = null;
      edition = null;
    }
    this.source = newSource;
  }

  public void setPage(Integer newPage) {
    if (source != null && source.equalsIgnoreCase(CUSTOM_SOURCE_NAME)) {
      this.page = null;
      return;
    }
    this.page = String.valueOf(newPage);
  }

  public void setEdition(IExaltedEdition edition) {
    if (source != null && source.equalsIgnoreCase(CUSTOM_SOURCE_NAME)) {
      this.edition = null;
      return;
    }
    this.edition = edition;
  }
}