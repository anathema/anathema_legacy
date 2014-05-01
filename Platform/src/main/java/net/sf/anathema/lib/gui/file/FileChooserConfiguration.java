package net.sf.anathema.lib.gui.file;

public class FileChooserConfiguration {
  public final String description;
  public final String filter;
  public final String nameSuggestion;

  public FileChooserConfiguration(String description, String filter, String nameSuggestion) {
    this.description = description;
    this.filter = filter;
    this.nameSuggestion = nameSuggestion;
  }
}