package net.sf.anathema.character.presenter;

import net.sf.anathema.character.model.background.IBackground;

import static java.text.MessageFormat.format;

public class BackgroundTextCompiler {

  private Displayer displayer;

  public BackgroundTextCompiler(Displayer displayer) {
    this.displayer = displayer;
  }

  public String compileDisplayedText(IBackground background) {
    String description = background.getDescription() != null ? format(" ({0})", background.getDescription()) : "";
    return displayer.getDisplayObject(background) + description;
  }
}
