package net.sf.anathema.lib.gui.file;

import java.nio.file.Path;

public interface SingleFileChooser {
  Path selectSaveFile(FileChooserConfiguration configuration);
}