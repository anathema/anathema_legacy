package net.sf.anathema.hero.framework.perspective.sheet;

import java.nio.file.Path;

public interface SingleFileChooser {
  Path selectSaveFile(FileChooserConfiguration configuration);
}