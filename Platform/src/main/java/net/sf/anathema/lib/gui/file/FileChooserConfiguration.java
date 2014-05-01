package net.sf.anathema.lib.gui.file;

public class FileChooserConfiguration {
  public final Extension extension;
  public final String nameSuggestion;

  public FileChooserConfiguration(Extension extension, String nameSuggestion) {
    this.extension = extension;
    this.nameSuggestion = nameSuggestion;
  }
}