package net.sf.anathema.hero.framework.perspective.sheet;

public class FileChooserConfiguration {
  public final String description;
  public final String filter;

  public FileChooserConfiguration(String description, String filter) {
    this.description = description;
    this.filter = filter;
  }
}