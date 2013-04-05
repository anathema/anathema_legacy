package net.sf.anathema.lib.gui.ui;

import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.icon.ImageProvider;

import javax.swing.Icon;

public abstract class AbstractUI {

  private final ImageProvider imageProvider = new ImageProvider();

  protected Icon getIcon(RelativePath path) {
    return imageProvider.getImageIcon(path);
  }
}