package net.sf.anathema.hero.framework.perspective.sheet;

import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Path;

import static net.sf.anathema.hero.framework.perspective.sheet.PrintCommand.PDF_EXTENSION;

public class FxFileChooser {

  //TODO (Swing->FX): Move this to Character_Main_FX instead of Character_Main_Impl
  public static Path selectSaveFile() {
    FileChooser chooser = new javafx.stage.FileChooser();
    chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*" + PDF_EXTENSION));
    File file = chooser.showSaveDialog(null);
    if (file == null) {
      return null;
    }
    return file.toPath();
  }
}